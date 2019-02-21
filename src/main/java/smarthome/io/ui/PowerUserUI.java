package smarthome.io.ui;

import smarthome.model.House;

import java.util.ArrayList;

final class PowerUserUI {

    private PowerUserUI() {
    }

    static void powerUser(House house) {
        int option = -1;

        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Show all the devices connected to a grid");
            options.add("[2] Deactivate a device");
            options.add("[3] Get the total nominal power of a room");
            options.add("[4] Get the total nominal power of a grid");
            options.add("[5] Get the total nominal power of a subset of rooms and/or devices");
            options.add("[6] Get the total energy consumption of a device, a room or a grid in a given time interval");
            options.add("[7] Get the total energy consumption of as a data series of a device/room/grid in a given time interval");
            options.add("[8] Estimate the total energy used in heating water in a given day");
            options.add("[0] Exit");

            UtilsUI.showList("Power Usage", options, false, 5);
            option = UtilsUI.requestIntegerInInterval(0, 8, "Please choose an action between 1 and 8, or 0 to exit the program");

            switch (option) {
                case 1:
                    UtilsUI.underMaintenanceMsg("US160");
                    break;
                case 2:
                    EditDevicesUI devicesUI = new EditDevicesUI(house);
                    devicesUI.deactivateDevice();
                    break;
                case 3:
                    GetTotalNominalPowerUI ui230 = new GetTotalNominalPowerUI(house);
                    ui230.getRoomTotalNominalPowerUI ();
                    break;
                case 4:
                    GetTotalNominalPowerUI uS172 = new GetTotalNominalPowerUI(house);
                    uS172.getGridTotalNominalPowerUI ();
                    break;
                case 5:
                    System.out.println("US 705 DisplayCustomTotalNominalPower is under maintenance, it will be available shortly");

                    break;
                case 6:
                    GetEnergyConsumptionInPeriodUI uiEC = new GetEnergyConsumptionInPeriodUI(house);
                    uiEC.selectOption();
                    break;
                case 7:
                    System.out.println("US730 EnergyConsumptionDataSeries is under maintenance, it will be available shortly");
                    System.out.println(house.getRoomList().getRoomList().get(2).getDeviceList().get(1).getActivityLogSum());
                    break;
                case 8:
                    System.out.println("US 752 GetEnergyConsumptionOfWaterHeatingUI is under maintenance, it will be available shortly");
                    break;
                default:
                    //no action needed
            }
        }
    }
}
