package smarthome.model.devices;

import smarthome.model.Device;
import smarthome.model.DeviceSpecs;
import smarthome.model.DeviceType;

public class ElectricWaterHeaterType implements DeviceType {
    private String deviceType = "ElectricWaterHeater";

    public String getDeviceType() {
        return this.deviceType;
    }

    public Device createDevice(String deviceName, double nominalPower) {
        String devType = getDeviceType();
        String[] attributeNames = {"Volume of Water Capacity", "Volume of Water to Heat","Hot Water Temperature","Cold Water Temperature", "Performance Ratio"};
        String[] attributeUnits = {"liters","liters","C","C","PR"};

        DeviceSpecs devSpecs = new GenericSpecs(devType, attributeNames, attributeUnits);

        return new ElectricWaterHeater(deviceName, devSpecs, nominalPower);
    }
}
