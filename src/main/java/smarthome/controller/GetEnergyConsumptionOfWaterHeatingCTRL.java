package smarthome.controller;

import smarthome.model.Device;
import smarthome.model.House;
import smarthome.model.RoomList;

import java.util.List;

import static java.lang.Double.parseDouble;

public class GetEnergyConsumptionOfWaterHeatingCTRL {

    private RoomList roomList;


    public GetEnergyConsumptionOfWaterHeatingCTRL(House house) {
        this.roomList = house.getRoomList();
    }

    public List<Device> getDevicesInAllRoomsByType(String deviceType) {
        return this.roomList.getDevicesInAllRoomsByType(deviceType);
    }

    public double getEnergyConsumptionByDeviceType(String deviceType) {
        return this.roomList.getEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(Device device, String attribute, String newValue){
        device.getDeviceSpecs().setAttributeValue(attribute,parseDouble(newValue));
    }
}
