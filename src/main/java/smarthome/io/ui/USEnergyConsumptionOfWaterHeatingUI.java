package smarthome.io.ui;

import smarthome.controller.USEnergyConsumptionOfWaterHeatingCTRL;
import smarthome.model.House;
import smarthome.model.RoomList;

import java.util.Scanner;

public class USEnergyConsumptionOfWaterHeatingUI {

    private House mHouse;
    private RoomList mRoomList;
    private USEnergyConsumptionOfWaterHeatingCTRL mCtrl;
    Scanner read = new Scanner(System.in);

    public USEnergyConsumptionOfWaterHeatingUI(House house) {
        mHouse = house;
        mRoomList = house.getRoomListFromHouse();
        mCtrl = new USEnergyConsumptionOfWaterHeatingCTRL(house);
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
            if (mCtrl.isValidColdWaterTemperature(mColdWaterTemperature)) {
                mCtrl.setColdWaterTemperatureInAllEWH(mColdWaterTemperature);
                break;
            }
            System.out.println("Cold water temperature must be set with a lower value than the lower hot water temperature in all" +
                    " Electric Water Heaters.");
        }
    }

    private void inputVolumeOfWater() {
        while (true) {
            System.out.println("Please insert the volume of water to heat in each water heater:");
            mVolumeOfWater = read.nextDouble();
            read.nextLine();
            if (mCtrl.valueIsPositive(mVolumeOfWater) && mCtrl.isValidVolumeOfWater(mVolumeOfWater)) {
                mCtrl.setVolumeOfWaterToHeatInAllEWH(mVolumeOfWater);
                break;
            }
            System.out.println("The volume of water to heat must be positive and cannot exceed any electric water heater capacity.");
        }
    }

    private void displayTotalEnergyConsumed() {
        System.out.println("Current Electric Water Heaters installed in the house: \n");
        System.out.println(mCtrl.showElectricWaterHeaterList());
        System.out.println("The estimated total energy used in heating water in a given day is: " + mCtrl.getEnergyConsumptionOfEWHGlobalList() + " KWh.");
    }
}
