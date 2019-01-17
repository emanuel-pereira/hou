package smarthome.io.ui;

import smarthome.controller.USEnergyConsumptionOfEWHCTRL;
import smarthome.model.House;
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
        inputColdWaterTemperature();
        inputVolumeOfWater();
        displayTotalEnergyConsumed();
    }

    private void inputColdWaterTemperature() {
        while (true) {
            System.out.println("Please insert the cold water temperature (ºC) in a given day:");
            mColdWaterTemperature = read.nextDouble();
            read.nextLine();
            if (mCtrl.isLowerThanHotWater(mColdWaterTemperature)) {
                mCtrl.setColdWaterTemperatureInGlobalEWHList(mColdWaterTemperature);
                break;
            }
            System.out.println("Cold water temperature must be set with a lower value than the lower hot water temperature in all" +
                    "Electric Water Heaters.");
        }
    }
    private void inputVolumeOfWater() {
        while (true) {
            System.out.println("Please insert the volume of water (l) produced in each water heater:");
            mVolumeOfWater = read.nextDouble();
            read.nextLine();
            if (mCtrl.valueIsPositive(mVolumeOfWater)) {
                mCtrl.setVolumeOfWaterToHeatInEWHList(mVolumeOfWater);
                break;
            }
            System.out.println("Please insert a positive value for volume of water.");
        }
    }
    private void displayTotalEnergyConsumed() {
        System.out.println("Current Electric Water Heaters installed in the house: \n");
        System.out.println(mCtrl.showElectricWaterHeaterList());
        System.out.println("The estimated total energy used in heating water in a given day is: " + mCtrl.getEnergyConsumptionOfEWHGlobalList() + " KWh.");
    }



}
