package smarthome.io.ui;

import java.util.Scanner;

public final class RoomOwnerUI {

    private RoomOwnerUI() {
    }

    public static void roomOwner() {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Room Owners UI");

        while (option != 0) {
            System.out.println("Click 1. I want to know the total nominal power of a Room");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    GetTotalNominalPowerUI ui230 = new GetTotalNominalPowerUI();
                    ui230.getRoomTotalNominalPower ();
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
