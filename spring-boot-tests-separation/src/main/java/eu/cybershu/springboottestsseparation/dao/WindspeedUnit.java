package eu.cybershu.springboottestsseparation.dao;


public enum WindspeedUnit {
    MS("ms"), MPH("mph"), KN("kn");

    private final String name;

    WindspeedUnit(String name) {
        this.name = name;
    }
}
