package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class WashingMachine implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mCapacity;
    private ProgramList mProgramListInWM;

    public WashingMachine(DeviceType deviceType, int capacity) {
        this.mDeviceType=deviceType;
        this.mCapacity = capacity;
        this.mProgramListInWM = new ProgramList();
    }

    public void setCapacity(int newCapacity) {
        mCapacity = newCapacity;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String Capacity = "4 - Washing Machine Capacity : " + this.mCapacity;
        result.add(Capacity);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String Capacity = "4 - Washing Machine Capacity : " + this.mCapacity;
        if (attribute.equals(Capacity))
            setCapacity(parseInt(newValue));
    }

    /*public ProgramList getmProgramListInDW() {
        return mProgramListInWM;
    }*/

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }
}
