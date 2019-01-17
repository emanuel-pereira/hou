package smarthome.controller;

import smarthome.model.*;

public class USEnergyConsumptionOfEWHCTRL {


    private House mHouse;
    private RoomList mRoomList;

    public USEnergyConsumptionOfEWHCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse();
    }

    public void setVolumeOfWaterInGlobalEWHList(double volumeOfWater) {
        mHouse.setVolumeOfWaterInGlobalEWHList(volumeOfWater);
    }
    public void setColdWaterTemperatureInGlobalEWHList(double coldWaterTemperature) {
        mHouse.setColdWaterTemperatureInGlobalEWHList(coldWaterTemperature);
    }

    public double getEnergyConsumptionOfEWHGlobalList(){
        return mHouse.getEnergyConsumptionOfEWHGlobalList();
    }
}
