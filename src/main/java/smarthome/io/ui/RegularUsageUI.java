package smarthome.io.ui;

import smarthome.model.House;
import smarthome.model.RoomList;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class RegularUsageUI {

    public static void regularUsage(House house, RoomList roomList, SensorTypeList sensorTypeList) {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;
        System.out.println("Regular Users UI");

        while (option != 0) {
            System.out.println("Click 1. US600: As a Regular User, I want to .........");
            System.out.println("Click 2. US605CurrentTempRoomUI: Show current temperature in a room");
            System.out.println("Click 3. US610: As a Regular User, I want to .........");
            System.out.println("Click 4. US620: As a Regular User, I want to  .........");
            System.out.println("Click 5. US623: As a Regular User, I want to get the average daily rainfall in the house area for a given period (days)");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    System.out.println("US600");
                    break;
                case 2:
                    US605CurrentTempRoomUI uS605CurrentTempRoomUI = new US605CurrentTempRoomUI (house, sensorTypeList); //US605CurrentTempRoomUI uS605CurrentTempRoomUI = new US605CurrentTempRoomUI(roomList,sensorTypeList);
                    uS605CurrentTempRoomUI.run (); //uS605CurrentTempRoomUI.showCurrentTempRoom ();
                break;
                case 3:
                    System.out.println("US610");
                    break;
                case 4:
                    System.out.println("US620");
                    break;
                case 5:
                    System.out.println("US623");
                    break;
            }

        }

    }
}
