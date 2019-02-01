package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class PowerSourceList {
    private List<PowerSource> mPSList;

    public PowerSourceList () {
        mPSList = new ArrayList<>();
    }


    public PowerSource newPowerSource (String inputName, String inputType, double inputMaxPower, double inputStorageCapacity){
        return new PowerSource(inputName,inputType,inputMaxPower,inputStorageCapacity);
    }

    public boolean addPS(PowerSource inputPS) {
        if (mPSList.contains(inputPS))
            return false;
        else {
            mPSList.add(inputPS);
            return true;
        }
    }

    public List<PowerSource> getPSList() {
        return mPSList;
    }
}
