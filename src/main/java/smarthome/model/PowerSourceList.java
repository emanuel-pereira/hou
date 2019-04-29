package smarthome.model;

import smarthome.io.ui.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceList {
    private final List<PowerSource> psList;

    public PowerSourceList () {
        this.psList = new ArrayList<>();
    }


    public PowerSource newPowerSource (String inputName, String inputType, double inputMaxPower, double inputStorageCapacity){
        return new PowerSource(inputName,inputType,inputMaxPower,inputStorageCapacity);
    }

    public boolean addPS(PowerSource inputPS) {
        if (this.psList.contains(inputPS))
            return false;
        else {
            this.psList.add(inputPS);
            return true;
        }
    }

    public List<PowerSource> getPSList() {
        return this.psList;
    }

    public int getPSListSize() {
        return this.psList.size();
    }

    public String showPowerSourceListInString() {
        List <PowerSource> list = getPSList();
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
}
