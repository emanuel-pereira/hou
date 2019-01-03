package smarthome.io.ui;

import smarthome.model.DataTypeList;
import smarthome.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseAdministrationUI {

    public static void houseAdministration(DataTypeList dataTypeList) {
        List<Room> roomList= new ArrayList<> ();
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("House administration UI");


        while (option != 0) {
            System.out.println("Click 1. US101: As System Administrator I want to .........");
            System.out.println("Click 2. US105: As an Administrator, I want to add a new room to the house");
            System.out.println("Click 3. US108: As System Administrator I want to .........");
            System.out.println("Click 4. US130: As System Administrator I want to .........");
            System.out.println("Click 5. US135: As System Administrator I want to .........");
            System.out.println("Click 6. US145: As System Administrator I want to .........");
            System.out.println("Click 7. US147: As System Administrator I want to .........");
            System.out.println("Click 8. US149: As System Administrator I want to .........");
            System.out.println("Click 9. US253: As System Administrator I want to .........");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    System.out.println("US101");
                    break;
                case 2:
                    US105AddNewRoomToHouseUI ui105 = new US105AddNewRoomToHouseUI(roomList);
                    ui105.addRoomToTheHouse ();
                    break;
                case 3:
                    System.out.println("US108");
                    break;
                case 4:
                    System.out.println("US130");
                    break;
                case 5:
                    System.out.println("US135");
                    break;
                case 6:
                    System.out.println("US145");
                    break;
                case 7:
                    System.out.println("US147");
                    break;
                case 8:
                    System.out.println("US149");
                    break;
                case 9:
                    US253AddSensorToRoomUI ui253 = new US253AddSensorToRoomUI(roomList,dataTypeList);
                    ui253.run();
                    System.out.println("US253");
                    break;
            }
        }
    }

}
