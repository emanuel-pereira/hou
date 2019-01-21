/*
package smarthome.io.ui;

import smarthome.controller.AttachRoomToGridAndListCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class AttachRoomToGridAndListUI {
    private House mHouse;
    private AttachRoomToGridAndListCTRL mCtrl;

    public AttachRoomToGridAndListUI(House house) {
        mHouse = house;
        mCtrl = new AttachRoomToGridAndListCTRL (house);
    }

    Scanner read = new Scanner(System.in);

    public void run() {
        String insertValidOption = "Please insert a valid option \n.";
        String listRoomsAttachedToHousegridMsg="List of rooms attached to HouseGrid: ";
        int indexOfHouseGrid;
        int indexOfRoom;
        while (true) {
            if (!(mCtrl.getHouseGridListSize() == 0)) {
                if (!(mCtrl.getListOfRooms ().getRoomList ().isEmpty ())) {
                    String option;
                    while (true) {
                        //attach and list rooms attached to that grid
                        System.out.println("Choose an option from the list below:");
                        System.out.println("1 - Attach a Room to a House Grid");
                        //user selects grid and shows list of rooms attached to that grid
                        System.out.println("2 - List Rooms attached to a House Grid");
                        //detach and list rooms attached to that grid
                        System.out.println("3 - Detach a Room from a House Grid");
                        option = read.nextLine();

                        if (option.matches("1")) {
                            System.out.println("Choose a house grid from the list below to attach a room to it:");
                            System.out.println(mCtrl.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();

                            if (indexOfHouseGrid > mCtrl.getHouseGridListSize()) {
                                System.out.println(insertValidOption);
                                break;
                            }

                            if (!(mCtrl.getListOfRoomsWithoutHouseGrid().getRoomList().isEmpty())) {
                                System.out.println("Choose a room to attach from the list below:");
                                System.out.println(mCtrl.showRoomsWithoutHouseGridInStr());
                                indexOfRoom = read.nextInt();
                                read.nextLine();
                                mCtrl.attachRoomToHouseGrid(indexOfHouseGrid, indexOfRoom);
                                System.out.println("Room " + mHouse.getRoomList().get(indexOfRoom - 1).getName() + " was successfully attached to HouseGrid: " + mHouse.getHGListInHouse().get(indexOfHouseGrid - 1).getGridID() + " | Nominal Power: " + mHouse.getHGListInHouse().get(indexOfHouseGrid - 1).getContractedMaximumPower() + ".\n");
                                System.out.println(listRoomsAttachedToHousegridMsg);
                                System.out.println(mCtrl.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                                break;
                            }
                            break;
                        }

                        if (option.matches("2")) {
                            System.out.println("Choose a house grid from the list below to list the rooms attached to it:");
                            System.out.println(mCtrl.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();

                            if (indexOfHouseGrid > mCtrl.getHouseGridListSize()) {
                                System.out.println(insertValidOption);
                                break;
                            }
                            if (mCtrl.getListOfRoomsWithHouseGrid(indexOfHouseGrid).getRoomListSize()==0) {
                                System.out.println(listRoomsAttachedToHousegridMsg + mHouse.getHGListInHouse().get(indexOfHouseGrid - 1).getGridID() + " is empty. Please attach rooms first.\n");
                                break;
                            }
                            System.out.println(listRoomsAttachedToHousegridMsg);
                            System.out.println(mCtrl.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                            break;
                        }

                        if (option.matches("3")) {
                            System.out.println("Choose a house grid from the list below to detach a room from it:");
                            System.out.println(mCtrl.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();
                            if (indexOfHouseGrid > mCtrl.getHouseGridListSize()) {
                                System.out.println(insertValidOption);
                                break;
                            }
                            System.out.println("Choose a room to detach from the list below:");
                            System.out.println(mCtrl.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                            indexOfRoom = read.nextInt();
                            read.nextLine();
                            mCtrl.detachRoomFromHouseGrid(indexOfHouseGrid, indexOfRoom);
                            System.out.println("The room was successfully detached from housegrid " + mHouse.getHGListInHouse().get(indexOfHouseGrid - 1).getGridID() + ".\n");
                            System.out.println(mCtrl.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                            break;
                        }
                        break;
                    }
                    break;
                }
                System.out.println("List of Rooms is empty. Please create a new room in US105.");
                break;
            }
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            break;
        }
    }
}

*/
