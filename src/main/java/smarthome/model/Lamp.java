package smarthome.model;

public class Lamp implements DeviceSpecs {
    private int mLuminousFlux;

    public Lamp(int luminousFlux) {
        this.mLuminousFlux = luminousFlux;
    }
    public void setLuminousFlux(int newLuminousFlux) {
        mLuminousFlux=newLuminousFlux;
    }
    public int getLuminousFlux(){
        return mLuminousFlux;
    }
    public String showDeviceSpecsListAttributesInString() {
        StringBuilder result = new StringBuilder();
        result.append("4 - Luminous Flux : " + this.mLuminousFlux);
        return result.toString();
    }
}

