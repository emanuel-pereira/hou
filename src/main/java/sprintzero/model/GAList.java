package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> mGAList;

    //Construtor

    public GAList() {

        mGAList = new ArrayList<>();
    }

    //Métodos

    public GeographicalArea newGA(String inputDesignation, String typeArea, double width, double height, double latitude, double longitude, double altitude) {
        return new GeographicalArea(inputDesignation, typeArea, width, height, latitude, longitude, altitude);
    }

    public boolean addGA(GeographicalArea inputGA) {
        if (mGAList.contains(inputGA))
            return false;
        return mGAList.add(inputGA);
    }

    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

    /**
     * Method to get Geographical Area in index position i
     *
     * @param i index position of Geographical Area
     * @return Geographical Area in index position i
     */

    public GeographicalArea get(int i) {
        return this.mGAList.get(i);
    }

    /**
     * @return
     */
    public int size() {
        return this.mGAList.size();
    }

    /**
     * Method that checks if a specific location is within one or more Geographical Areas and returns the
     * list of Geographical Areas that contain that location. If no match is found, then this method returns null.
     *
     * @param latitude  latitude of the location
     * @param longitude longitude of the location
     * @return a list of Geographical Areas that contain a specific location
     */
    public GAList listOfGAsContainingLocation(double latitude, double longitude) {
        GAList listOfGAsContainingLocation = new GAList();
        for (int i = 0; i < mGAList.size(); i++) {
            if (mGAList.get(i).locationIsInAG(latitude, longitude)) {
                listOfGAsContainingLocation.addGA(mGAList.get(i));
            }
        }
        return listOfGAsContainingLocation;
    }

    /**
     * Method that enables the user to add a sensor to the Sensor List of a specific Geographical Area
     * in a list of Geographical Areas that contain the sensor's location.
     *
     * @param sensor    to be added to Geographical Area in index position of GAList
     * @param indexOfGA in GAList to which the user wants to add the Sensor
     * @return true if sensor is added to GA in index position, otherwise false.
     */
    public boolean addSensorToGAInListOfGAs(Sensor sensor, int indexOfGA) {
        GAList listOfGAsContainingSensor = listOfGAsContainingLocation(sensor.getSensorLocation().getLatitude(),
                sensor.getSensorLocation().getLongitude());
        if (indexOfGA < listOfGAsContainingSensor.size()) {
            GeographicalArea selectedGA = listOfGAsContainingSensor.get(indexOfGA);
            return (selectedGA.addSensor(sensor));
        } else return false;
    }
}
