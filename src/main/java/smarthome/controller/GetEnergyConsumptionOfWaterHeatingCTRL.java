package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class GetEnergyConsumptionOfWaterHeatingCTRL {

    private RoomList mRoomList;


    public GetEnergyConsumptionOfWaterHeatingCTRL(House house) {
        mRoomList = house.getRoomList();
    }

    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        return mRoomList.getDevicesInAllRoomsByType(deviceType);
    }

    public String showDeviceAttributesInString(Device device) throws IllegalAccessException {
        return device.showDeviceAttributesInString();
    }

    public double getEnergyConsumptionByDeviceType(String deviceType) {
        return mRoomList.getEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(Device device, String attribute, String newValue) throws IllegalAccessException {
        device.setAttributeValue(attribute, newValue);
    }
}
