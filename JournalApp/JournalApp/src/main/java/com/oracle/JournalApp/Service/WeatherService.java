package com.oracle.JournalApp.Service;

import com.oracle.JournalApp.api_response.WeatherResponse;
import com.oracle.JournalApp.cache.Appcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String API_KEY;

    @Autowired
    private RestTemplate restTemplate;
@Autowired
private  Appcache appcache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appcache.Appcache.get("weather-app").replace("CITY", city).replace("ACCESS_KEY", API_KEY);
       ResponseEntity<WeatherResponse>response =  restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
      WeatherResponse body = response.getBody();
      return body;
    }
}
