package smarthome.model;

import java.util.Calendar;
import java.util.Objects;


public class Sensor {
    private String mDesignation;
    private Location mLocation;
    private SensorType mSensorType;
    private Calendar mStartDate;
    private String mUnit; //to analyse the creation of a class unit so we may have a list of units for a specific Datatype (eg. SensorType: temperature with list of units containing: celsius, kelvin and fahrenheit)
    private ReadingList mReadingList;


    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     * accordingly with the criteria defined in the "nameIsValid" method.
     *
     * @param designation refers to sensors name
     */
    public Sensor(String designation) {
        if (nameIsValid (designation)) {
            this.mDesignation = designation;
        }

    }

    /**
     * Constructor requiring to set a specific designation and a location for any object of
     * type Sensor created.
     *
     * @param designation refers to sensor name
     * @param startDate   refers to the sensor's working status start date
     * @param latitude    refers to the sensor's latitude
     * @param longitude   refers to the sensor's longitude
     * @param altitude    refers to the sensor's altitude
     * @param sensorType    refers to the sensor's chosen data type, humidity, precipitation
     */
    public Sensor(String designation, Calendar startDate, double latitude, double longitude, double altitude, String sensorType) {
        if (nameIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = new Location(latitude, longitude, altitude);
            this.mSensorType = new SensorType(sensorType);
            this.mReadingList=new ReadingList();
        }
    }

    public Sensor(String designation, Calendar startDate, double latitude, double longitude, double altitude, SensorType sensorType) {
        if (nameIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = new Location(latitude, longitude, altitude);
            this.mSensorType = sensorType;
            this.mReadingList=new ReadingList();
        }
    }


    /**
     * Sensor for Rooms
     *
     * @param designation
     * @param startDate
     * @param sensorType
     * @param unit
     * @param readings
     */
    public Sensor(String designation, Calendar startDate, SensorType sensorType, String unit, ReadingList readings) {
        if (nameIsValid(designation)) {
            mDesignation = designation;
            mStartDate = startDate;
            mSensorType = sensorType;
            mUnit = unit;
            mReadingList = readings;
        }
    }

    /**
     * Sensor for GA
     *
     * @param designation
     * @param startDate
     * @param latitude
     * @param longitude
     * @param altitude
     * @param sensorType
     * @param unit
     * @param readings
     */
    public Sensor(String designation, Calendar startDate, double latitude, double longitude, double altitude, SensorType sensorType, String unit, ReadingList readings) {
        if (nameIsValid(designation)) {
            mDesignation = designation;
            mStartDate = startDate;
            mLocation = new Location(latitude, longitude, altitude);
            mSensorType = sensorType;
            mUnit = unit;
            mReadingList = readings;
        }
    }

    /**
     * Method to check if the sensorDesignation given to name the sensor meets the criteria defined to be
     * considered a valid sensorDesignation, namely:
     * - name cannot be empty or null
     * - name must have only alphanumeric characters
     *
     * @param name sensor's name
     * @return true if name sensorDesignation is valid, if it is not null or empty
     */
    public boolean nameIsValid(String name) {
        if (name.trim ().isEmpty ()) {
            return false;
        }
        return name.matches("[A-Za-z0-9 \\- ]*");
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     *
     * @param sensorDesignation sensor's name String
     */
    public boolean setSensorDesignation(String sensorDesignation) {
        if (nameIsValid (sensorDesignation)) {
            this.mDesignation = sensorDesignation;
            return true;
        }
        return false;
    }

    /**
     * Returns the designation of the sensor
     *
     * @return is the sensor's name designation
     */
    public String getDesignation() {
        return this.mDesignation;
    }

    /**
     * Changes the location of the sensor to the new location coordinates inputted by the user.
     *
     * @param location , of the object Location type, to update the location of the sensor
     */
    public void setSensorLocation(Location location) {
        this.mLocation = location;
    }

    /**
     * Returns the location coordinates of the sensor
     *
     * @return object Location
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * Changes the dataType of the sensor to the dataType inputted.
     *
     * @param sensorType new object from the dataType class.
     */
    public void setSensorType(SensorType sensorType) {
        this.mSensorType = sensorType;
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return object dataType
     */
    public SensorType getSensorType() {
        return this.mSensorType;
    }

    public ReadingList getReadingList() {
        return mReadingList;
    }

    /**
     * Method to calculate linear distance between two sensors
     *
     * @param sensor1 object sensor 1
     * @param sensor2 object sensor 2
     * @return calculated distance betwwen both objects
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        return mLocation.calcLinearDistanceBetweenTwoPoints (sensor1.getLocation (), sensor2.getLocation ());
    }



    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        return mReadingList.getLastReading();
    }

    public double getLastReadingValuePerSensor() {
        double lastValue;
        lastValue = mReadingList.getReadingList ().get (mReadingList.getReadingList ().size () - 1).returnValueOfReading ();
        return lastValue;
    }

    /**
     * This method returns the maximum value within a list of readings for a sensor.
     *
     * @return the highest reading of a list of readings
     */
    /*public double getMaxReadingSensor(){
        return  mReadingList.getMaxReading();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return Objects.equals (mDesignation, sensor.mDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDesignation, mLocation, mSensorType);
    }
}
