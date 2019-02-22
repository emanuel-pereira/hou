package smarthome.model;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Dishwasher implements DeviceSpecs {
    private int mCapacity;
    private DeviceType mDeviceType;

    public Dishwasher() {
    }

    public Dishwasher(int capacity) {
        mCapacity = capacity;
    }

    public void setCapacity(int newCapacity) {

        mCapacity = newCapacity;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public DeviceType getDeviceType() {
        return mDeviceType;
    }

    @Override
    public void setType(DeviceType deviceType) {
        mDeviceType = deviceType;
    }

    public List<String> getAttributesNames() {
        String classNameString = this.getClass().getSimpleName();
        return Configuration.getDeviceSpecsAttributes(classNameString);
    }

    public int getAttributesValues(String attribute) {
        int value = 0;
        switch (attribute) {
            case "Dishwasher Capacity":
                value = getCapacity();
                break;
        }
        return value;}

        public void setAttributeValue (String attribute, String newValue){
            switch (attribute) {
                case "Dishwasher Capacity":
                    setCapacity(parseInt(newValue));
                    break;
            }
        }

        public String showDeviceAttributeNamesAndValues () {
            StringBuilder result = new StringBuilder();
            int number = 3;
            for (String s : getAttributesNames()) {
                result.append(number++);
                result.append(" - ");
                result.append(s.concat(" : " + getAttributesValues(s)));
                result.append("\n");
            }
            return result.toString();
        }
        @Override
        public double getEnergyConsumption () {
            return 0;
        }
    }
