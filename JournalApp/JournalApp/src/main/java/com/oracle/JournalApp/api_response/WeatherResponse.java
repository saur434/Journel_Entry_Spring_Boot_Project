package com.oracle.JournalApp.api_response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Current {
        private int temperature;
        private int weather_code;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int humidity;
    }
}
