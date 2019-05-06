package de.creatision.weather.service.openweather;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidLocationException;

public interface OpenWeatherClient {

	CurrentWeather getWeatherInfo(String cityName, String postalCode, String countryCode)
			throws InvalidLocationException;

}
