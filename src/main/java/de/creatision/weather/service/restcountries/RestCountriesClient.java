package de.creatision.weather.service.restcountries;

import de.creatision.weather.exception.InvalidLocationException;

public interface RestCountriesClient {
	
	String getCountryCode(String countryName) throws InvalidLocationException;

}
