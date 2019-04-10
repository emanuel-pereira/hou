package smarthome.controller;

import smarthome.model.*;

import static smarthome.model.House.getGridListInHouse;

public class GetDeviceListInGridByTypeCTRL {


    public GetDeviceListInGridByTypeCTRL () {

    }

    public HouseGridList getHouseGridListCtrl() {
        return getGridListInHouse();
    }

    public int getHGListSizeCtrl() {
        return getGridListInHouse().getSize();
    }

    public String showHouseGridListInStringCtrl() {
        return this.getHouseGridListCtrl().showHouseGridListInString();
    }

    public HouseGrid getHouseGrid(int indexHG){
        return this.getHouseGridListCtrl().get(indexHG-1);
    }

        public String getHouseGridName(int indexHG){
            return this.getHouseGrid(indexHG).getMeteredDesignation();
        }

    public RoomList getListOfRoomsInGrid(int indexHG) {
        return this.getHouseGrid(indexHG).getRoomListInAGrid();
    }

    public int getRoomListSizeCtrl(int indexHG) {
        return this.getListOfRoomsInGrid(indexHG).getRoomListSize();
    }

    public DeviceList getDeviceListInGridCtrl(int indexHG) {
        return this.getHouseGrid(indexHG).getDeviceListInGrid();
    }

    public int getDeviceListInGridSizeCtrl(int indexHG){
        return this.getDeviceListInGridCtrl(indexHG).size();
    }

    public DeviceList deviceListGroupByTypeCtrl(int indexHG) {
        return this.getHouseGrid(indexHG).getDeviceListInGridGroupBy();

    }

    public String showGroupedDeviceListInGridString (int indexHG) {
        return this.getHouseGrid(indexHG).showGroupedDeviceListInGridString();
    }
}
