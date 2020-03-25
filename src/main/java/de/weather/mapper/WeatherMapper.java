package de.weather.mapper;

import static de.weather.domainvalue.Degree.CELSIUS;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import de.weather.datatransferobject.WeatherResponse;

public class WeatherMapper {

	public static WeatherResponse makeWeatherResponse(CurrentWeather currentWeather) {
		return WeatherResponse.builder()
				.temperature(currentWeather.getMainParameters().getTemperature())
				.city(currentWeather.getCityName())
				.unit(CELSIUS.getCode())
				.build();
	}

}
