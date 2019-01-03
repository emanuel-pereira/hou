package smarthome.model;

public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower;
    private String mDesignation;

    public HouseGrid(double inputContractedMaximumPower) {
        mContractedMaximumPower = inputContractedMaximumPower;
    }

    /*public HouseGrid(double inputContractedMaximumPower, String Designation) {
        mContractedMaximumPower = inputContractedMaximumPower;
        mDesignation = Designation;
    }*/

    public Double getmContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setmContractedMaximumPower(Double ContractedMaximumPower) {
        this.mContractedMaximumPower = ContractedMaximumPower;
    }

    public String getGridID() {
        return mDesignation;
    }
}
