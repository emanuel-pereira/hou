package smarthome.controller;

import smarthome.model.*;

public class USEnergyConsumptionOfWaterHeatingCTRL {


    private House mHouse;
    private RoomList mRoomList;
    private NumberValidations mNumberValidations;

    public USEnergyConsumptionOfWaterHeatingCTRL(House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse();
        mNumberValidations= new NumberValidations();
    }

    public void setVolumeOfWaterToHeatInEWHList(double volumeOfWater) {
        mHouse.setVolumeOfWaterToHeatInEWHList(volumeOfWater);
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
    public boolean isLowerThanHotWater(double coldWaterTemperature){
        return mHouse.isLowerThanHotWater(coldWaterTemperature);
    }
}
