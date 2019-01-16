package smarthome.model;

public class Lamp implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mLuminousFlux;

    public Lamp(DeviceType deviceType, int luminousFlux) {
        this.mDeviceType = deviceType;
        this.mLuminousFlux = luminousFlux;
    }
    public void setLuminousFlux(int newLuminousFlux) {
        mLuminousFlux=newLuminousFlux;
    }
    public int getLuminousFlux(){
        return mLuminousFlux;
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
        result.append("4 - Luminous Flux : " + this.mLuminousFlux);
        return result.toString();
    }
}

