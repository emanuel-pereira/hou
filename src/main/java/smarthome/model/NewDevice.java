package smarthome.model;

public interface NewDevice {

    String getDeviceName();
    NewDeviceSpecs getDeviceSpecs();
    double getNominalPower();
    boolean isActive();
    ReadingList getActivityLog();

    void setDeviceName(String name);
    void setNominalPower(double nominalPower);
    boolean deactivateDevice();

}