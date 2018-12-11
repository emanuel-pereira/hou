package sprintzero.model;

import java.util.*;

import static java.lang.Double.isNaN;


public class Sensor {
    private String mDesignation;
    private Calendar mStartDate;
    private Location mLocation;
    private DataType mDataTypeDesignation; //temp, humidade,
    private List<Reading> mListOfReadings = new ArrayList<>();

    public Sensor() {
    }

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
    public Sensor(String designation, Calendar startDate, Location sensorLocation, DataType dataType) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = sensorLocation;
            this.mDataTypeDesignation = dataType;
        }
    }

    /**
     * Constructor requiring to set a specific designation and a location for any object of
     * type Sensor created.
     *
     * @param sensorLocation
     */
    public Sensor(String designation, Calendar startDate, Location sensorLocation, DataType dataType, List<Reading> listOfReadings) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
            this.mStartDate = startDate;
            this.mLocation = sensorLocation;
            this.mDataTypeDesignation = dataType;
            this.mListOfReadings = listOfReadings;
        }
    }


    /**
     * Method to check if the designation given to name the sensor meets the criteria defined to be
     * considered a valid designation, namely:
     * - mDesignation cannot be empty or null
     *
     * @param designation
     * @return
     */
    public boolean designationIsValid(String designation) {
        return designation != null && !designation.trim().isEmpty();
    }


    /**
     * Changes the designation of the sensor to the one inputted by the user.
     *
     * @param designation
     */

    public boolean setDesignation(String designation) {
        if (designationIsValid(designation)) {
            this.mDesignation = designation;
            return true;
        }
        return false;
    }

    /**
     * Returns the designation of the sensor
     *
     * @return
     */
    public String getDesignation() {
        return this.mDesignation;
    }

    /**
     * Changes the location of the sensor to the new location coordinates inputted by the user.
     *
     * @param location
     */

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    /**
     * Returns the location coordinates of the sensor
     *
     * @return
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * Changes the dataType of the sensor to the dataType inputted.
     *
     * @param dataType
     */
    public void setDataTypeDesignation(DataType dataType) {
        this.mDataTypeDesignation = dataType;
    }

    /**
     * Returns the dataType of the sensor.
     *
     * @return
     */
    public DataType getDataTypeDesignation() {
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
        return Location.calcLinearDistanceBetweenTwoPoints(sensor1.getLocation(), sensor2.getLocation());
    }


    /**
     * Method to get the list of readings of a sensor.
     *
     * @return the list of readings of a sensor
     */

    public List<Reading> getListOfReadings() {
        return this.mListOfReadings;
    }

    /**
     * Method to add a new reading to the list of readings of a sensor.
     *
     * @param newReading
     */

    public void addReading(Reading newReading) {
        if (!(getListOfReadings().contains(newReading)))
            this.mListOfReadings.add(newReading);
    }

    /**
     * This method looks for the last reading within a list of readings for a sensor.
     *
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
     * Method to calculate the average value of all the readings for each month of the year
     *
     * @return the average of reading values for each month
     */
    public double[] getMonthlyAverageReadingEachMonth() {
        double[] averageValuesEachMonth = new double[12];
        for (int i = 0; i < averageValuesEachMonth.length; i++) {
            if (isMonthOfReadingList(i + 1))
                averageValuesEachMonth[i] = getMonthlyAverageReadings(i + 1);
            if (averageValuesEachMonth[i] == 0) {
                averageValuesEachMonth[i] = Double.NaN;
            }
        }
        return averageValuesEachMonth;
    }

    /**
     * Method to get the average minimum value in a list of average monthly readings
     *
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
     *
     * @return
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


}