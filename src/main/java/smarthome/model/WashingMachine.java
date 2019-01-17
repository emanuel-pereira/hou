package smarthome.model;

public class WashingMachine implements DeviceSpecs {
    private int mCapacity;
    private ProgramList mProgramListInWM;

    public WashingMachine(int capacity) {
        mCapacity = capacity;
        mProgramListInWM = new ProgramList();
    }

    public void setCapacity(int newCapacity) {
        mCapacity = newCapacity;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public String showDeviceSpecsListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("4 - Capacity : " + this.mCapacity);

        return result.toString();
    }
}
