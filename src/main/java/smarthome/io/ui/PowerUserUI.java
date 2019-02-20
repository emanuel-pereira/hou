package smarthome.io.ui;

import smarthome.model.House;

import java.util.ArrayList;

public final class PowerUserUI {

    private PowerUserUI() {
    }

    public static void powerUser(House house) {
        int option = -1;

        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Show all the devices connected to a grid");
            options.add("[2] Get the total nominal power connected to a grid");
            options.add("[3] Get the total nominal power of a room");
            options.add("[4] Estimate the total energy used in heating water in a given day");
            options.add("[5] Get the total nominal power in a subset of rooms and/or devices");
            options.add("[6] Deactivate a device");
            options.add("[7] Get the total energy consumption of a device, a room or a grid in a given time interval");
            options.add("[8] Get the data series of the metered energy consumption of a device/room/grid in a given time interval");
            options.add("[0] Exit");

            UtilsUI.showList("Power Usage", options, false, 5);
            option = UtilsUI.requestIntegerInInterval(0, 8, "Please choose an action between 1 and 8, or 0 to exit the program");

            switch (option) {
                case 1:
                    System.out.println("US 160 GetDeviceListInGridByTypeUI is under maintenance, it will be available shortly");
                    break;
                case 2:
                    GetGridTotalNominalPowerUI uS172 = new GetGridTotalNominalPowerUI(house);
                    uS172.run();
                    break;
                case 3:
                    GetRoomTotalNominalPowerUI ui230 = new GetRoomTotalNominalPowerUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 4:
                    System.out.println("US 752 GetEnergyConsumptionOfWaterHeatingUI is under maintenance, it will be available shortly");
                    break;
                case 5:
                    System.out.println("US 705 DisplayCustomTotalNominalPower is under maintenance, it will be available shortly");
                    break;
                case 6:
                    EditDevicesUI devicesUI = new EditDevicesUI(house);
                    devicesUI.deactivateDevice();
                    break;
                case 7:
                    GetEnergyConsumptionInPeriodUI uiEC = new GetEnergyConsumptionInPeriodUI(house);
                    uiEC.selectOption();
                    break;
                case 8:
                    System.out.println("US730 EnergyConsumptionDataSeries is under maintenance, it will be available shortly");
                    System.out.println(house.getRoomList().getRoomList().get(2).getDeviceList().get(1).getActivityLogSum());
                    break;
                default:
                    //no action needed
            }
        }
    }
}
