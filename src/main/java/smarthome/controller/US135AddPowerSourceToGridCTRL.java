package smarthome.controller;

import smarthome.model.*;
import smarthome.model.Validations.NameValidations;

import java.util.List;

public class US135AddPowerSourceToGridCTRL {

    private House mHouse;
    private NameValidations mNameValidations;


    public US135AddPowerSourceToGridCTRL (House house) {
        mHouse = house;
        mNameValidations = new NameValidations();
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

    public HouseGrid getHouseGrid(int indexHG){
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
            result.append(ps.getMaxPower());
            result.append(" kw, Storage Capacity ");
            result.append(ps.getStorageCapacity());
            result.append(" kw.\n");
        }
        return result.toString();
    }

    public boolean alphanumericName(String inputName) {

        return mNameValidations.alphanumericName(inputName);
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
