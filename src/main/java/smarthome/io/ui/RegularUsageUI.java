package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public final class RegularUsageUI {

    private RegularUsageUI() {
    }

    public static void regularUsage(House house, SensorTypeList sensorTypeList, GAList gaList) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Regular Users UI");

        while (option != 0) {
            System.out.println("Click 1. US600: As a Regular User, I want to get a the current temperature in the house area.");
            System.out.println("Click 2. Show current temperature in a room");
            System.out.println("Click 3. US610: As a Regular User, I want to get the maximum temperature in a room.");
            System.out.println("Click 4. US620: As a Regular User, I want to get the total rainfall in a given day.");
            System.out.println("Click 5. US623: As a Regular User, I want to get the average daily rainfall in the house area for a given period (days).");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US600GetCurrentMeteoValueHouseAreaUI us600 = new US600GetCurrentMeteoValueHouseAreaUI(house, sensorTypeList, gaList);
                    us600.run();
                    break;
                case 2:
                    US605CurrentTempRoomUI uS605CurrentTempRoomUI = new US605CurrentTempRoomUI(house, sensorTypeList);
                    uS605CurrentTempRoomUI.run();
                    break;
                case 3:
                    System.out.println("US610");
                    break;
                case 4:
                    US620GetTotalRainfallOnaDayOfHouseUI us620GetTotalRainfallOnaDayOfHouseUI = new US620GetTotalRainfallOnaDayOfHouseUI(house, sensorTypeList);
                    us620GetTotalRainfallOnaDayOfHouseUI.run();
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
