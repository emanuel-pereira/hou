package smarthome.io.ui;

import smarthome.model.House;

import java.util.Scanner;

public class RoomOwnerUI {

    public static void RoomOwner(House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Room Owners UI");

        while (option != 0) {
            System.out.println("Click 1. I want to know the total nominal power of a Room");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    System.out.println("US230");
                    US230TotalNominalPowerRoomUI ui230 = new US230TotalNominalPowerRoomUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
            }

        }

    }
}
