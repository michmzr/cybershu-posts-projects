package eu.cybershu.springboottestsseparation.dao;


public enum WindspeedUnit {
    MS("ms"), MPH("mph"), KN("kn");

    public final String windUnitName;

    WindspeedUnit(String windUnitName) {
        this.windUnitName = windUnitName;
    }
}
