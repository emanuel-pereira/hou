package smarthome.model;

public interface Device {


    /* ---- Getters ---- */
    String getDeviceName();

    DeviceSpecs getDeviceSpecs();

    String getDeviceType();

    double getNominalPower();

    double getEnergyConsumption();

    boolean isActive();

    boolean isMetered();

    ReadingList getActivityLog();

    /* ---- Setters ---- */
    void setDeviceName(String name);

    void setNominalPower(double nominalPower);

    void setIsMetered(boolean isMetered);

    boolean deactivateDevice();


    /* ---- DEPRECATED ---- */
    String showDeviceAttributesInString();
}