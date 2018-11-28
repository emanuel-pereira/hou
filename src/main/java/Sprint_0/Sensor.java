package Sprint_0;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class Sensor {
    private String _designation;
    GregorianCalendar _startDate = new GregorianCalendar(2018, 1, 01, 9, 00);
    private Location _location;
    private DataType _dataTypeDesignation; //temp, humidade,
    private List<Reading> _reading = new ArrayList<>();
//    java.util.Date temp = new SimpleDateFormat("yyyy-mm-dd HH").parse("2012-07-10 14:58:00.000000");

    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     *
     * @param designation every object of type sensor must have a designation.
     */
    public Sensor(String designation) {
        this._designation = designation;
    }

    /**
     * Constructor requiring to set the location for any object of type sensor
     *
     * @param sensorLocation
     */
    public Sensor(Location sensorLocation) {
        this._location = sensorLocation;
    }


    public boolean designationIsValid(String designation) {
        if (designation != null && designation != "") {
            return designation.matches("[a-zA-Z0-9]*") && designation.length() < 40;
        }
        return false;
    }

    public void set_designation(String designation) {
        if (designationIsValid(designation)) {
            this._designation = designation;
        }
    }

    public String get_designation() {
        return this._designation;
    }

    public void set_location(Location location) {
        {
            this._location = location;
        }
    }

    public Location get_location() {
        return this._location;
    }

    public void set_dataTypeDesignation(DataType dataType) {
        this._dataTypeDesignation = dataType;
    }

    public DataType get_dataTypeDesignation() {
        return this._dataTypeDesignation;
    }

    /**
     * Method to calculate linear distance between two sensors
     * @param sensor1
     * @param sensor2
     * @return
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {

        Location location = new Location();
        double linearDistanceBetweenTwoSensor = location.calcLinearDistance(sensor1._location, sensor2._location);
        return linearDistanceBetweenTwoSensor;
    }

}
