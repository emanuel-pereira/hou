package smarthome.io.ui;

import smarthome.model.*;

import java.util.Scanner;

public class PowerUserUI {

    public static void powerUser(House house) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Power Users UI");

        while (option != 0) {
            System.out.println("Click 1. I want to .........");
            System.out.println("Click 2. I want to know the total nominal power connected to a grid");
            System.out.println("Click 3. I want to know the total nominal power of a Room");
            System.out.println("Click 4. I want to estimate the total energy used in heating water in a given day");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {

                case 1:
                    System.out.println("US160");
                    break;
                case 2:
                    US172TotalNominalPowerInGridUI uS172 = new US172TotalNominalPowerInGridUI (house);
                    uS172.run ();
                    break;
                case 3:
                    US230TotalNominalPowerRoomUI ui230 = new US230TotalNominalPowerRoomUI(house);
                    ui230.showTotalNominalPowerRoom();
                    break;
                case 4:
                    USEnergyConsumptionOfEWHUI ui752= new USEnergyConsumptionOfEWHUI(house);
                    ui752.run();

            }

        }

    }
}
