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

    @Override
    public String getType() {
        return mDeviceType.getType();
    }

    @Override
    public String getTypeFromIndex(int index) {
        return mDeviceType.getTypeFromIndex(index);
    }

}
