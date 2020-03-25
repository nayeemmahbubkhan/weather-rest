package de.weather.service.openweather;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import de.weather.exception.EntityNotFoundException;
import de.weather.exception.InvalidLocationException;

public interface OpenWeatherClient {

	CurrentWeather getWeatherInfo(String cityName, String postalCode, String countryCode)
			throws InvalidLocationException;

}
