package smarthome.model;

public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower = Double.NaN;
    private String mDesignation;

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
        return mDesignation;
    }
}
