package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.House;
import smarthome.model.SensorTypeList;
import smarthome.model.*;

import java.util.List;
import java.util.Scanner;

public class HouseAdministrationUI {

    public static void houseAdministration(SensorTypeList sensorTypeList, GAList gaList, List<Room> roomList,
                                           House house, HouseGridList hgList, PowerSourceList pslist) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("House administration UI");


        while (option != 0) {
            System.out.println("Click 1. US101: As an Administrator, I want to configure the location of the house");
            System.out.println("Click 2. US105: As an Administrator, I want to add a new room to the house");
            System.out.println("Click 3. US108: As System Administrator, I want to select a room and edit.");
            System.out.println("Click 4. US130: As an Administrator I want to create a house grid");
            System.out.println("Click 5. US135: As System Administrator I want to add a new power source to a house grid");
            System.out.println("Click 6. As an Administrator, I want to attach/detach a room to/from a house grid and list rooms attached to a house grid");
            System.out.println("Click 7. Get the Total Nominal Power from a Room.");
            System.out.println("Click 8. I want to add a new sensor to a room from the list of available sensor types, in order to configure it. ");
            System.out.println("Click 9. I want to list all sensors in a room");
            System.out.println("Click 10. As an Administrator, I want add a new device to a room, list all devices in a room or edit the configuration of an existing device");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US101ConfigureHouseLocationUI ui101 = new US101ConfigureHouseLocationUI(gaList, house);
                    ui101.configureHouseLocationUS101();
                    break;
                case 2:
                    US105AddNewRoomToHouseUI ui105 = new US105AddNewRoomToHouseUI(house);
                    ui105.addRoomToHouse();
                    break;
                case 3:/*
                    US108EditRoomOfHouseUI ui108 = new US108EditRoomOfHouseUI(house.getRoomListFromHouse().getRoomList());
                    ui108.run();*/
                    break;
                case 4:

                    US130newHouseGridUI ui130 = new US130newHouseGridUI(house, hgList);
                    ui130.run();
                    break;
                case 5:
                    System.out.println("US135");
                    US135AddPowerSourceToGridUI ui135 = new US135AddPowerSourceToGridUI(house,hgList,pslist);
                    ui135.addPowerSourceToHouseGrid();
                    break;
                case 6:
                    USAttachRoomToGridAndListUI ui145 = new USAttachRoomToGridAndListUI(house);
                    ui145.run();
                    break;
                case 7:
                    US230TotalNominalPowerRoomUI ui230 = new US230TotalNominalPowerRoomUI(house);
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
                    USAddSetAndListDevicesInRoomUI ui210 = new USAddSetAndListDevicesInRoomUI(house);
                    ui210.selectOption();
                    break;
            }
        }
    }
}
