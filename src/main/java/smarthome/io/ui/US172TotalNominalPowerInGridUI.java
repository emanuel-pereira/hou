package smarthome.io.ui;

import smarthome.controller.US172TotalNominalPowerInGridCTRL;
import smarthome.model.House;
import smarthome.model.HouseGridList;
import smarthome.model.RoomList;

import java.util.Scanner;

public class US172TotalNominalPowerInGridUI {

    Scanner read = new Scanner (System.in);

    private US172TotalNominalPowerInGridCTRL mUS172CTRL;

    public US172TotalNominalPowerInGridUI(House house) {
        mUS172CTRL = new US172TotalNominalPowerInGridCTRL (house);
    }

    public int indexGrid;

    /**
     * Check if there is any house Grid before continuing
     */
    public void run() {
        if (!mUS172CTRL. getHouseGridList ().getHouseGridList ().isEmpty ()) {
            this.checkIfRoomsExists ();
        } else System.out.println ("Please ask the Administrator to create a House Grid");
    }

    /**
     * Check if there are any rooms before continuing
     */
    public void checkIfRoomsExists() {
        if (!mUS172CTRL.getRoomList ().getRoomList ().isEmpty ()) {
            this.showGridList ();
        } else System.out.println ("Please ask the House Administrator to add Rooms");
    }

    /**
     * Show a list where teh user can choose th grid that we want the total nominal power
     */
    public void showGridList() {
        while (true) {
            System.out.println ("Choose the Grid:");
            System.out.println (mUS172CTRL.showHouseGridListInString ());
            indexGrid = read.nextInt ();
            if (indexGrid > mUS172CTRL.getHouseGridList ().getHouseGridList ().size ())
                System.out.println ("Please insert a valid option \n");
            else break;
        }
        this.checkIfRoomsAttachToGrid ();
    }

    /**
     * Before showing the total nominal power this method checks if there are any rooms attached to the grid
     */
    public void checkIfRoomsAttachToGrid() {
        if (!mUS172CTRL.getListOfRoomsWithThisGrid (indexGrid).getRoomList ().isEmpty ()) {
            this.checkIfDevicesExists ();
        } else System.out.println ("Please ask the House Administrator to attach Rooms to a Grid ");
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms
     */
    public void checkIfDevicesExists() {
        if (!mUS172CTRL.getDeviceList (indexGrid).getDeviceList ().isEmpty ()) {
            this.getTotalNominalPowerInGrid ();
        } else System.out.println ("Please ask the House Administrator to add Devices to a Room");
    }

    /**
     * Shows the total nominal power of the chosen grid
     */
    public void getTotalNominalPowerInGrid() {
        System.out.println ("The total Nominal Power of this Grid is: " + mUS172CTRL.getTotalNominalPowerInGrid (indexGrid));

    }


}
