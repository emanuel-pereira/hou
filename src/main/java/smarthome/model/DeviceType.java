package smarthome.model;

public interface DeviceType {

    /* ---- Getters ---- */
    String getDeviceType();

    /* ---- Creator ---- */
    Device createDevice();
}