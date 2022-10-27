package eu.cybershu.springboottestsseparation.dao;

import java.time.Instant;
import java.time.ZoneId;

record WeatherHistoryRequest(
        float latitude,
        float longitude,
        Instant startDate,
        Instant endDate,
        TemperatureUnit temperatureUnit,
        WindspeedUnit windspeedUnit,
        ZoneId zoneId
) {
}
