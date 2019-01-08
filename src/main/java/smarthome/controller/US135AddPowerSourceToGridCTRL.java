package smarthome.controller;

import smarthome.model.House;
import smarthome.model.PowerSource;
import smarthome.model.HouseGrid;

import java.util.List;

public class US135AddPowerSourceToGridCTRL {

    private House mHouse;
    private HouseGrid mHouseGrid;

    public US135AddPowerSourceToGridCTRL(House house) {
        mHouse = house;
    }

    /*public boolean newPS (String namePS, String typePS, double maxPower, double storageCapacity) {
        PowerSource ps = mHouseGrid.newPowerSource(namePS,typePS,maxPower,storageCapacity);
        return mHouseGrid.addPS (ps);
    }*/

   /* public List<PowerSource> getPowerSourceList () {
        return mHouseGrid.getPSList();
    }*/
    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHouseGridList();
    }

    public String showHouseGridListInString() {
        List<HouseGrid> list = mHouse.getHouseGridList();
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

    public boolean addNewPS(int indexOfHG, String namePS, String typePS, double maxPower, double storageCapacity) {
        HouseGrid houseGrid = mHouse.getHouseGridList().get(indexOfHG-1);
        return houseGrid.addPS(new PowerSource(namePS,typePS,maxPower,storageCapacity));
    }
}
