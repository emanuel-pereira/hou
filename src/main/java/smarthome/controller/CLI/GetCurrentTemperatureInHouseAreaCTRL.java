package smarthome.controller.CLI;

import smarthome.model.*;

import static smarthome.model.House.getClosestSensorWithLatestReading;
import static smarthome.model.House.getHouseGA;


public class GetCurrentTemperatureInHouseAreaCTRL {

    private final SensorTypeList sensorTypeList;

    public GetCurrentTemperatureInHouseAreaCTRL(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    /**
     * @return the number of elements in the sensor type list as an integer value
     */
    public int getSensorTypeListSize(){
        return this.sensorTypeList.size();
    }

    /**
     * @return the geographical area where the house instance is located.
     */
    public GeographicalArea getHouseGACTRL(){
        return getHouseGA();
    }

    /**
     * @return the sensor type list in string format
     */
    public String showSensorTypeList() {
        return this.sensorTypeList.showSensorTypeListInString();
    }

    /**
     * @param indexOfSensorType integer parameter to select the sensor type in the index position
     * @return the sensor type in the selected index position of the sensor type list
     */
    public SensorType getSensorTypeByIndex(int indexOfSensorType) {
        return this.sensorTypeList.getSensorTypeList().get(indexOfSensorType);
    }

    /**
     * Method to get the size of the list of sensors of the geographical area where the house is located
     * filtered by the sensorType selected as parameter.
     * @param sensorType that filters the list of sensors of the geographical area where the house is located
     * @return the size of the list of sensors of the selected sensorType
     */
    public int getSensorListOfTypeSize(SensorType sensorType) {
        GeographicalArea houseGA= getHouseGA();
        SensorList gaOfHouseSensorList=houseGA.getSensorListInGA();
        return gaOfHouseSensorList.getListOfSensorsByType(sensorType).size();

    }

    /**
     * Method that returns the closest sensor to the house location of the selected sensor type,
     * considering as fall-back selection criterion the sensor with the latest readings.
     * @param sensorType selected to check sensors of that type
     * @return the closest sensor to the house location of the selected sensor type,
     * considering as fall-back selection criterion the sensor with the latest readings.
     */
    public Sensor getClosestSensorWithLatestReadingCTRL(SensorType sensorType) {
        return getClosestSensorWithLatestReading(sensorType);
    }

    /**
     * Method that returns the last reading value registered for the selected sensor
     * @param sensor selected to get the last reading
     * @return the last reading value registered for the selected sensor
     */
    public double getLastReadingOfSensor(Sensor sensor){
        return sensor.getSensorBehavior().getLastReadingValue();
    }
}
