package smarthome.io.ui;

import smarthome.model.*;

import java.util.Scanner;

public class PowerUserUI {

    private PowerUserUI() {
    }

    public static void powerUser(House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("POWER USER");

        while (option != 0) {
            System.out.println("Click 1. Show all the devices connected to a grid");
            System.out.println("Click 2. Get the total nominal power connected to a grid");
            System.out.println("Click 3. Get the total nominal power of a room");
            System.out.println("Click 4. Estimate the total energy used in heating water in a given day\n");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {

                /*case 1:
                    US160GetDeviceListInGridByTypeUI ui160 = new US160GetDeviceListInGridByTypeUI(house);
                    ui160.getDeviceListInGrid();
                    break;*/
                case 2:
                    TotalNominalPowerInGridUI uS172 = new TotalNominalPowerInGridUI(house);
                    uS172.run();
                    break;
                case 3:
                    TotalNominalPowerRoomUI ui230 = new TotalNominalPowerRoomUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 4:
                    EnergyConsumptionOfWaterHeatingUI ui752 = new EnergyConsumptionOfWaterHeatingUI(house);
                    ui752.run();
                default:
                    System.out.println("Please choose a valid option.");
            }

        }

    }
}
