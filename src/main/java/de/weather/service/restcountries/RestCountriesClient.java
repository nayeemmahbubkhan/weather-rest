package de.weather.service.restcountries;

import de.weather.exception.InvalidLocationException;

public interface RestCountriesClient {
	
	String getCountryCode(String countryName) throws InvalidLocationException;

}
