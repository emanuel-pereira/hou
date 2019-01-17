package smarthome.model;

public class Dishwasher implements DeviceSpecs {
    private int mCapacity;
    private double mEnergyConsumption;
    private ProgramList mProgramListInDW;


    public Dishwasher(int capacity) {
        mCapacity = capacity;
        mProgramListInDW = new ProgramList();
    }

    public void setCapacity(int newCapacity) {

        mCapacity = newCapacity;
    }

    public int getCapacity() {

        return mCapacity;
    }

    public String showDeviceSpecsListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("4 - Dishwater Capacity : " + this.mCapacity);
        return result.toString();
    }
}
