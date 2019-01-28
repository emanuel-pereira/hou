package smarthome.io.ui;

import smarthome.model.*;

import java.util.List;
import java.util.Scanner;

public final class HouseAdministrationUI {
    private HouseAdministrationUI(){}

    public static void houseAdministration(SensorTypeList sensorTypeList, GAList gaList, List<Room> roomList,
                                           House house, HouseGridList hgList, PowerSourceList pslist) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("House administration UI");
        
        while (option != 0) {
            System.out.println("Click 1. Configure the location of the house");
            System.out.println("Click 2. Add a new room to the house");
            System.out.println("Click 3. Show the list of existing rooms");
            System.out.println("Click 4. Create a house grid");
            System.out.println("Click 5. Add a new power source to a house grid");
            System.out.println("Click 6. Attach/detach a room to/from a house grid and list rooms attached to a house grid");
            System.out.println("Click 7. Show the Total Nominal Power from a Room.");
            System.out.println("Click 8. Add a new sensor to a room");
            System.out.println("Click 9. Show list all sensors in a room");
            System.out.println("Click 10. Add a new device to a room, list all devices in a room or edit the configuration of an existing device");
            System.out.println("Click 11. Show all the devices connected to a grid");
            System.out.println("Click 12. Show the total nominal power connected to a grid");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    ConfigureHouseLocationUI ui101 = new ConfigureHouseLocationUI(gaList, house);
                    ui101.configureHouseLocationUS101();
                    break;
                case 2:
                    US105AddNewRoomToHouseUI ui105 = new US105AddNewRoomToHouseUI(house);
                    ui105.addRoomToHouse();
                    break;
                case 3:
                    US108ListTheRoomsOfHouseUI ui108 = new US108ListTheRoomsOfHouseUI(house.getRoomList().getRoomList());
                    ui108.run();
                    break;
                case 4:
                    NewHouseGridUI ui130 = new NewHouseGridUI(house, hgList);
                    ui130.run();
                    break;
                case 5:
                    //US135AddPowerSourceToGridUI ui135 = new US135AddPowerSourceToGridUI(house, hgList, pslist);
                    //ui135.addPowerSourceToHouseGrid();
                    break;
                case 6:
                    AttachDetachAndListRoomsInGridUI ui145 = new AttachDetachAndListRoomsInGridUI(house);
                    ui145.checkIfHGListIsEmpty();
                    break;
                case 7:
                    TotalNominalPowerRoomUI ui230 = new TotalNominalPowerRoomUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 8:
                    US253AddSensorToRoomUI ui253 = new US253AddSensorToRoomUI(house, sensorTypeList);
                    ui253.run();
                    break;
                case 9:
                    US253AddSensorToRoomUI us250 = new US253AddSensorToRoomUI(house, sensorTypeList);
                    us250.run2();
                    break;
                case 10:
                    EditDevicesUI ui210 = new EditDevicesUI(house);
                    ui210.selectOption();
                    break;
                case 11:
                    //US160GetDeviceListInGridByTypeUI ui160 = new US160GetDeviceListInGridByTypeUI (house);
                    //ui160.getDeviceListInGrid ();
                    break;
                case 12:
                    TotalNominalPowerInGridUI uS172 = new TotalNominalPowerInGridUI(house);
                    uS172.run();
                    break;

                default:
                    System.out.println("Please choose a valid option.");

            }
        }
    }
}

