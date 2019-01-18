package smarthome.io.ui;

import smarthome.controller.US160GetDeviceListInGridByTypeCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US160GetDeviceListInGridByTypeUI {

    private US160GetDeviceListInGridByTypeCTRL mCtrl160;

    public US160GetDeviceListInGridByTypeUI(House house) {
        mCtrl160 = new US160GetDeviceListInGridByTypeCTRL(house);
    }

    public void getDeviceListInGrid() {
        if (mCtrl160.getHouseGridListCtrl().size() != 0) {
            System.out.println("Please select the House Grid to see the Devices that are connected to it:");
            System.out.println(mCtrl160.showHouseGridListInStringCtrl());
            int indexHG;
            while (true) {
                Scanner read1 = new Scanner(System.in);
                indexHG = read1.nextInt();
                if (indexHG > mCtrl160.getHouseGridListCtrl().size() || indexHG<=0)
                    System.out.println("Please insert a valid option.\n");
                if(mCtrl160.getListOfRoomsInGrid(indexHG).getRoomListSize() == 0) {
                    System.out.println("No Rooms were found in this grid, please add a Room to a Grid.");
                    break;
                }
                if(mCtrl160.getDeviceListInGridCtrl(indexHG).size() == 0) {
                    System.out.println("No Devices were found, please add a Device to a Room.");
                    break;
                }
                else{
                    System.out.println(mCtrl160.showGroupedDeviceListInGridString(indexHG));
                }
                    break;
            }
        }
        if(mCtrl160.getHouseGridListCtrl().size() == 0){
            System.out.println("No House Grids have been found, please create at least one.");
        }
        else{
            System.out.println("\n");
        }
    }
}
