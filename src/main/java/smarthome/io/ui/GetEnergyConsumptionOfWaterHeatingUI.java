package smarthome.io.ui;

import smarthome.controller.GetEnergyConsumptionOfWaterHeatingCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class GetEnergyConsumptionOfWaterHeatingUI {


    private GetEnergyConsumptionOfWaterHeatingCTRL mCtrl;
    Scanner read = new Scanner(System.in);

    public GetEnergyConsumptionOfWaterHeatingUI(House house) {
        mCtrl = new GetEnergyConsumptionOfWaterHeatingCTRL(house);
    }

    public void run() throws IllegalAccessException{

        for (Device device : mCtrl.getDevicesInAllRoomsByType("ElectricWaterHeater")) {
            String coldWaterTemperature = "coldWaterTemperature";
            String volumeOfWaterToHeat = "volumeOfWaterToHeat";
            System.out.println("Please specify the cold water temperature and the volume water to heat in the following device:");
            System.out.println(mCtrl.showDeviceAttributesInString(device));
            System.out.println("Please insert the cold water temperature (ºC):");
            String newColdWaterTemp = read.nextLine(); //to validate only positive values
            mCtrl.setAttribute(device, coldWaterTemperature, newColdWaterTemp);
            System.out.println("Please insert the volume of water to heat:");
            String newVolumeOfWater = read.nextLine();
            mCtrl.setAttribute(device, volumeOfWaterToHeat, newVolumeOfWater);
            System.out.println("The following Electric Water Heater was successfully updated with the following parameters:");
            System.out.println(mCtrl.showDeviceAttributesInString(device));
        }
        System.out.println("The total energy used in heating water in a given day is " + mCtrl.getEnergyConsumptionByDeviceType("ElectricWaterHeater") + " KWh.\n");
    }
}
