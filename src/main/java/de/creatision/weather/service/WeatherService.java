package de.creatision.weather.service;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidLocationException;

public interface WeatherService {

	CurrentWeather getWeather(String cityName, String postalCode, String countryCode, Long userId)
			throws EntityNotFoundException, InvalidLocationException;

}
