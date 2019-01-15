package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class PowerUserUI {

    public static void powerUser() {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Power Users UI");

        while (option != 0) {
            System.out.println("Click 1. I want to .........");
            System.out.println("Click 2. I want to know the total nominal power connected to a grid");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {

                case 1:
                    System.out.println("US160");
                    break;
                case 2:
                    System.out.println("US172");
                    break;
            }

        }

    }
}
