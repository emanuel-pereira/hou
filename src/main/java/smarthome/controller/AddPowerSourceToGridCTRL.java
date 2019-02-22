package smarthome.controller;

import smarthome.io.ui.UtilsUI;
import smarthome.model.*;
import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

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
        List <PowerSource> list = getPowerSourceListCtrl(indexHG);
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
            result.append(UtilsUI.formatDecimal(ps.getMaxPower(),2));
            result.append(" kw, Storage Capacity ");
            result.append(UtilsUI.formatDecimal(ps.getStorageCapacity(),2));
            result.append(" kw.\n");
        }
        return result.toString();
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
