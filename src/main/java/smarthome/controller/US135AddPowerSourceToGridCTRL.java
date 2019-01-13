package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class US135AddPowerSourceToGridCTRL {

    private  House mHouse;
    private HouseGridList mHGList;
    private PowerSourceList mPSList;
    private HouseGrid mHouseGrid;

    public US135AddPowerSourceToGridCTRL(House house,HouseGridList hgList){
        mHouse = house;
        mHGList = hgList;
    }

    public US135AddPowerSourceToGridCTRL (House house,HouseGridList hgList, PowerSourceList psList) {
        mHouse = house;
        mHGList = hgList;
        mPSList = psList;
    }


    /*public boolean newPS (String namePS, String typePS, double maxPower, double storageCapacity) {
        PowerSource ps = mHouseGrid.newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return mHouseGrid.addPS (ps);
    }*/

   /* public List<PowerSource> getPowerSourceList () {
        return mHouseGrid.getPSList();
    }*/
    public List<HouseGrid> getHouseGridList() {
        return mHGList.getHouseGridList();
    }

    public boolean addHouseGridCtrl(HouseGrid inputHouseGrid) {
        return mHouse.addHGinHGLinHouse(inputHouseGrid);
    }

    public String showHouseGridListInString() {
        List<HouseGrid> list = getHouseGridList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (HouseGrid houseGrid : list) {
            result.append(number++);
            result.append(element);
            result.append(houseGrid.getGridID());
            result.append(", Nominal Power: ");
            result.append(houseGrid.getContractedMaximumPower());
            result.append("\n");
        }
        return result.toString();
    }

    /*public  List<PowerSource> getPowerSourceListCtrl() {
        return mHouseGrid.getPSListHG();
    }

    public boolean createNewPowerSource (String namePS, String typePS,double maxPower, double storageCapacity, PowerSourceList psList){
        PowerSource ps = psList.newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return psList.addPSpslist(ps);
    }

    public String showPowerSourceListInString() {
        List <PowerSource> list = getPowerSourceListCtrl();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for(PowerSource ps : list) {
            result.append(number++);
            result.append(element);
            result.append (ps.getName());
            result.append(", ");
            result.append(ps.getTypePS());
            result.append(" type, Maximum Power ");
            result.append(ps.getMaxPower());
            result.append(" kw, Storage Capacity ");
            result.append(ps.getStorageCapacity());
            result.append(" kw. \n");
        }
        return result.toString();
    }*/

    public PowerSourceList getPSListInHGCtrl () {
        return mHouseGrid.getPSListInHG();
    }

    public boolean addNewPSToGrid(int indexOfHG, String namePS, String typePS, double maxPower, double storageCapacity) {
        //mHGList.getHouseGridListCtrl().get(indexOfHG-1);
        //HouseGrid grid = mHGList.getHouseGridListCtrl().get(indexOfHG-1);
        //PowerSource ps = mPSList.newPowerSource(namePS,typePS,maxPower,storageCapacity);
        //return mHGList.get(indexOfHG-1).addPS(new PowerSource(namePS,typePS,maxPower,storageCapacity));
        //mPSList.addPS(ps);//return mPSList.addPS( new PowerSource (namePS, typePS, maxPower, storageCapacity));
        PowerSource ps = mHouse.getHGListInHouse().getHouseGridList().get(indexOfHG-1).getPSListInHG().newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return mHouse.getHGListInHouse().getHouseGridList().get(indexOfHG-1).getPSListInHG().addPS(ps);
    }
}
