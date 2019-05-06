package de.creatision.weather.service;

import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.creatision.weather.domainobject.User;
import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidLocationException;
import de.creatision.weather.service.openweather.OpenWeatherClient;
import de.creatision.weather.service.openweather.OpenWeatherClientImpl;
import de.creatision.weather.service.restcountries.RestCountriesClient;

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
	public CurrentWeather getWeather(String cityName, String postalCode, String countryCode, Long userId)
			throws EntityNotFoundException, InvalidLocationException {

		if (cityName == null & postalCode == null) {
			User user = UserService.findUser(userId);
			cityName = user.getCity();
			countryCode = restCountriesClient.getCountryCode(user.getCountry());
		}

		return openWeatherClient.getWeatherInfo(cityName, postalCode, countryCode);
	}

}
