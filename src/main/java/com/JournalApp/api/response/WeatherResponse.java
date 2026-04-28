package com.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherResponse {
    private Current current;

    @Data
    public class Current{
        @JsonProperty("observation_time")
        public String observationTime;
        public int temperature;

        @JsonProperty("wheather_code")
        public int weatherCode;

        @JsonProperty("weather_descriptions")
        public List<String> weatherDescriptions;

        @JsonProperty("wind_speed")
        public int windSpeed;

        @JsonProperty("wind_degree")
        public int windDegree;

        public int feelslike;
    }

    public class Location{
        public String name;
        public String country;
        public String region;
        public String lat;
        public String lon;
        public String timezone_id;
        public String localtime;
        public int localtime_epoch;
        public String utc_offset;
    }

}
