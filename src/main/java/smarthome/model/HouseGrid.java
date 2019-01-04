package smarthome.model;

public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower = Double.NaN;
    private String mDesignation;

    public HouseGrid(double inputContractedMaximumPower) {
        if (valueIsPositive(inputContractedMaximumPower))
            mContractedMaximumPower = inputContractedMaximumPower;
    }

    public HouseGrid(double inputContractedMaximumPower, String Designation) {
        if (valueIsPositive(inputContractedMaximumPower)) {
            mContractedMaximumPower = inputContractedMaximumPower;
            mDesignation = Designation;
        }
    }

    public Double getContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setContractedMaximumPower(Double ContractedMaximumPower) {
        if (valueIsPositive(ContractedMaximumPower))
            this.mContractedMaximumPower = ContractedMaximumPower;
    }

    private boolean valueIsPositive(Double ContractedMaximumPower) {
        return (ContractedMaximumPower > 0);
    }

    public String getGridID() {
        return mDesignation;
    }
}
