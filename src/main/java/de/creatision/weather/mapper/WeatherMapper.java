package de.creatision.weather.mapper;

import org.openweathermap.api.model.currentweather.CurrentWeather;

import static de.creatision.weather.domainvalue.Degree.CELSIUS;

import de.creatision.weather.datatransferobject.WeatherResponse;

public class WeatherMapper {

	public static WeatherResponse makeWeatherResponse(CurrentWeather currentWeather) {
		return WeatherResponse.builder()
				.temperature(currentWeather.getMainParameters().getTemperature())
				.city(currentWeather.getCityName())
				.unit(CELSIUS.getCode())
				.build();
	}

}
