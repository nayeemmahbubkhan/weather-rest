package de.weather.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import de.weather.service.openweather.OpenWeatherClientImpl;

@RunWith(SpringRunner.class)
@RestClientTest(OpenWeatherClientImpl.class)
@TestPropertySource(properties = "app.weather.api.key=test-ABC")
public class OpenWeatherClientTest {

}
