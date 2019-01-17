package smarthome.io.ui;

import smarthome.controller.US230TotalNominalPowerRoomCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US230TotalNominalPowerRoomUI {
    private House mHouse;
    private US230TotalNominalPowerRoomCTRL mCtrlUS230;

    Scanner read = new Scanner(System.in);

    public US230TotalNominalPowerRoomUI(House house) {
        mHouse = house;
        mCtrlUS230 = new US230TotalNominalPowerRoomCTRL(house);
    }

    public void showTotalNominalPowerRoom () {
        int indexRoom;
        if (mCtrlUS230.getRoomListCtrl().size() != 0) {
            System.out.println("Please select the Room from which you wish to know the Total Nominal Power:");
            System.out.println(mCtrlUS230.showListRoomInString());
            while (true) {
                Scanner read1 = new Scanner(System.in);
                indexRoom = read1.nextInt();
                String roomName = mCtrlUS230.getRoomNameCtrl(indexRoom);
                if(indexRoom > mCtrlUS230.getRoomListCtrl().size())
                    System.out.println("Please insert a valid option.\n");
                if(mCtrlUS230.getDeviceListInRoomCtrl(indexRoom).size() == 0){
                    System.out.println("No Devices were found, please add a Device to a Room");
                    break;
                }
                else {
                    double totalNP = mCtrlUS230.getNominalPowerRoomCtrl(indexRoom);
                    System.out.println("The Total Nominal Power of " +roomName+ " is of " +totalNP+ " kw.\n");
                }break;
            }
        }
        if(mCtrlUS230.getRoomListCtrl().size() == 0) {
            System.out.println("No Rooms have been found, please create at least one");
        }
        else{
            System.out.println("\n");
        }

    }
}
