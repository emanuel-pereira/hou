package smarthome.io.ui;

import smarthome.controller.ListRoomsOfHouseCTRL;
import smarthome.model.Room;

import java.util.List;
import java.util.Scanner;

public class ListRoomsOfHouseUI {


    private AddRoomToHouseUI roomToHouseUI;
    private List<Room> mRoomList;

    private ListRoomsOfHouseCTRL mCtrlUS108;


    public ListRoomsOfHouseUI(List<Room> inputList) {
        mRoomList = inputList;

        mCtrlUS108 = new ListRoomsOfHouseCTRL(inputList);

    }


    public void run() {

        Scanner read = new Scanner(System.in);


        System.out.println("The Rooms of the house are:");
        System.out.println(mCtrlUS108.showListRoomInString());


    }
}




