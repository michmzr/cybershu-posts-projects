package eu.cybershu.springboottestsseparation.dao


import com.fasterxml.jackson.databind.ObjectMapper
import eu.cybershu.springboottestsseparation.BaseTestSpec

class WeatherHistoryResponseBindingTest extends BaseTestSpec {
    def setup() {
    }

    def "given json response expect deserialized all fields"() {
        given:
        ObjectMapper mapper = new ObjectMapper()
        mapper.findAndRegisterModules()

        String httpResponse = loadFile("weatherapi/responses/200_example01.json")
        when:
        WeatherHistoryResponse weatherHistoryResponse = mapper.readValue(httpResponse, WeatherHistoryResponse.class);

        then:
        verifyAll {
            weatherHistoryResponse.latitude == 52.52f
            weatherHistoryResponse.longitude == 13.419f
            weatherHistoryResponse.timezone == "Europe/Berlin"
            weatherHistoryResponse.timezoneAbbrevation == "CEST"
            weatherHistoryResponse.hourly.time.size() == 3
            weatherHistoryResponse.hourly.temperatures.size() == 9
        }
    }
}
