package de.weather.datatransferobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestCountriesResponse {

	private String name;
	private String alpha2Code;
}
