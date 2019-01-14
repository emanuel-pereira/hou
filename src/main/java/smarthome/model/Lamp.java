package smarthome.model;

public class Lamp implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mLuminousFlux;

    public Lamp(DeviceType deviceType, int luminousFlux){
        this.mDeviceType=deviceType;
        this.mLuminousFlux=luminousFlux;
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
