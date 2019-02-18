package smarthome.io.ui;

import smarthome.model.House;

import java.util.Scanner;

public final class RoomOwnerUI {

    private RoomOwnerUI() {
    }

    public static void roomOwner(House house) {
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
                    GetRoomTotalNominalPowerUI ui230 = new GetRoomTotalNominalPowerUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 2:
                    System.out.println("new functions are coming soon");
                    break;

                default:
                    System.out.println("Please choose a valid option.");
            }

        }

    }
}
