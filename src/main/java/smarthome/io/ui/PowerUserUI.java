package smarthome.io.ui;

import java.util.ArrayList;

public final class PowerUserUI {

    private PowerUserUI() {
    }

    public static void powerUser() {
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
                    GetDeviceListInGridByTypeUI ui160 = new GetDeviceListInGridByTypeUI();
                    ui160.checkIfHGListIsEmpty();
                    break;
                case 2:
                    EditDevicesUI devicesUI = new EditDevicesUI();
                    devicesUI.deactivateDevice();
                    break;
                case 3:
                    GetTotalNominalPowerUI ui230 = new GetTotalNominalPowerUI();
                    ui230.getRoomTotalNominalPower ();
                    break;
                case 4:
                    GetTotalNominalPowerUI uS172 = new GetTotalNominalPowerUI();
                    uS172.getGridTotalNominalPower ();
                    break;
                case 5:
                    UtilsUI.showInfo("404","The US is currently not available");
                    break;
                case 6:
                    GetEnergyConsumptionInPeriodUI uiEC = new GetEnergyConsumptionInPeriodUI();
                    uiEC.selectMetered();
                    break;
                case 7:
                    UtilsUI.underMaintenanceMsg("US730");

                    break;
                case 8:
                    UtilsUI.underMaintenanceMsg("US752");
                    break;
                default:
                    //no action needed
            }
        }
    }
}
