package de.weather.service.openweather;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.ResponseFormat;
import org.openweathermap.api.query.Type;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import de.weather.exception.InvalidLocationException;
import de.weather.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenWeatherClientImpl implements OpenWeatherClient {

	private String openweatherApiKey;
	
	@Autowired
	public OpenWeatherClientImpl(@Value("${openweather.api.key}") String openweatherApiKey, UserService userService) {
		this.openweatherApiKey = openweatherApiKey;
	}

	@Cacheable("weather")
	@Override
	public CurrentWeather getWeatherInfo(String cityName, String postalCode, String countryCode)
			throws InvalidLocationException {


		DataWeatherClient client = new UrlConnectionDataWeatherClient(openweatherApiKey);

		CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = (cityName != null)
				? getQueryBuilderForCity(cityName, countryCode)
				: getQueryBuilderForPostalCode(postalCode, countryCode);

		log.info("fetching weather info from openweather api for city: " + cityName + " with country code: "
				+ countryCode);
		CurrentWeather currentWeather = null;
		//TODO: improve exception handling
		try {
			currentWeather = client.getCurrentWeather(currentWeatherOneLocationQuery);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new InvalidLocationException("incorrect location");
		}
		
		log.info("fetched temperature successfull: " + currentWeather.getMainParameters().getTemperature() + " for "
				+ currentWeather.getCityName() + ", " + currentWeather.getSystemParameters().getCountry());

		return currentWeather;
	}

	private CurrentWeatherOneLocationQuery getQueryBuilderForCity(String cityName, String countryCode) {

		return QueryBuilderPicker.pick().currentWeather().oneLocation().byCityName(cityName).countryCode(countryCode)
				.type(Type.ACCURATE).language(Language.ENGLISH).responseFormat(ResponseFormat.JSON)
				.unitFormat(UnitFormat.METRIC).build();
	}

	private CurrentWeatherOneLocationQuery getQueryBuilderForPostalCode(String postalCode, String countryCode) {

		return QueryBuilderPicker.pick().currentWeather().oneLocation().byZipCode(postalCode, countryCode)
				.language(Language.ENGLISH).responseFormat(ResponseFormat.JSON)
				.unitFormat(UnitFormat.METRIC).build();
	}
}
