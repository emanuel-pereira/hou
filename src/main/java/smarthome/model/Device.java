package smarthome.model;

public interface Device {


    /* ---- Getters ---- */
    String getDeviceName();

    //TODO Does it make sense to ask for the entire DeviceSpecs from a Device point of view?
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