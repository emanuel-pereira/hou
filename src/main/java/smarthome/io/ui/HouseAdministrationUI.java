package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.ArrayList;

public final class HouseAdministrationUI {

    private HouseAdministrationUI() {
    }

    public static void houseAdministration(SensorTypeList sensorTypeList, GAList gaList,
                                           House house) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        int option = -1;
        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Configure the location of the house");
            options.add("[2] Add a new room to the house");
            options.add("[3] Show the list of existing rooms");
            options.add("[4] Create a house grid");
            options.add("[5] Add a new power source to a house grid");
            options.add("[6] List (Attach/detach) a room to/from a house grid");
            options.add("[7] Show the Total Nominal Power from a Room.");
            options.add("[8] Add a new sensor to a room");
            options.add("[9] List all sensors in a room");
            options.add("[10] List (edit/add/remove) devices in a room");
            options.add("[11] Show all the devices connected to a grid");
            options.add("[12] Show the total nominal power connected to a grid");
            options.add("[0] Exit");

            UtilsUI.showList("House administration", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 12, "Please choose an action between 1 and 12, or 0 to exit the program");

            switch (option) {
                case 1:
                    ConfigureHouseLocationUI ui101 = new ConfigureHouseLocationUI(gaList, house);
                    ui101.configureHouseLocationUS101();
                    break;
                case 2:
                    AddRoomToHouseUI ui105 = new AddRoomToHouseUI(house);
                    ui105.addRoomToHouse();
                    break;
                case 3:
                    ListRoomsOfHouseUI ui108 = new ListRoomsOfHouseUI(house.getRoomList());
                    ui108.run();
                    break;
                case 4:
                    NewHouseGridUI ui130 = new NewHouseGridUI(house);
                    ui130.run();
                    break;
                case 5:
                    AddPowerSourceToGridUI ui135 = new AddPowerSourceToGridUI(house);
                    ui135.checkIfHGListIsEmpty();
                    break;
                case 6:
                    AttachDetachAndListRoomsInGridUI ui145 = new AttachDetachAndListRoomsInGridUI(house);
                    ui145.checkIfHGListIsEmpty();
                    break;
                case 7:
                    GetTotalNominalPowerUI ui230 = new GetTotalNominalPowerUI(house);
                    ui230.getRoomTotalNominalPower ();
                    break;
                case 8:
                    NewSensorUI newSensorUI = new NewSensorUI(house, sensorTypeList,gaList);
                    newSensorUI.checkIfRoomListIsEmpty();
                    break;
                case 9:
                    NewSensorUI listSensorsUI = new NewSensorUI(house, sensorTypeList,gaList);
                    listSensorsUI.selectRoomAndList();
                    break;
                case 10:
                    EditDevicesUI ui210 = new EditDevicesUI(house);
                    ui210.selectOption();
                    break;
                case 11:
                    UtilsUI.underMaintenanceMsg("US160");
                    break;
                case 12:
                    GetTotalNominalPowerUI uS172 = new GetTotalNominalPowerUI(house);
                    uS172.getGridTotalNominalPower ();
                    break;
                default:
                    //no action needed
            }
        }
    }
}

