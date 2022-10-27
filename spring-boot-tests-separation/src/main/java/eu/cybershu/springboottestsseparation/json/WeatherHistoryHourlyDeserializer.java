package eu.cybershu.springboottestsseparation.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import eu.cybershu.springboottestsseparation.dao.WeatherHistoryHourly;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.LinkedList;
import java.util.List;

public class WeatherHistoryHourlyDeserializer extends JsonDeserializer<WeatherHistoryHourly> {
    private static final DateTimeFormatter TIMESTAMP_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
            .toFormatter();

    @Override
    public WeatherHistoryHourly deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        List<LocalDateTime> time = new LinkedList<>();
        List<Float> temperatures = new LinkedList<>();

        final var timesNode = node.get("time");
        timesNode.forEach(jsonNode -> {
            String timeVal = jsonNode.asText();
            time.add(LocalDateTime.parse(timeVal, TIMESTAMP_PARSER));
        });

        final var temperatureNode = node.get("temperature_2m");
        temperatureNode.forEach(jsonNode -> {
            String temp = jsonNode.asText();
            temperatures.add(Float.parseFloat(temp));
        });

        return new WeatherHistoryHourly(time, temperatures);
    }
}
