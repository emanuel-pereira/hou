package smarthome.model;


import java.util.Objects;

public class SensorType {


    private String sensorType;


    /**
     * Constructor method that defines a designation for a type of data
     *
     * @param sensorTypeDesignation - String that names the type of data
     */
    public SensorType(String sensorTypeDesignation) {
        this.sensorType = sensorTypeDesignation;
    }


    /**
     * Method to return a data type designation
     *
     * @return designation of a data type
     */
    public String getType() {
        return this.sensorType;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SensorType))
            return false;
        SensorType sensorType = (SensorType) o;
        return this.sensorType.equals (sensorType.getType());
    }

    /**
     * hashCode() just returns the object's address in memory
     * This method OverRides the hashCode method defined by class
     * Object does also return distinct integers for distinct objects.
     * This is typically implemented by converting the internal
     * address of the object into an integer
     *
     * @return hashcode int value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash (this.sensorType);
    }


}


