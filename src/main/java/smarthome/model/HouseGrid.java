package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower = Double.NaN;
    private String mDesignation;
    private PowerSource mPowerSource;
    private List<PowerSource> mPowerSourceList = new ArrayList<>();
    private HouseGrid mHouseGrid;

    public HouseGrid() {
    }

    public HouseGrid(double inputContractedMaximumPower) {

        if (valueIsPositive(inputContractedMaximumPower))
            mContractedMaximumPower = inputContractedMaximumPower;

    }

    public HouseGrid(double inputContractedMaximumPower, String designation) {
        if (valueIsPositive(inputContractedMaximumPower)) {
            mContractedMaximumPower = inputContractedMaximumPower;
            mDesignation = designation;

        }
    }



    public Double getContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setContractedMaximumPower(Double contractedMaximumPower) {
        if (valueIsPositive(contractedMaximumPower))
            this.mContractedMaximumPower = contractedMaximumPower;
    }

    private boolean valueIsPositive(Double contractedMaximumPower) {
        return (contractedMaximumPower > 0);
    }

    public String getGridID() {
        return mDesignation; }


    public PowerSource newPowerSource (String inputName, String inputType, double inputMaxPower, double inputStorageCapacity){
        return mPowerSource = new PowerSource(inputName,inputType,inputMaxPower,inputStorageCapacity);
    }

    public boolean addPS(PowerSource inputPS) {
        if (mPowerSourceList.contains(inputPS))
        return false;
        else {
            mPowerSourceList.add(inputPS);
            return true;
        }
    }

    public PowerSource getPowerSource (){
        return mPowerSource;
    }

    public List<PowerSource> getPSList() {
        return mPowerSourceList;
    }
}
