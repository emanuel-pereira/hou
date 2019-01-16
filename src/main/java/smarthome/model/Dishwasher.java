package smarthome.model;

public class Dishwasher implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mCapacity;
    private double mEnergyConsumption;
    private ProgramList mProgramListInDW;


    public Dishwasher(DeviceType deviceType, int capacity) {
        mDeviceType = deviceType;
        mCapacity = capacity;
        mProgramListInDW = new ProgramList();
    }

    public void setCapacity(int newCapacity) {
        mCapacity = newCapacity;
    }

    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
    }

    public String showDeviceSpecsListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("4 - Capacity : " + this.mCapacity);
        result.append("\n");
        result.append("6 - Program List : " + this.mProgramListInDW.showProgramListInString());

        return result.toString();
    }

    public int getCapacity() {
        return mCapacity;
    }

    public double getEnergyConsumption() {
        return mEnergyConsumption;
    }

    public ProgramList getmProgramListInDW() {
        return mProgramListInDW;
    }
}
