package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Dishwasher implements DeviceSpecs {
    private DeviceType mDeviceType;
    private int mCapacity;
    private double mEnergyConsumption;
    private ProgramList mProgramListInDW;




    public Dishwasher(DeviceType deviceType,int capacity) {
        mDeviceType=deviceType;
        mCapacity = capacity;
        mProgramListInDW = new ProgramList();
    }

    public void setCapacity(int newCapacity) {

        mCapacity = newCapacity;
    }

    public int getCapacity() {

        return mCapacity;
    }

    @Override
    public DeviceType getType() {
        return mDeviceType;
    }

    public List<String> getDeviceAttributesInString() {
        List<String> result = new ArrayList<>();
        String capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        //ProgramList programList = "5 - ";

        result.add(capacity);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        String Capacity = "4 - Dishwater Capacity : " + this.mCapacity;
        if (attribute.equals(Capacity))
            setCapacity(parseInt(newValue));
    }

   /* public ProgramList getmProgramListInDW() {
        return mProgramListInDW;
    }
*/
}
