package smarthome.model;

public class HouseGrid {
    //The electrical network of the house
    private Double mContractedMaximumPower;

    public HouseGrid(double inputContractedMaximumPower){
        mContractedMaximumPower=inputContractedMaximumPower;
    }
    public Double getmContractedMaximumPower() {
        return mContractedMaximumPower;
    }

    public void setmContractedMaximumPower(Double ContractedMaximumPower) {
        this.mContractedMaximumPower = ContractedMaximumPower;
    }
}
