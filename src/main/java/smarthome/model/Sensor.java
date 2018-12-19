package smarthome.model;

import java.util.*;


/**
 *
 */
public class Sensor {
    private String mDesignation;
    private Calendar mStartDate;
    private Location mLocation;
    private DataType mDataType; //temp, humidade,
    private List<Reading> mListOfReadings = new ArrayList<>();

    public Sensor() {
    }

    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     * accordingly with the criteria defined in the "nameIsValid" method.
     * @param designation refers to sensors name
     */
    public Sensor(String designation) {
        if (nameIsValid(designation)) {
            this.mDesignation = designation;
        }
    }

    /**
     * Constructor requiring to set a specific designation and a location for any object of
     * type Sensor created.
     * @param designation refers to sensor name
     * @param startDate refers to the sensor's working status start date
     * @param latitude refers to the sensor's latitude
     * @param longitude refers to the sensor's longitude
     * @param altitude refers to the sensor's altitude
     * @param dataType refers to the sensor's chosen data type, humidity, precipitation
     */
    public Sensor(String designation, Calendar startDate, double latitude, double longitude, double altitude, DataType dataType) {
        if (nameIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = new Location(latitude, longitude, altitude);
            this.mDataType = dataType;
        }
    }

    /**
     * Constructor requiring to set a specific designation, start date, location, data reading type and list of readings
     * for an object of type Sensor created.
     * @param designation sensor designation
     * @param startDate sensor start date
     * @param sensorLocation sensor location
     * @param dataType sensor data type
     * @param listOfReadings sensor's list of readings
     *
     */
    public Sensor(String designation, Calendar startDate, Location sensorLocation, DataType dataType, List<Reading> listOfReadings) {
        if (nameIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = sensorLocation;
            this.mDataType = dataType;
            this.mListOfReadings = listOfReadings;
        }
    }

    /**
     * Method to check if the sensorDesignation given to name the sensor meets the criteria defined to be
     * considered a valid sensorDesignation, namely:
     * - mDesignation cannot be empty or null
     * @param sensorDesignation sensor's name
     * @return true if name sensorDesignation is valid, if it is not null or empty
     */
    public boolean nameIsValid(String sensorDesignation) {
        return sensorDesignation != null && !sensorDesignation.trim().isEmpty();
    }

    /**
     * Changes the sensorDesignation of the sensor to the one inputted by the user.
     * @param sensorDesignation sensor's name String
     */
    public boolean setSensorDesignation(String sensorDesignation) {
        if (nameIsValid(sensorDesignation)) {
            this.mDesignation = sensorDesignation;
            return true;
        }
        return false;
    }

    /**
     * Returns the designation of the sensor
     * @return is the sensor's name designation
     */
    public String getDesignation() {
        return this.mDesignation;
    }

    /**
     * Changes the location of the sensor to the new location coordinates inputted by the user.
     * @param location , of the object Location type, to update the location of the sensor
     */
    public void setSensorLocation(Location location) {
        this.mLocation = location;
    }

    /**
     * Returns the location coordinates of the sensor
     * @return object Location
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * Changes the dataType of the sensor to the dataType inputted.
     * @param dataType new object from the dataType class.
     */
    public void setSensorDataType(DataType dataType) {
        this.mDataType = dataType;
    }

    /**
     * Returns the dataType of the sensor.
     * @return object dataType
     */
    public DataType getSensorDataType() {
        return this.mDataType;
    }

    /**
     * Method to calculate linear distance between two sensors
     * @param sensor1 object sensor 1
     * @param sensor2 object sensor 2
     * @return calculated distance betwwen both objects
     */
    public double calcLinearDistanceBetweenTwoSensors(Sensor sensor1, Sensor sensor2) {
        return Location.calcLinearDistanceBetweenTwoPoints(sensor1.getLocation(), sensor2.getLocation());
    }

    /**
     * Method to get the list of readings of a sensor.
     * @return the list of readings of a sensor
     */
    public List<Reading> getListOfReadings() {
        return this.mListOfReadings;
    }

    /**
     * Method to add a new reading to the list of readings of a sensor.
     * @param newReading new reading object with a value and date
     */
    public void addReadingToList(Reading newReading) {
        if (!(getListOfReadings().contains(newReading)))
            this.mListOfReadings.add(newReading);
    }

