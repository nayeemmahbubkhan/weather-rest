package de.creatision.weather.datatransferobject;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeatherResponse {
	
	private double temperature;
	private String unit;
	private String city;
	
}
