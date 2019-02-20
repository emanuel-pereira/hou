package smarthome.io.ui;

import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.ArrayList;

public final class RegularUsageUI {

    private RegularUsageUI() {
    }

    public static void regularUsage(House house, SensorTypeList sensorTypeList) {
        int option = -1;

        while (option != 0) {

            ArrayList<String> options = new ArrayList<>();
            options.add("[1] Get the latest reading of a type in the house area.");
            options.add("[2] Show current temperature in a room");
            options.add("[3] Get the maximum temperature in a room.");
            options.add("[4] Get the total rainfall in a given day.");
            options.add("[5] Get the average daily rainfall in the house area for a given period (days).");
            options.add("[0] Exit");

            UtilsUI.showList("Regular Usage", options, false, 5);
            option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 5, or 0 to exit the program");

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
                    //no action needed
            }

        }

    }
}
