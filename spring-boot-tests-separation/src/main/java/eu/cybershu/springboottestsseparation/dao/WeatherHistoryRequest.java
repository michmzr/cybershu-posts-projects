package eu.cybershu.springboottestsseparation.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public record WeatherHistoryRequest(
        float latitude,
        float longitude,
        LocalDate startDate,
        LocalDate endDate,
        TemperatureUnit temperatureUnit,
        WindspeedUnit windspeedUnit,
        ZoneId zoneId
) {

    public Map<String, String> toMap() {
        Map<String, String> properties = new HashMap<>();
        properties.put("latitude", String.valueOf(latitude));
        properties.put("longitude", String.valueOf(longitude));
        properties.put("start_date", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        properties.put("end_date", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        properties.put("temperature_unit", temperatureUnit.tempUnitName);
        properties.put("windspeed_unit", windspeedUnit.windUnitName);
//        properties.put("zoneId", zoneId.s);
        return properties;
    }
}
