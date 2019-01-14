package smarthome.model;


public interface DeviceSpecs {
    /**
     * @return the type of device for a device types that have their own specific features
     */
    String getType();

    /**
     * @param index device type in the specified index enum position
     * @return the type of device for a device types that have their own specific features
     */
    String getTypeFromIndex(int index);
}
