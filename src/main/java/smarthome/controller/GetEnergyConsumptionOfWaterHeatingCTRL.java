package smarthome.controller;

import smarthome.model.*;

import java.util.List;

import static java.lang.Double.parseDouble;

public class GetEnergyConsumptionOfWaterHeatingCTRL {

    private RoomList mRoomList;


    public GetEnergyConsumptionOfWaterHeatingCTRL(House house) {
        mRoomList = house.getRoomList();
    }

    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        return mRoomList.getDevicesInAllRoomsByType(deviceType);
    }

    public double getEnergyConsumptionByDeviceType(String deviceType) {
        return mRoomList.getEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(Device device, String attribute, String newValue){
        device.getDeviceSpecs().setAttributeValue(attribute,parseDouble(newValue));
    }
}
