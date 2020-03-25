package de.weather.service;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import de.weather.exception.EntityNotFoundException;
import de.weather.exception.InvalidLocationException;

public interface WeatherService {

	CurrentWeather getWeather(String cityName, String postalCode, String countryName, Long userId)
			throws EntityNotFoundException, InvalidLocationException;

}
