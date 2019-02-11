package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class EnergyConsumptionOfWaterHeatingCTRL {

    private RoomList mRoomList;


    public EnergyConsumptionOfWaterHeatingCTRL(House house) {
        mRoomList = house.getRoomList();
    }

    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        return mRoomList.getDevicesInAllRoomsByType(deviceType);
    }

    public String showDeviceAttributesInString(Device device) {
        return device.showDeviceAttributesInString();
    }

    public double getEnergyConsumptionByDeviceType(String deviceType) {
        return mRoomList.getEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(Device device, String attribute, String newValue) {
        device.setAttributeValue(attribute, newValue);
    }
}
