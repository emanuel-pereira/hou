package Sprint_0;

import java.util.*;

public class Sensor {
    private String mDesignation;
    GregorianCalendar mStartDate = new GregorianCalendar(2018, 1, 01, 9, 00);
    private Location mLocation;
    private DataType mDataTypeDesignation; //temp, humidade,
    private List<Reading> mReading = new ArrayList<>();
//    java.util.Date temp = new SimpleDateFormat("yyyy-mm-dd HH").parse("2012-07-10 14:58:00.000000");

    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     * accordingly with the criteria defined in the "designationIsValid" method.
     *
     * @param designation
     */
    public Sensor(String designation) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
        }
    }

    /**
     * Constructor requiring to set a specific designation and a location for any object of
     * type Sensor created.
     *
     * @param sensorLocation
     */
    public Sensor(String designation, Location sensorLocation) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
            this.mLocation = sensorLocation;
        }
    }


    /**
     * Method to check if the designation given to name the sensor meets the criteria defined to be
     * considered a valid designation, namely:
     * - mDesignation cannot be empty or null
     * - mDesignation must have only alphabetic characters and a maximum length of 40 characters.
     *
     * @param designation
     * @return
     */
    public boolean designationIsValid(String designation) {
        if (designation != null && !(designation.equals(""))) {
            return designation.matches("[a-zA-Z0-9]*") && designation.length() < 40;
        }
        return false;
    }


    /**
     * Changes the designation of the sensor to the one inputted by the user.
     *
     * @param designation
     */

    public void setmDesignation(String designation) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
        }
    }

    /**
     * Returns the designation of the sensor
     *
     * @return
     */
    public String getmDesignation() {
        return this.mDesignation;
    }

    /**
     * Changes the location of the sensor to the new location coordinates inputted by the user.
     *
     * @param location
     */

    public void setmLocation(Location location) {
        {
            this.mLocation = location;
        }
    }

    /**
     * Returns the location coordinates of the sensor
     *
     * @return
     */
    public Location getmLocation() {
        return this.mLocation;
    }

    /**
     * Changes the dataType of the sensor to the dataType inputted.
     *
     * @param dataType
     */
    public void setmDataTypeDesignation(DataType dataType) {
        this.mDataTypeDesignation = dataType;
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return
     */
    public DataType getmDataTypeDesignation() {
        return this.mDataTypeDesignation;
    }

    /**
     * Method to calculate linear distance between two sensors
     *
     * @param sensor1
     * @param sensor2
     * @return
     */

    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        Location location = new Location();
        double linearDistanceBetweenTwoSensor = location.calcLinearDistanceBetweenTwoPoints(sensor1.getmLocation(), sensor2.getmLocation());
        return linearDistanceBetweenTwoSensor;
    }

    public List<Reading> addReadingValue(Reading newReading) {
        List<Reading> ListReadingValue = new ArrayList<>();
        if (!(mReading.contains(newReading)))
            ListReadingValue.add(newReading);
        return ListReadingValue;

    }
}
/**
 * public List<Reading> getListValuesReadPerSensor(Sensor sensor1) {
 * <p>
 * List<Reading> ListValuesReadPerSensor = new ArrayList<>();
 * Reading r = new Reading();
 * sensor1 = null;
 * <p>
 * ListValuesReadPerSensor.add(r.returnValueOfReading())
 * return null;
 * }
 * <p>
 * }
