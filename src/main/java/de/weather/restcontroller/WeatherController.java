package de.weather.restcontroller;

import static de.weather.mapper.WeatherMapper.makeWeatherResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.weather.datatransferobject.WeatherResponse;
import de.weather.exception.DataFormatException;
import de.weather.exception.EntityNotFoundException;
import de.weather.exception.InvalidAccessTokenException;
import de.weather.exception.InvalidLocationException;
import de.weather.service.WeatherService;
import de.weather.service.security.JWTService;

@RestController
@RequestMapping("v1/weather")
public class WeatherController {

	private WeatherService weatherService;
	private JWTService securityService;

	@Autowired
	public WeatherController(WeatherService weatherService, JWTService securityService) {
		this.weatherService = weatherService;
		this.securityService = securityService;
	}

	@GetMapping
	public WeatherResponse getWeather(@RequestHeader String accessToken, @RequestParam(required = false) String cityName,
			@RequestParam(required = false) String postalCode, @RequestParam(defaultValue = "Germany") String countryName)
			throws InvalidLocationException, NumberFormatException, SecurityException, DataFormatException,
			InvalidAccessTokenException, EntityNotFoundException {
		Long userId = Long.parseLong(securityService.decodeAndVerifyJwt(accessToken));
		return makeWeatherResponse(weatherService.getWeather(cityName, postalCode, countryName, userId));
	}

}
