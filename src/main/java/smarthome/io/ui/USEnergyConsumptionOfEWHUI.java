package smarthome.io.ui;

import smarthome.controller.USEnergyConsumptionOfEWHCTRL;
import smarthome.model.House;
import smarthome.model.Room;
import smarthome.model.RoomList;

import java.util.Scanner;

public class USEnergyConsumptionOfEWHUI {

    private House mHouse;
    private RoomList mRoomList;
    private USEnergyConsumptionOfEWHCTRL mCtrl;
    Scanner read = new Scanner(System.in);

    public USEnergyConsumptionOfEWHUI(House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse();
        mCtrl = new USEnergyConsumptionOfEWHCTRL(house);
    }

    private double mColdWaterTemperature;
    private double mVolumeOfWater;

    public void run() {
        System.out.println("Please insert the cold water temperature (ºC) in a given day:");
        mColdWaterTemperature = read.nextDouble();
        read.nextLine();
        mCtrl.setColdWaterTemperatureInGlobalEWHList(mColdWaterTemperature);
        while (true) {
            System.out.println("Please insert the volume of water (l) produced in each water heater:");
            mVolumeOfWater = read.nextDouble();
            read.nextLine();
            if (mCtrl.valueIsPositive(mVolumeOfWater)) {
                mCtrl.setVolumeOfWaterInGlobalEWHList(mVolumeOfWater);
                break;
            }
            System.out.println("Please insert a positive value for volume of water.");
        }
        System.out.println("Current Electric Water Heaters installed in the house: \n");
        System.out.println(mCtrl.showElectricWaterHeaterList());
        System.out.println("The estimated energy used in heating water in a given day is: " + mCtrl.getEnergyConsumptionOfEWHGlobalList() + " KWh.");
    }
}
