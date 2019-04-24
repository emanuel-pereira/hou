package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

public class Freezer extends GenericDevice {
    /**
     * Public constructor for creating an instance of a Freezer which inherits all characteristics of a Generic Device
     * plus its specific characteristics (DeviceSpecs)
     * @param deviceName name of the device
     * @param deviceSpecs specific attributes of the device Freezer
     * @param nominalPower integer value representing the nominal power of a device
     */
    public Freezer(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
    }

    /**
     * @return the estimated daily energy consumption of a freezer given its annual energy consumption
     */
    @Override
    public double getEstimatedEnergyConsumption(){
        return this.getNominalPower()/365;
    }

}

