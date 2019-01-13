package smarthome.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower = Double.NaN;
    private String mDesignation;
    private PowerSource mPowerSource;
    private PowerSourceList mPSListInHG;
    private HouseGrid mHouseGrid;

    public HouseGrid() {
    }

    public HouseGrid(double inputContractedMaximumPower) {

        if (valueIsPositive(inputContractedMaximumPower))
            mContractedMaximumPower = inputContractedMaximumPower;

    }

    public HouseGrid(String designation, double inputContractedMaximumPower) {
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


    public PowerSourceList getPSListInHG () {
        return mPSListInHG;
    }
}
