package smarthome.model;

public interface Device {

    /* ---- Getters ---- */
    String getDeviceName();

    DeviceSpecs getDeviceSpecs();

    String getDeviceType();

    boolean isActive();

    ReadingList getActivityLog();

    double getNominalPower();

    /* ---- Setters ---- */
    void setDeviceName(String name);

    void setNominalPower(double nominalPower);

    boolean deactivateDevice();

}