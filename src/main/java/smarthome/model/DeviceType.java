package smarthome.model;

public interface DeviceType {

    /* ---- Getters ---- */
    String getDeviceType();

    /* ---- Creator ---- */
    Device createDevice(String deviceName, double nominalPower) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}