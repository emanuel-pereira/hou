package smarthome.io.ui;

import smarthome.controller.GetGridTotalNominalPowerCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class GetGridTotalNominalPowerUI {

    Scanner read = new Scanner (System.in);

    private GetGridTotalNominalPowerCTRL mUS172CTRL;
    private int mIndexGrid;

    public GetGridTotalNominalPowerUI(House house) {
        mUS172CTRL = new GetGridTotalNominalPowerCTRL(house);
    }

    /**
     * Check if there is any house Grid before continuing
     */
    public void run() {
        if (mUS172CTRL.getHouseGridListSize () != 0) {
            this.checkIfRoomsExists();
        } else System.out.println ("Please ask the Administrator to create a House Grid");
    }

    /**
     * Check if there are any rooms before continuing
     */
    public void checkIfRoomsExists() {
        if (mUS172CTRL.getRoomListSize () != 0) {
            this.showGridList ();
        } else System.out.println ("Please ask the House Administrator to add Rooms");
    }

    /**
     * Show a list where the user can choose the grid that will return the total nominal power
     */
    public void showGridList() {
        while (true) {
            System.out.println ("Choose the Grid:");
            System.out.println(mUS172CTRL.showHouseGridListInString());
            mIndexGrid = read.nextInt ();
            if (mIndexGrid > mUS172CTRL.getHouseGridListSize())
              System.out.println(UtilsUI.insertValidOptionMsg());
            else break;
        }
        this.checkIfRoomsAttachToGrid ();
    }

    /**
     * Before showing the total nominal power this method checks if there are any rooms attached to the grid
     */
    public void checkIfRoomsAttachToGrid() {
        if (mUS172CTRL.getRoomListInAGridSize (mIndexGrid) != 0) {
            this.checkIfDevicesExists();
        } else System.out.println("Please ask the House Administrator to attach Rooms to a Grid ");
    }

    /**
     * Before showing the total nominal power this method checks if there are devices added to the rooms
     */
    public void checkIfDevicesExists() {
        if (mUS172CTRL.deviceListSizeInGridIsNotEmpty (mIndexGrid)) {
            this.getTotalNominalPowerInGrid();
        } else System.out.println("Please ask the House Administrator to add Devices to a Room");
    }

    /**
     * Shows the total nominal power of the chosen grid
     */
    public void getTotalNominalPowerInGrid() {
        System.out.println("The total Nominal Power of this Grid is: " + mUS172CTRL.getTotalNominalPowerInGrid(mIndexGrid));
    }

}


