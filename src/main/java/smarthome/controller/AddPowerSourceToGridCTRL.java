package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class AddPowerSourceToGridCTRL {

    private House mHouse;
    private HouseGridList mHGList;
    private PowerSourceList mPSList;
    private HouseGrid mHouseGrid;


    public AddPowerSourceToGridCTRL(House house, HouseGridList hgList, PowerSourceList psList) {
        mHouse = house;
        mHGList = hgList;
        mPSList = psList;
    }

    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHGListInHouse().getHouseGridList();
    }

    public  List<PowerSource> getPowerSourceListCtrl(HouseGrid houseGrid) {
        mHouseGrid = houseGrid;
        return mHouseGrid.getPSListInHG().getPSList();
    }

    public String showPowerSourceListInString(HouseGrid houseGrid) {
        List <PowerSource> list = getPowerSourceListCtrl(houseGrid);
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
            result.append(" kw.\n");
        }
        return result.toString();
    }


    public boolean addNewPSToGrid(int indexOfHG, String namePS, String typePS, double maxPower, double storageCapacity) {
        PowerSource ps = mHouse.getHGListInHouse().getHouseGridList().get(indexOfHG-1).getPSListInHG().newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return mHouse.getHGListInHouse().getHouseGridList().get(indexOfHG-1).getPSListInHG().addPS(ps);
    }
}
