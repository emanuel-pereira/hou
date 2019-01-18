package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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
    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String Capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        result.add(Capacity);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String Capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        if (attribute.equals(Capacity))
            setCapacity(parseInt(newValue));
    }
}
