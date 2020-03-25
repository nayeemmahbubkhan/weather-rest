package de.weather.service.security;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import de.weather.exception.InvalidAccessTokenException;

@Service
//TODO: @Singleton 
public class JWTServiceImpl implements JWTService {
	
  private static final Logger LOG = LoggerFactory.getLogger(JWTServiceImpl.class);
  private String jwtIssuer;
  private String signatureSecret;
  private long jwtValidTimeInDays;
  private static final String USER_ID = "userId";

  @Autowired
  public JWTServiceImpl(@Value("${jwt.issuer}") String jwtIssuer,
      @Value("${jwt.signature.secret}") String signatureSecret,
      @Value("${jwt.valid.days}") String jwtValidTimeInDays) {
    this.jwtIssuer = jwtIssuer;
    this.signatureSecret = signatureSecret;
    this.jwtValidTimeInDays = Long.parseLong(jwtValidTimeInDays);
  }

  @Override
  public String encodeIntoJwt(String userId) {
    
    try {
      return JWT.create().withIssuer(jwtIssuer)
          .withExpiresAt(Date.from(Instant.now().plus(jwtValidTimeInDays, ChronoUnit.DAYS)))
          .withClaim(USER_ID, userId)
          .sign(Algorithm.HMAC256(signatureSecret));
    } catch (UnsupportedEncodingException e) {
      LOG.error("JWT not created!", e);
      throw new RuntimeException("JWT not created!", e);
    }
  }

  @Override
  public String decodeAndVerifyJwt(String jwtAsString) throws SecurityException, InvalidAccessTokenException {
    
    JWT jwt;
	try {
		jwt = JWT.decode(jwtAsString);
	} catch (JWTDecodeException e) {
		throw new InvalidAccessTokenException("invalid accessKey");
	}
    String userId = jwt.getClaim("userId").asString();
    verify(jwtAsString, userId);
    
    return userId;
  }

  private String verify(String jwtAsString, String userId) throws InvalidAccessTokenException {
    try {
      JWTVerifier verifier = JWT.require(Algorithm.HMAC256(signatureSecret))
          .withIssuer(jwtIssuer)
          .withClaim(USER_ID, userId).build();
      
      DecodedJWT decodedJWT = verifier.verify(jwtAsString);
      
      Date expiresAt = decodedJWT.getExpiresAt();
      LOG.debug("expiresAt {}", expiresAt.toString());
      
      if (Date.from(Instant.now()).after(expiresAt)) {
        throw new InvalidAccessTokenException("JWT is no longer valid");
      }
      return decodedJWT.getToken();
    } catch (SignatureVerificationException | UnsupportedEncodingException | InvalidClaimException | JWTDecodeException e) {
      LOG.error("JWT not verified!", e);
      throw new InvalidAccessTokenException("JWT verification failed!", e);
    }
  }

}
