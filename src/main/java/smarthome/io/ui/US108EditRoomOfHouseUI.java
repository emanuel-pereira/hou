package smarthome.io.ui;

import smarthome.controller.US108EditRoomOfHouseCTRL;
import smarthome.model.Room;

import java.util.List;
import java.util.Scanner;

public class US108EditRoomOfHouseUI {


    private US105AddNewRoomToHouseUI roomToHouseUI;
    private List<Room> mRoomList;

    private US108EditRoomOfHouseCTRL mCtrlUS108;

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

        System.out.println("Success! Room selected.");

        System.out.println("Edit room name" + mRoom.getName());

        String inputRoomName = read.next();

        System.out.println("Success! Edit room floor" + mRoom.getFloor());

        int inputFloor = read.nextInt();

        System.out.println("Success! Edit room dimensions. Insert room length" + mRoom.getArea());

        double inputAreaLength = read.nextDouble();

        System.out.println("Success! Insert room width" + mRoom.getArea());

        double inputAreaWidth = read.nextDouble();

        System.out.println(mCtrlUS108.showListRoomInString());


        mCtrlUS108.setRoom(indexRoom, inputRoomName, inputFloor, inputAreaLength, inputAreaWidth);
    }

}
