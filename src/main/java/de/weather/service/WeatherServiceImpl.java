package de.weather.service;

import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.weather.domainobject.User;
import de.weather.exception.EntityNotFoundException;
import de.weather.exception.InvalidLocationException;
import de.weather.service.openweather.OpenWeatherClient;
import de.weather.service.openweather.OpenWeatherClientImpl;
import de.weather.service.restcountries.RestCountriesClient;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherClientImpl.class);
	private UserService UserService;
	private OpenWeatherClient openWeatherClient;
	private RestCountriesClient restCountriesClient;

	@Autowired
	public WeatherServiceImpl(UserService userService, OpenWeatherClient openWeatherClient,
			RestCountriesClient restCountriesClient) {
		this.UserService = userService;
		this.openWeatherClient = openWeatherClient;
		this.restCountriesClient = restCountriesClient;
	}

	@Override
	public CurrentWeather getWeather(String cityName, String postalCode, String countryName, Long userId)
			throws EntityNotFoundException, InvalidLocationException {

		String countryCode = restCountriesClient.getCountryCode(countryName);
		
		if (cityName == null & postalCode == null) {
			User user = UserService.findUser(userId);
			cityName = user.getCity();
			countryCode = restCountriesClient.getCountryCode(user.getCountry());
		}

		return openWeatherClient.getWeatherInfo(cityName, postalCode, countryCode);
	}

}
