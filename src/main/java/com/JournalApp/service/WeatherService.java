package com.journalApp.service;

import com.journalApp.api.response.WeatherResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WeatherService {
    private static final String apiKey= "def8f576a4cf35c18ffbc86142ce7710";

    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI= API.replace("CITY",city).replace("API_KEY",apiKey);
        HttpEntity<String> httpEntity= new HttpEntity<>(requestBody);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        response.getStatusCode();
        WeatherResponse body= response.getBody();
        return body;
    }

}
