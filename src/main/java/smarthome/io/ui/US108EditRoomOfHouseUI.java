package smarthome.io.ui;

import smarthome.controller.US108EditRoomOfHouseCTRL;
import smarthome.model.House;
import smarthome.model.Room;

import java.util.List;
import java.util.Scanner;

public class US108EditRoomOfHouseUI {


    private US105AddNewRoomToHouseUI roomToHouseUI;
    private List<Room> mRoomList;

    private US108EditRoomOfHouseCTRL mCtrlUS108;
    private House mHouse;
    private Room mRoom;


    public US108EditRoomOfHouseUI(List<Room> inputList) {
        mRoomList = inputList;

        mCtrlUS108 = new US108EditRoomOfHouseCTRL(inputList);

    }


    public void run() {

        Scanner read = new Scanner(System.in);


        System.out.println("Select the Room to edit from the list below:");
        System.out.println(mCtrlUS108.showListRoomInString());

        int indexRoom = read.nextInt();

        System.out.println("Success! Room selected. Edit room name (Space character are not allowed).");

        String inputRoomName = read.next();

        System.out.println("Success! Name room edited. Insert room floor");


        int inputFloor = read.nextInt();

        System.out.println("Success! Edit room dimensions. Insert room length" );

        double inputAreaLength = read.nextDouble();

        System.out.println("Success! Insert room width" );

        double inputAreaWidth = read.nextDouble();

        System.out.println("Success! Insert room height" );

        double inputHeight = read.nextDouble();

        System.out.println("Success! Room height edited.");


        mCtrlUS108.setRoom(indexRoom, inputRoomName, inputFloor, inputAreaLength, inputAreaWidth, inputHeight);
        System.out.println("Success. The " + inputRoomName + " on the " + inputFloor + " floor with " + inputHeight + "m of height and " + inputAreaLength * inputAreaWidth + "m² was edited.");
    }

}
