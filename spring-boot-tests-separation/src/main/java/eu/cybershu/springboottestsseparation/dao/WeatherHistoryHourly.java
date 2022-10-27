package eu.cybershu.springboottestsseparation.dao;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class WeatherHistoryHourly {
    private List<LocalDateTime> time;
    private List<Float> temperatures;
}
