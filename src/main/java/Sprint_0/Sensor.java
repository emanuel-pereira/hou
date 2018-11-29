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
     *
     * @param designation every object of type sensor must have a designation.
     */
    public Sensor(String designation) {
        this.mDesignation = designation;
    }

    /**
     * Constructor requiring to set the location for any object of type sensor
     *
     * @param sensorLocation
     */
    public Sensor(Location sensorLocation) {
        this.mLocation = sensorLocation;
    }

    public boolean designationIsValid(String designation) {
        if (designation != null && designation != "") {
            return designation.matches("[a-zA-Z0-9]*") && designation.length() < 40;
        }
        return false;
    }

    public void setmDesignation(String designation) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
        }
    }

    public String getmDesignation() {
        return this.mDesignation;
    }

    public void setmLocation(Location location) {
        {
            this.mLocation = location;
        }
    }

    public Location getmLocation() {
        return this.mLocation;
    }

    public void setmDataTypeDesignation(DataType dataType) {
        this.mDataTypeDesignation = dataType;
    }

    public DataType getmDataTypeDesignation() {
        return this.mDataTypeDesignation;
    }

    /**
     * Method to calculate linear distance between two sensors
     * @param sensor1
     * @param sensor2
     * @return
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        Location location = new Location();
        double linearDistanceBetweenTwoSensor = location.calcLinearDistanceBetweenTwoPoints(sensor1.getmLocation(), sensor2.getmLocation());
        return linearDistanceBetweenTwoSensor;
    }
}
