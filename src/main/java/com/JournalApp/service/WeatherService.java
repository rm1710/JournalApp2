package com.journalapp.service;

import com.journalapp.api.response.WeatherResponse;
import com.journalapp.cache.AppCache;
import com.journalapp.constants.Placeholders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalAPI= appCache.APP_CACHE.get("weather_api").replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        response.getStatusCode();
        WeatherResponse body= response.getBody();
        return body;
    }

}
