package eu.cybershu.springboottestsseparation.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import eu.cybershu.springboottestsseparation.json.WeatherHistoryHourlyDeserializer;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherHistoryResponse {
        private float latitude;
        private float longitude;
        private String timezone;
        @JsonProperty("timezone_abbreviation")
        private String timezoneAbbrevation;

        @JsonDeserialize(using = WeatherHistoryHourlyDeserializer.class)
        private WeatherHistoryHourly hourly;
}
