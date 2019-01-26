package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class USEnergyConsumptionOfWaterHeatingCTRL {

    private RoomList mRoomList;


    public USEnergyConsumptionOfWaterHeatingCTRL(House house) {
        mRoomList = house.getRoomList();
    }

    public List<Device> getDevicesInAllRoomsByType() {
        return mRoomList.getDevicesInAllRoomsByType(DeviceType.ELECTRIC_WATER_HEATER);
    }

    public String showDeviceAttributesInString(Device device) {
        return device.showDeviceAttributesInString();
    }

    public double getEnergyConsumptionByDeviceType(DeviceType deviceType) {
        return mRoomList.getEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(Device device, String attribute, String newValue) {
        device.setAttributeValue(attribute, newValue);
    }
}
