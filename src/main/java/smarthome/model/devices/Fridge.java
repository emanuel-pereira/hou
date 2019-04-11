package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

public class Fridge extends GenericDevice {
    Fridge(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
    }

    @Override
    public double getEstimatedEnergyConsumption(){
        return this.getNominalPower()/365;
    }

}