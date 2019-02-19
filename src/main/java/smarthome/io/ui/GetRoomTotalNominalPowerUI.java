package smarthome.io.ui;

import smarthome.controller.GetRoomTotalNominalPowerCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class GetRoomTotalNominalPowerUI {
    private GetRoomTotalNominalPowerCTRL mCtrlUS230;
    Scanner read = new Scanner(System.in);


    public GetRoomTotalNominalPowerUI(House house) {
        mCtrlUS230 = new GetRoomTotalNominalPowerCTRL(house);
    }

    public void showTotalNominalPowerRoom() {
        int indexRoom;

        if (mCtrlUS230.getRoomListSize() == 0) {
            System.out.println("No Rooms have been found, please create at least one.");

        } else {
            System.out.println("Please select the Room from which you wish to know the Total Nominal Power:");
            System.out.println(mCtrlUS230.showListRoomInString());

            Scanner read1 = new Scanner(System.in);
            indexRoom = read1.nextInt();

            // Verify if indexRoom is valid and ask for a valid input if not
            while (indexRoom > mCtrlUS230.getRoomListSize() || indexRoom <= 0) {
                UtilsUI.printLnInsertValidOptionMsg();
                indexRoom = read1.nextInt();
            }

            if (mCtrlUS230.getDeviceListSizeInRoom(indexRoom) == 0) {
                System.out.println("No devices were found in the selected room.");

            } else {
                String roomName = mCtrlUS230.getRoomNameCtrl(indexRoom);
                double totalNP = mCtrlUS230.getNominalPowerRoomCtrl(indexRoom);
                System.out.println("The Total Nominal Power of " + roomName + " is of " + totalNP + " kw.\n");
            }
        }
    }
}
