package smarthome.model;

public interface Device {


    /* ---- Getters ---- */
    String getDeviceName();
    DeviceSpecs getDeviceSpecs();
    double getNominalPower();
    boolean isActive();
    ReadingList getActivityLog();

    /* ---- Setters ---- */
    void setDeviceName(String name);
    void setNominalPower(double nominalPower);

    boolean deactivateDevice();

}