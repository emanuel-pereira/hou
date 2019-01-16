package smarthome.io.ui;

import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;
import static smarthome.io.ui.RoomOwnerUI.RoomOwner;

public class MainUI {
    public static void main(String[] args) {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        HouseGridList hglist = new HouseGridList();
        PowerSourceList pslist = new PowerSourceList();
        List<Room> roomList = new ArrayList<>();




        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Click 1 System Administration");
            System.out.println("Click 2 House Administration");
            System.out.println("Click 3 Regular User");
            System.out.println("Click 4 Power User");
            System.out.println("Click 5 Room Owner");
            System.out.println("Click 0 Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    systemAdministration(sensorTypeList,gaList);
                    break;
                case 2:
                    houseAdministration(sensorTypeList,gaList,roomList,house,hglist,pslist);
                    break;
                case 3:
                    regularUsage(house,sensorTypeList,gaList);
                    break;
                case 4:
                    powerUser(house);
                    break;
                case 5:
                    RoomOwner(house);
                break;
            }
        }
    }
}