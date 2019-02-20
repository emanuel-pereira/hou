package smarthome.io.ui;

import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public final class RegularUsageUI {

    private RegularUsageUI() {
    }

    public static void regularUsage(House house, SensorTypeList sensorTypeList) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Regular Users UI");

        while (option != 0) {
            System.out.println("Click 1. Get the latest reading of a type in the house area.");
            System.out.println("Click 2. Show current temperature in a room");
            System.out.println("Click 3. Get the maximum temperature in a room.");
            System.out.println("Click 4. Get the total rainfall in a given day.");
            System.out.println("Click 5. Get the average daily rainfall in the house area for a given period (days).");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    GetCurrentTemperatureInHouseAreaUI us600 = new GetCurrentTemperatureInHouseAreaUI(house, sensorTypeList);
                    us600.run();
                    break;
                case 2:
                    GetCurrentTemperatureInRoomUI uS605CurrentTempRoomUI = new GetCurrentTemperatureInRoomUI(house, sensorTypeList);
                    uS605CurrentTempRoomUI.run();
                    break;
                case 3:
                    System.out.println("US610");
                    break;
                case 4:
                    GetTotalRainfallForDayInHouseAreaUI getTotalRainfallForDayInHouseAreaUI = new GetTotalRainfallForDayInHouseAreaUI(house, sensorTypeList);
                    getTotalRainfallForDayInHouseAreaUI.run();
                    break;
                case 5:
                    System.out.println("US623");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }

        }

    }
}
