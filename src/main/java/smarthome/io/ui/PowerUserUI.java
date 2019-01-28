package smarthome.io.ui;

import smarthome.model.House;

import java.util.Scanner;

public final class PowerUserUI {

    private PowerUserUI() {
    }

    public static void powerUser(House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        //System.out.println("POWER USER");

        while (option != 0) {
            System.out.println("\n******* POWER USER MENU ********\n");
            System.out.println("Click 1. Show all the devices connected to a grid");
            System.out.println("Click 2. Get the total nominal power connected to a grid");
            System.out.println("Click 3. Get the total nominal power of a room");
            System.out.println("Click 4. Estimate the total energy used in heating water in a given day");
            System.out.println("Click 5. Get the total nominal power in a subset of rooms and/or devices");
            System.out.println("Click 6. Deactivate a device");
            System.out.println("\nClick 0. Exit");

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
                    break;
                case 5:
                    System.out.println("US705");
                    DisplayVariableTotalNominalPowerUI ui705 = new DisplayVariableTotalNominalPowerUI(house);
                    ui705.run();
                    break;
                case 6:
                    System.out.println("US222");
                    EditDevicesUI uiDevices = new EditDevicesUI(house);
                    uiDevices.deactivateDevice();
                    break;
                default:
                    System.out.println("Input not accepted, please insert a number from 1 to 5\n");
                    break;
            }
        }
    }
}
