package de.weather.service.security;

import static de.weather.service.securityvalue.Encoder.BCRYPT;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptServiceImpl implements BCryptService {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCRYPT.getStrength());

	@Override
	public String encode(String rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public boolean verify(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

}
