package smarthome.model;

public class WashingMachine implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mCapacity;
    private ProgramList mProgramListInWM;

    public WashingMachine(DeviceType deviceType, int capacity) {
        mDeviceType = deviceType;
        mCapacity = capacity;
        mProgramListInWM = new ProgramList();
    }

    public void setCapacity(int newCapacity){
        mCapacity = newCapacity;
    }
    public int getCapacity(){
        return mCapacity;
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

        return result.toString();
    }

}
