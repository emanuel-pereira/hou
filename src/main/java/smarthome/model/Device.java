package smarthome.model;

import java.util.Calendar;

public interface Device {


    /* ---- Getters ---- */
    String getDeviceName();

    DeviceSpecs getDeviceSpecs();

    String getDeviceType();

    double getNominalPower();
/*

    double getEnergyConsumption(Calendar startDate, Calendar endDate);
*/

    boolean isActive();

    boolean isMetered();

    ReadingList getActivityLog();

    /* ---- Setters ---- */
    void setDeviceName(String name);

    void setNominalPower(double nominalPower);

    void setIsMetered(boolean isMetered);

    boolean deactivateDevice();

}