    /**
     * This method looks for the last reading within a list of readings for a sensor.
     * @return the last reading of a list of readings
     */
    public Reading getLastReadingPerSensor() {
        Reading lastValue;
        lastValue = mListOfReadings.get(getListOfReadings().size() - 1);
        return lastValue;
    }

    /**
     * Method to check if a month has registered readings
     *
     * @param monthOfReadings - the month to be checked for readings
     * @return true if the month has readings, false if it has not
     */
    public boolean isMonthOfReadingList(int monthOfReadings) {
        for (int index = 0; index < mListOfReadings.size(); index++) {
            if (mListOfReadings.get(index).getMonthOfReading() == monthOfReadings)
                return true;
        }
        return false;
    }

    /**
     * Method to calculate the average value of all the readings for one given month
     *
     * @param monthOfReadings - the month from which we want to calculate the average of the registered readings
     * @return the average of reading values for one month
     */
    public double getMonthlyAverageReadings(int monthOfReadings) {

        double sum = 0;
        int counter = 0;
        for (int index = 0; index < mListOfReadings.size(); index++) {
            if (mListOfReadings.get(index).getMonthOfReading() == monthOfReadings) {
                sum += mListOfReadings.get(index).returnValueOfReading();
                counter++;
            }
        }
        if (counter == 0) {
            return Double.NaN;
        }
        return sum / counter;
    }


    /**
     * Method to calculate the smallest value of all the readings for one given month
     *
     * @param monthOfReadings - the month from which we want to calculate the smallest value of the registered readings
     * @return the smallest of reading values for one month
     */
    public double getMonthlyMinimumReading(int monthOfReadings) {

        double minimum = Double.NaN;
        if (isMonthOfReadingList(monthOfReadings)) {
            minimum = mListOfReadings.get(0).returnValueOfReading();
            for (int index = 0; index < mListOfReadings.size(); index++) {
                if (mListOfReadings.get(index).returnValueOfReading() < minimum) {
                    minimum = mListOfReadings.get(index).returnValueOfReading();
                }
            }
        }
        return minimum;
    }

    /**
     * Method to retrieve the monthly average for each month
     * For this calculation the method takes into account the measured values
     * @return array averageValuesEachMonth with the average values for each month
     */
    public double[] getMonthlyAverageReadingEachMonth() {
        double[] averageValuesEachMonth = new double[12];
        Arrays.fill(averageValuesEachMonth, Double.NaN);//to populate the array with null values, since before it were 0.0
        for (int i = 0; i < averageValuesEachMonth.length; i++) {
            if (isMonthOfReadingList(i + 1))
                averageValuesEachMonth[i] = getMonthlyAverageReadings(i + 1);
        }
        return averageValuesEachMonth;
    }

    /**
     * Method to get the average minimum value in a list of average monthly readings
     * @return the average minimum value of
     */
    public double getMinimumAverageReading() {
        double minimum = getMonthlyAverageReadingEachMonth()[0];
        for (int i = 1; i < getMonthlyAverageReadingEachMonth().length; i++) {
            if (Double.isNaN(minimum)) {
                minimum = getMonthlyAverageReadingEachMonth()[i];
            }
            if (minimum > getMonthlyAverageReadingEachMonth()[i]) {
                minimum = getMonthlyAverageReadingEachMonth()[i];
            }
        }
        return minimum;
    }

    /**
     * Method to get the average maximum value in a list of average monthly readings
     * @return the maximum average month readings
     */
    public double getMaximumAverageReading() {
        double maximum = getMonthlyAverageReadingEachMonth()[0];
        for (int i = 1; i < getMonthlyAverageReadingEachMonth().length; i++) {
            if (Double.isNaN(maximum)) {
                maximum = getMonthlyAverageReadingEachMonth()[i];
            }
            if (maximum < getMonthlyAverageReadingEachMonth()[i]) {
                maximum = getMonthlyAverageReadingEachMonth()[i];
            }
        }
        return maximum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(mDesignation, sensor.mDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDesignation, mLocation, mDataType);
    }
}
