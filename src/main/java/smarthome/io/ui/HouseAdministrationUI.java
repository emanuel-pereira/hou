package smarthome.io.ui;

import smarthome.model.*;

import java.util.List;
import java.util.Scanner;

public class HouseAdministrationUI {

    public static void houseAdministration(DataTypeList dataTypeList, GAList gaList, List<Room> roomList, House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("House administration UI");


        while (option != 0) {
            System.out.println("Click 1. US101: As an Administrator, I want to configure the location of the house");
            System.out.println("Click 2. US105: As an Administrator, I want to add a new room to the house");
            System.out.println("Click 3. US108: As System Administrator I want to .........");
            System.out.println("Click 4. US130: As an Administrator I want to create a house grid");
            System.out.println("Click 5. US135: As System Administrator I want to .........");
            System.out.println("Click 6. As an Administrator, I want to attach/detach a room to/from a house grid and list rooms attached to a house grid");
            System.out.println("Click 7. US253: As System Administrator I want to .........");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US101ConfigureHouseLocationUI ui101 = new US101ConfigureHouseLocationUI(gaList, house);
                    ui101.configureHouseLocationUS101();
                    break;
                case 2:
                    US105AddNewRoomToHouseUI ui105 = new US105AddNewRoomToHouseUI(house);
                    ui105.addRoomToHouse ();
                    break;
                case 3:
                    System.out.println("US108");
                    break;
                case 4:
                    US130newHouseGridUI ui130 = new US130newHouseGridUI(house);
                    ui130.run();
                    break;
                case 5:
                    System.out.println("US135");
                    break;
                case 6:
                    USAttachRoomToGridAndListUI ui145 = new USAttachRoomToGridAndListUI(house);
                    ui145.run();
                    break;

                case 7:
                    US253AddSensorToRoomUI ui253 = new US253AddSensorToRoomUI(house, dataTypeList);
                    ui253.run();
                    System.out.println("US253");
                    break;
            }
        }
    }

}
