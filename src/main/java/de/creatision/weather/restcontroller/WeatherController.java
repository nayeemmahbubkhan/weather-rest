package de.creatision.weather.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.creatision.weather.datatransferobject.WeatherResponse;
import de.creatision.weather.exception.DataFormatException;
import de.creatision.weather.exception.EntityNotFoundException;
import de.creatision.weather.exception.InvalidAccessTokenException;
import de.creatision.weather.exception.InvalidLocationException;
import de.creatision.weather.service.WeatherService;
import de.creatision.weather.service.security.JWTService;

import static de.creatision.weather.mapper.WeatherMapper.makeWeatherResponse;

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
