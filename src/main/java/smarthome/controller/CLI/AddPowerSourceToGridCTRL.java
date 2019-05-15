package smarthome.controller.CLI;

import smarthome.model.HouseGrid;
import smarthome.model.PowerSource;
import smarthome.model.PowerSourceList;

import java.util.List;

import static smarthome.model.House.getGridListInHouse;


public class AddPowerSourceToGridCTRL {

    public AddPowerSourceToGridCTRL() {
        //Controller public constructor
    }

    public List<HouseGrid> getHouseGridList() {
        return getGridListInHouse().getHouseGridList();
    }

    public int getHGListSizeCtrl() {
        return getGridListInHouse().getSize();
    }

    public String getHGListInStringCtrl (){
        return getGridListInHouse().showHouseGridListInString();
    }

    private HouseGrid getHouseGrid(int indexHG){
        return this.getHouseGridList().get(indexHG-1);
    }

    public String getHouseGridName(int indexHG){
        return this.getHouseGrid(indexHG).getMeteredDesignation();
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
