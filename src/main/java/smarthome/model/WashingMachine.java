package smarthome.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class WashingMachine implements DeviceSpecs {
    private int mCapacity;
    private DeviceType mDeviceType;

    private String capacity = "Washing Machine Capacity";


    public WashingMachine() {
    }

    public WashingMachine(int capacity) {
        this.mCapacity = capacity;
    }

    public void setCapacity(int newCapacity) {
        mCapacity = newCapacity;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public List<String> getAttributesNames() {
        List<String> result = new ArrayList<>();
        result.add(capacity);
        return result;
    }

    public void setAttributeValue(String attribute, String newValue) {
        if (attribute.contains(capacity))
            setCapacity(parseInt(newValue));

    }

    public String showDeviceAttributeNamesAndValues() {
        StringBuilder result = new StringBuilder();
        int number = 3;
        for (String s : getAttributesNames()) {
            result.append(number);
            result.append(" - ");
            if (s.contains(capacity))
                result.append(s.concat(" : " + this.getCapacity()));
            result.append("\n");
            number++;
        }
        return result.toString();
    }
    @Override
    public double getEnergyConsumption() {
        return 0;
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }
    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }
}