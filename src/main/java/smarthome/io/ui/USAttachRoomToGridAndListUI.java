package smarthome.io.ui;

import smarthome.controller.USAttachRoomToGridAndListCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class USAttachRoomToGridAndListUI {
    private House mHouse;
    private USAttachRoomToGridAndListCTRL mCtrlUS147;

    public USAttachRoomToGridAndListUI(House house) {
        mHouse = house;
        mCtrlUS147 = new USAttachRoomToGridAndListCTRL(house);
    }

    Scanner read = new Scanner(System.in);

    public void run() {
        String insertValidOption = "Please insert a valid option \n.";
        String listRoomsAttachedToHousegridMsg="List of rooms attached to HouseGrid: ";
        int indexOfHouseGrid;
        int indexOfRoom;
        while (true) {
            if (!(mCtrlUS147.getHouseGridList().isEmpty())) {
                if (!(mCtrlUS147.getListOfRooms().isEmpty())) {

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
                            System.out.println(mCtrlUS147.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();

                            if (indexOfHouseGrid > mCtrlUS147.getHouseGridList().size()) {
                                System.out.println(insertValidOption);
                                break;
                            }

                            if (!(mCtrlUS147.getListOfRoomsWithoutHouseGrid().isEmpty())) {
                                System.out.println("Choose a room to attach from the list below:");
                                System.out.println(mCtrlUS147.showRoomsWithoutHouseGridInStr());
                                indexOfRoom = read.nextInt();
                                read.nextLine();
                                mCtrlUS147.attachRoomToHouseGrid(indexOfHouseGrid, indexOfRoom);
                                System.out.println("Room " + mHouse.getRoomList().get(indexOfRoom - 1).getName() + " was successfully attached to HouseGrid: " + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getGridID() + " | Nominal Power: " + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getContractedMaximumPower() + ".\n");
                                System.out.println(listRoomsAttachedToHousegridMsg);
                                System.out.println(mCtrlUS147.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                                break;
                            }
                            break;
                        }

                        if (option.matches("2")) {
                            System.out.println("Choose a house grid from the list below to list the rooms attached to it:");
                            System.out.println(mCtrlUS147.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();

                            if (indexOfHouseGrid > mCtrlUS147.getHouseGridList().size()) {
                                System.out.println(insertValidOption);
                                break;
                            }
                            if (!(mCtrlUS147.getListOfRoomsWithHouseGrid(indexOfHouseGrid).isEmpty())) {
                                System.out.println(listRoomsAttachedToHousegridMsg + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getGridID() + " is empty. Please attach rooms first.\n");
                                break;
                            }
                            System.out.println(listRoomsAttachedToHousegridMsg);
                            System.out.println(mCtrlUS147.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                            break;
                        }

                        if (option.matches("3")) {
                            System.out.println("Choose a house grid from the list below to detach a room from it:");
                            System.out.println(mCtrlUS147.showHouseGridListInString());
                            indexOfHouseGrid = read.nextInt();
                            read.nextLine();
                            if (indexOfHouseGrid > mCtrlUS147.getHouseGridList().size()) {
                                System.out.println(insertValidOption);
                                break;
                            }
                            System.out.println("Choose a room to detach from the list below:");
                            System.out.println(mCtrlUS147.showRoomsWithHouseGridInStr(indexOfHouseGrid));
                            indexOfRoom = read.nextInt();
                            read.nextLine();
                            mCtrlUS147.detachRoomFromHouseGrid(indexOfHouseGrid, indexOfRoom);
                            System.out.println("The room was successfully detached from housegrid " + mHouse.getHouseGridList().get(indexOfHouseGrid - 1).getGridID() + ".\n");
                            break;
                        }
                        break;
                    }
                    break;
                }
                System.out.println("There are no rooms without HouseGrid. Please detach one first.");
                break;
            }
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid in US130.");
            break;
        }
    }
}

