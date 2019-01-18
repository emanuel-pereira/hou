package smarthome.io.ui;

import smarthome.controller.US108ListTheRoomsOfHouseCTRL;
import smarthome.model.Room;

import java.util.List;
import java.util.Scanner;

public class US108ListTheRoomsOfHouseUI {


    private US105AddNewRoomToHouseUI roomToHouseUI;
    private List<Room> mRoomList;

    private US108ListTheRoomsOfHouseCTRL mCtrlUS108;


    public US108ListTheRoomsOfHouseUI(List<Room> inputList) {
        mRoomList = inputList;

        mCtrlUS108 = new US108ListTheRoomsOfHouseCTRL(inputList);

    }


    public void run() {

        Scanner read = new Scanner(System.in);


        System.out.println("The Rooms of the house are:");
        System.out.println(mCtrlUS108.showListRoomInString());


    }
}




