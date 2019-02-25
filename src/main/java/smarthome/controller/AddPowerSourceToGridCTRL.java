package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class AddPowerSourceToGridCTRL {

    private House mHouse;


    public AddPowerSourceToGridCTRL(House house) {
        mHouse = house;
    }

    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHGListInHouse().getHouseGridList();
    }

    public int getHGListSizeCtrl() {
        return mHouse.getHGListInHouse().getSize();
    }

    public String getHGListInStringCtrl (){
        return mHouse.getHGListInHouse().showHouseGridListInString();
    }

    private HouseGrid getHouseGrid(int indexHG){
        return this.getHouseGridList().get(indexHG-1);
    }

    public String getHouseGridName(int indexHG){
        return this.getHouseGrid(indexHG).getGridID();
    }

    public  List<PowerSource> getPowerSourceListCtrl(int indexHG) {
        return this.getHouseGrid(indexHG).getPSListInHG().getPSList();
    }

    public String showPowerSourceListInString(int indexHG) {
        return this.getHouseGrid(indexHG).getPSListInHG().showPowerSourceListInString();
    }

    public int getPSListSizeCtrl (int indexOfHG) {
        return getHouseGridList().get(indexOfHG-1).getPSListInHG().getPSListSize();
    }

    public boolean addNewPSToGrid(int indexOfHG, String namePS, String typePS, double maxPower, double storageCapacity) {
        PowerSourceList psListOfHG = this.getHouseGrid(indexOfHG).getPSListInHG();
        PowerSource ps = psListOfHG.newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return this.getHouseGrid(indexOfHG).getPSListInHG().addPS(ps);
    }
}
