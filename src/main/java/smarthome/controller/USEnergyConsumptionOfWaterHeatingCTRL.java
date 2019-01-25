package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Validations.NumberValidations;

import java.util.List;

public class USEnergyConsumptionOfWaterHeatingCTRL {


    private House mHouse;
    private RoomList mRoomList;
    private NumberValidations mNumberValidations;

    public USEnergyConsumptionOfWaterHeatingCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomList();
        mNumberValidations = new NumberValidations();
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
