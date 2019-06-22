package smarthome.io.ui;

import smarthome.model.SensorTypeList;

import java.util.ArrayList;

public final class RegularUsageUI {

    private RegularUsageUI() {
    }

    public static void regularUsage(SensorTypeList sensorTypeList) throws IllegalAccessException {
        int option = -1;

        while (option != 0) {

            ArrayList<String> options = new ArrayList<>();
            options.add("[1] Get the latest reading of a type in the house area.");
            options.add("[2] Show current temperature in a room");
            options.add("[3] Get the maximum temperature in a room in a given day.");
            options.add("[4] Get the total rainfall in a given day.");
            options.add("[5] Get the average daily rainfall in the house area for a given period (days).");
            options.add("[6] Display the last coldest day in the house area.");
            options.add("[7] Display the first hottest day in the house area.");
            options.add("[8] Display the day with the highest temperature amplitude in the house area.");
            options.add("[0] Exit");

            UtilsUI.showList("Regular Usage", options, false, 5);
            option = UtilsUI.requestIntegerInInterval(0, 8, "Please choose an action between 1 and 8, or 0 to exit the program");

            switch (option) {
                case 1:
                    GetCurrentTemperatureInHouseAreaUI us600 = new GetCurrentTemperatureInHouseAreaUI(sensorTypeList);
                    us600.run();
                    break;
                case 2:
                    GetCurrentTemperatureInRoomUI uS605CurrentTempRoomUI = new GetCurrentTemperatureInRoomUI(sensorTypeList);
                    uS605CurrentTempRoomUI.run();
                    break;
                case 3:
                    UtilsUI.underMaintenanceMsg("610");
                    break;
                case 4:
                    GetTotalRainfallForDayInHouseAreaUI getTotalRainfallForDayInHouseAreaUI = new GetTotalRainfallForDayInHouseAreaUI(sensorTypeList);
                    getTotalRainfallForDayInHouseAreaUI.run();
                    break;
                case 5:
                    GetAverageDailyRainfallForTimeIntervalInHouseAreaUI avgRainfallUi = new GetAverageDailyRainfallForTimeIntervalInHouseAreaUI(sensorTypeList);
                    avgRainfallUi.getAverageDailyRainfallForInterval();
                    break;
                case 6:
                    GetDailySensorDataUI getDailySensorDataUI = new GetDailySensorDataUI(sensorTypeList);
                    getDailySensorDataUI.displayLastMaximum();
                    break;
                case 7:
                    getDailySensorDataUI = new GetDailySensorDataUI(sensorTypeList);
                    getDailySensorDataUI.displayFirstMaximum();
                    break;
                case 8:
                    getDailySensorDataUI = new GetDailySensorDataUI(sensorTypeList);
                    getDailySensorDataUI.displayMaxAmplitude();
                    break;
                default:
                    //no action needed
            }

        }

    }
}
