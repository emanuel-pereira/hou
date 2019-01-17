package smarthome.model;

import java.util.EnumSet;

public enum DeviceType {
    ELECTRIC_WATER_HEATER("Electric Water Heater", 1),
    WASHING_MACHINE("Washing Machine", 2),
    DISHWASHER("Dishwasher", 3),
    FRIDGE("Fridge", 4),
    KETTLER("Kettler", 5),
    OVEN("Oven", 6),
    STOVE("Stove", 7),
    MICROWAVE_OVEN("Microwave Oven", 8),
    WALL_ELECTRIC_HEATER("Wall Electric Heater", 9),
    PORTABLE_ELECTRIC_OIL_HEATER("Portable Electric Oil Heater", 10),
    PORTABLE_ELECTRIC_CONVENCTION_HEATER("Portable Electric Convection Heater", 11),
    WALL_TOWEL_HEATER("Wall Towel Heater", 12),
    LAMP("Lamp", 13),
    TV("Television", 14);

    private String mDeviceTypeName;
    private int mIndex;

    DeviceType(String deviceType, int index) {
        mDeviceTypeName = deviceType;
        mIndex = index;
    }

    public String getTypeString() {
        return mDeviceTypeName;
    }

    public int getIndex() {
        return mIndex;
    }

    public String getTypeFromIndex(int index) {
        return DeviceType.values()[index].getTypeString();
    }

    public static String displayDeviceTypes() {
        StringBuilder result = new StringBuilder();
        String element = " - ";
        for (DeviceType d : EnumSet.allOf(DeviceType.class)) {
            result.append(d.getIndex()).append(element).append(d.getTypeString()).append(";\n");
        }
        return result.toString();
    }
}