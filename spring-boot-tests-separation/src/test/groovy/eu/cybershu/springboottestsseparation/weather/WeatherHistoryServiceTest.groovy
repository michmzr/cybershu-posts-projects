package eu.cybershu.springboottestsseparation.weather

import eu.cybershu.springboottestsseparation.dao.TemperatureUnit
import eu.cybershu.springboottestsseparation.dao.WeatherHistoryRequest
import eu.cybershu.springboottestsseparation.dao.WindspeedUnit
import spock.lang.Specification

import java.time.LocalDate
import java.time.ZoneId

class WeatherHistoryServiceTest extends Specification {
    private WeatherHistoryService weatherService

    def setup() {
        weatherService = new WeatherHistoryService()
    }

    def cleanup() {
    }

    def "test one"() {
        given:
        def request = new WeatherHistoryRequest(
                52.52f,
                13.41f,
                LocalDate.now().minusDays(14),
                LocalDate.now().minusDays(7),
                TemperatureUnit.CELSIUS,
                WindspeedUnit.MS,
                ZoneId.systemDefault()
        )
        when:
        def response = weatherService.history(request)

        then:
        verifyAll {
            response
            response.longitude
            response.latitude
            !response.getHourly().temperatures.isEmpty()
            !response.getHourly().time.isEmpty()
        }
    }
}
