package smarthome.model.devices;

import smarthome.model.DeviceSpecs;

public class ElectricWaterHeater extends GenericDevice {

    public ElectricWaterHeater(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        double estimatedEnergyConsumption;
        double temperatureDelta = getDeviceSpecs().getAttributeValue("Hot Water Temperature") - getDeviceSpecs().getAttributeValue("Cold Water Temperature");
        estimatedEnergyConsumption=(1.163 / 1000) * getDeviceSpecs().getAttributeValue("Volume of Water to Heat") * temperatureDelta - getDeviceSpecs().getAttributeValue("Performance Ratio");
        return estimatedEnergyConsumption;
    }

}