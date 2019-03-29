package smarthome.io.ui;

import smarthome.controller.GetDeviceListInGridByTypeCTRL;
import smarthome.model.House;

public class GetDeviceListInGridByTypeUI {

    private GetDeviceListInGridByTypeCTRL ctrl;
    private int indexOfHG;


    public GetDeviceListInGridByTypeUI(House house) {
        this.ctrl = new GetDeviceListInGridByTypeCTRL(house);
    }

    public void checkIfHGListIsEmpty() {
        if (this.ctrl.getHGListSizeCtrl() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid first.");
            return;
        }
        this.selectHG();
    }

    private void selectHG() {
        this.showAndSelectHG();
        this.listRoomsOfHG();
    }

    private void listRoomsOfHG() {
        if (hgRoomListIsEmpty())
            UtilsUI.backToMenu();
        else{
            this.listDevicesInHG();
        }

    }

    private void listDevicesInHG(){
        if(hgDeviceListIsEmpty())
            UtilsUI.backToMenu();
        else{
            showDevicesInHGGroupedBy();
        }
    }

    private void showDevicesInHGGroupedBy() {
        System.out.println("List of Devices attached to " + this.ctrl.getHouseGridName(this.indexOfHG) + ":");
        System.out.println(this.ctrl.showGroupedDeviceListInGridString(this.indexOfHG));
        UtilsUI.backToMenu();
    }

    private boolean hgDeviceListIsEmpty() {
        if (this.ctrl.getDeviceListInGridSizeCtrl(this.indexOfHG) == 0) {
            System.out.println("List of Devices in HouseGrid is empty. Please attach one first.");
            return true;
        }
        return false;
    }

    private boolean hgRoomListIsEmpty() {
        if (this.ctrl.getRoomListSizeCtrl(this.indexOfHG) == 0) {
            System.out.println("List of Power Sources in " + this.ctrl.getHouseGridName(this.indexOfHG) + " is empty. Please add one first.");
            return true;
        }
        return false;
    }

    public void showAndSelectHG (){
        System.out.println("Choose a house grid from the list below to add a Power Source to it:");
        System.out.println(this.ctrl.showHouseGridListInStringCtrl());
        this.indexOfHG = UtilsUI.requestIntegerInInterval(1, this.ctrl.getHGListSizeCtrl(),"Please insert a valid house grid index");
    }

}
