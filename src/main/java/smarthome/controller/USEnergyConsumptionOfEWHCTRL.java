package smarthome.controller;

import smarthome.model.*;

public class USEnergyConsumptionOfEWHCTRL {


    private House mHouse;
    private RoomList mRoomList;
    private NumberValidations mNumberValidations;

    public USEnergyConsumptionOfEWHCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse();
        mNumberValidations= new NumberValidations();
    }

    public void setVolumeOfWaterInGlobalEWHList(double volumeOfWater) {
        mHouse.setVolumeOfWaterInGlobalEWHList(volumeOfWater);
    }

    public void setColdWaterTemperatureInGlobalEWHList(double coldWaterTemperature) {
        mHouse.setColdWaterTemperatureInGlobalEWHList(coldWaterTemperature);
    }

    public double getEnergyConsumptionOfEWHGlobalList() {
        return mHouse.getEnergyConsumptionOfEWHGlobalList();
    }

    public String showElectricWaterHeaterList() {
        return mHouse.showElectricWaterHeaterList();
    }

    public boolean valueIsPositive(double value){
        return mNumberValidations.valueIsPositive(value);
    }
}
