package eu.cybershu.springboottestsseparation.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cybershu.springboottestsseparation.dao.WeatherHistoryRequest;
import eu.cybershu.springboottestsseparation.dao.WeatherHistoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/**
 * docs: https://open-meteo.com/en/docs/historical-weather-api#latitude=52.52&longitude=13.41&start_date=2022-01-01&end_date=2022-07-13&hourly=temperature_2m&daily=temperature_2m_max,temperature_2m_min&timezone=Europe%2FBerlin
 */
@Slf4j
@Service
public class WeatherHistoryService {
    private final String endpoint = "https://archive-api.open-meteo.com/v1/era5";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    public WeatherHistoryResponse history(WeatherHistoryRequest weatherHistoryRequest)
            throws URISyntaxException, IOException, InterruptedException {
        log.info("Calling api with request params: {}", weatherHistoryRequest);

        Map<String, String> uriVars = weatherHistoryRequest.toMap();
        uriVars.put("hourly", "temperature_2m");
//        uriVars.put("daily", "temperature_2m_max,temperature_2m_min");

        URI uri = new URI(endpoint + "?" + toUriVars(uriVars));
        log.debug("uri: {}", uri);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        log.debug("code: {}", response.statusCode());

        // print response body
        log.debug("respose: {}", response.body());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), WeatherHistoryResponse.class);
    }

    private String toUriVars(Map<String, String> params) {
        var builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return builder.toString();
    }

}
