package de.weather.service.restcountries;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.weather.datatransferobject.RestCountriesResponse;
import de.weather.exception.InvalidLocationException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestCountriesClientImpl implements RestCountriesClient {

	private String restCountriesApiUrl;

	public RestCountriesClientImpl(@Value("${restcountries.api.url}") String restCountriesApiUrl) {
		this.restCountriesApiUrl = restCountriesApiUrl;
	}

	@Cacheable("country")
	@Override
	public String getCountryCode(String countryName) throws InvalidLocationException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		log.info("fetching country code from restcountriesapi for: " + countryName);
		RestCountriesResponse[] result = null;
		try {
			result = restTemplate.getForObject(restCountriesApiUrl + countryName,
					RestCountriesResponse[].class);
		} catch (RestClientException e) {
			log.error("invalid countryname");
			throw new InvalidLocationException("invalid countryname: " + e.getMessage());
		}
		
		log.info("fetched country code succesfully for:" + countryName);
		
		return result[0].getAlpha2Code();
	}

}
