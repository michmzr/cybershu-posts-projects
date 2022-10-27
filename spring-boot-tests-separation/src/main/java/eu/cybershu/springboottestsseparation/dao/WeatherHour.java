package eu.cybershu.springboottestsseparation.dao;

import java.time.Instant;

public record WeatherHour(
        Instant time,
        float temperature
) {
}
