package smarthome.io.ui;

import smarthome.controller.GetDeviceListInGridByTypeCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class GetDeviceListInGridByTypeUI {

    private GetDeviceListInGridByTypeCTRL mCtrl;
    private Scanner mRead = new Scanner(System.in);
    private int mIndexOfHG;


    public GetDeviceListInGridByTypeUI(House house) {
        mCtrl = new GetDeviceListInGridByTypeCTRL(house);
    }

    public void checkIfHGListIsEmpty() {
        if (mCtrl.getHGListSizeCtrl() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid first.");
            return;
        }
        this.selectHG();
    }

    private void selectHG() {
        this.showAndSelectHG();
        if(!isNotValidIndexOfHG()){
            this.listRoomsOfHG();}
    }

    private void listRoomsOfHG() {
        if (hgRoomListIsEmpty())
            return;
        else{this.listDevicesInHG();}
    }

    private void listDevicesInHG(){
        if(hgDeviceListIsEmpty())
            return;
        else{showDevicesInHGGroupedBy();}
    }

    private void showDevicesInHGGroupedBy() {
        System.out.println("List of Devices attached to " + mCtrl.getHouseGridName(mIndexOfHG) + ":");
        System.out.println(mCtrl.showGroupedDeviceListInGridString(mIndexOfHG));
        UtilsUI.backToMenu();
    }

    private boolean hgDeviceListIsEmpty() {
        if (mCtrl.getDeviceListInGridSizeCtrl(mIndexOfHG) == 0) {
            System.out.println("List of Devices in HouseGrid is empty. Please attach one first.");
            return true;
        }
        return false;
    }

    private boolean hgRoomListIsEmpty() {
        if (mCtrl.getRoomListSizeCtrl(mIndexOfHG) == 0) {
            System.out.println("List of rooms in HouseGrid is empty. Please attach one first.");
            return true;
        }
        return false;
    }

    public void showAndSelectHG (){
        System.out.println("Choose a house grid from the list below to see the Devices attached to it:");
        System.out.println(mCtrl.showHouseGridListInStringCtrl());
        mIndexOfHG = mRead.nextInt();
        mRead.nextLine();
    }

    public boolean isNotValidIndexOfHG() {
        //boolean x=true;
        while(mIndexOfHG > mCtrl.getHGListSizeCtrl() || mIndexOfHG <= 0) {
                UtilsUI.printLnInsertValidOption();
                System.out.println("--");
                this.showAndSelectHG();
        }
        return false;
    }
}