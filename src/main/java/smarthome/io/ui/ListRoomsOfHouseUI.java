package smarthome.io.ui;

import smarthome.controller.CLI.ListRoomsOfHouseCTRL;

public class ListRoomsOfHouseUI {


    private final ListRoomsOfHouseCTRL mCtrlUS108;


    public ListRoomsOfHouseUI() {
        mCtrlUS108 = new ListRoomsOfHouseCTRL();

    }

    public void run() {
        if (mCtrlUS108.roomListSize () != 0) {
            System.out.println ("The Rooms of the house are:");
            System.out.println (mCtrlUS108.showListRoomInString ());
            UtilsUI.backToMenu();
        }
        else {
            System.out.println ("There are no rooms. Please add a room to the house");
            UtilsUI.backToMenu();
        }
    }
}




