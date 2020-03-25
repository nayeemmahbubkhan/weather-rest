package de.weather.service.restcountries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.weather.datatransferobject.RestCountriesResponse;
import de.weather.exception.InvalidLocationException;
import de.weather.service.openweather.OpenWeatherClientImpl;

@Service
public class RestCountriesClientImpl implements RestCountriesClient {

	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherClientImpl.class);
	private String restCountriesApiUrl;

	public RestCountriesClientImpl(@Value("${restcountries.api.url}") String restCountriesApiUrl) {
		this.restCountriesApiUrl = restCountriesApiUrl;
	}

	@Cacheable("country")
	@Override
	public String getCountryCode(String countryName) throws InvalidLocationException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		LOG.info("fetching country code from restcountriesapi for: " + countryName);
		RestCountriesResponse[] result = null;
		try {
			result = restTemplate.getForObject(restCountriesApiUrl + countryName,
					RestCountriesResponse[].class);
		} catch (RestClientException e) {
			LOG.error("invalid countryname");
			throw new InvalidLocationException("invalid countryname: " + e.getMessage());
		}
		LOG.info("fetched succesfully code");
		
		return result[0].getAlpha2Code();
	}

}
