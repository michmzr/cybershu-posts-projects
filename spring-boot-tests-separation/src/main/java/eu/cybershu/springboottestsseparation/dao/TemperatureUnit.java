package eu.cybershu.springboottestsseparation.dao;

public enum TemperatureUnit {
    CELSIUS("celsius"), FAHRENHEIT("fahrenheit");

    public final String tempUnitName;

    TemperatureUnit(String tempUnitName) {
        this.tempUnitName = tempUnitName;
    }
}
