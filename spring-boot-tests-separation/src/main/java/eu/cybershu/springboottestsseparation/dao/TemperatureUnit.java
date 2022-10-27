package eu.cybershu.springboottestsseparation.dao;

public enum TemperatureUnit {
    CELSIUS("celsius"), FAHRENHEIT("fahrenheit");

    private final String name;

    TemperatureUnit(String name) {
        this.name = name;
    }
}
