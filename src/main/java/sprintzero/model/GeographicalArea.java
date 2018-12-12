package sprintzero.model;


import java.util.ArrayList;
import java.util.List;

public class GeographicalArea {
    String mDesignation;
    TypeGA mTypeArea;
    Location mLocation;
    List<Sensor> mSensorList;
    OccupationArea mOccupation;

    TypeGAList TGAList = new TypeGAList ();
    /**
     * This constructor method does set up the Geographical Area name and it's type
     * @param designation GA name
     * @param typeArea    GA type
     */
    public GeographicalArea(String designation, TypeGA typeArea) {
        mDesignation = designation;
        mTypeArea = typeArea;
    }

    /**
     * This constructor method does set up the Geographical Area name as well as it's type and a location composed of coordinates and altitude
     * @param designation GA name
     * @param typeArea    GA type
     * @param location    GA location, latitude, longitude, altitude
     */
    public GeographicalArea(String designation, TypeGA typeArea, Location location) {
        mDesignation = designation;
        mTypeArea = typeArea;
        mLocation = location;

        //TGAList.addTypeGA (typeArea);
    }

    /**
     * This constructor method defines a Geographical Area with a designation, type, location as well as length and width
     * to calculate its occupation area
     * @param designation GA name
     * @param typeGA    GA type
     * @param longitude    GA longitude
     * @param latitude    GA latitude
     * @param altitude    GA altitude
     * @param length GA length
     * @param width GA width
     */
    public GeographicalArea(String designation, String typeGA, double latitude, double longitude, double altitude, double length, double width) {
        mDesignation = designation;
        mTypeArea = new TypeGA(typeGA);
        mLocation = new Location(latitude,longitude,altitude);
        mOccupation= new OccupationArea(length,width);
    }

    /**
     * Constructor requiring to set a specific the designation, type, location
     * for a given Geographical Area, as well as its sensor list.
     * @param designation GA name
     * @param typeArea    GA type
     * @param location    GA location coordinates(latitude, longitude, altitude)
     * @param sensorList  list of sensors within GA
     */
    public GeographicalArea(String designation, TypeGA typeArea, Location location, List<Sensor> sensorList) {
        mDesignation = designation;
        mTypeArea = typeArea;
        mLocation = location;
        mSensorList = sensorList;
    }



    /**
     * @return the list of sensors within a Geographical Area
     */
    public List<Sensor> getListOfSensors() {
        return this.mSensorList;
    }

    /**
     * Method to get the last reading of each sensor available within a Geographical Area
     * @return a list with the last reading of each sensor available in a Geographical Area
     */
    public List<Reading> getLastValuesOfSensorsInGA() {
        List<Reading> lastSensorsReadings = new ArrayList<>(mSensorList.size());
        for (int i = 0; i < mSensorList.size(); i++) {
            lastSensorsReadings.add(mSensorList.get(i).getLastReadingPerSensor());
        }
        return lastSensorsReadings;
    }

    /**
     * Method to calculate the distance between to Geographical Areas
     * @param anotherGA second Geographical Area
     * @return returns the linear distance already calculated
     */
    public double calculateDistanceTo(GeographicalArea anotherGA) {
        Location anotherLocation = anotherGA.getLocation();
        double distance;
        distance = this.calculateDistance(anotherLocation);
        //return this.mLocation.calcLinearDistanceBetweenTwoPoints(anotherLocation); advanced method
        return distance;
    }

    /**
     * method to get this Geographical Area location
     *
     * @return return this geographical area location
     */
    public Location getLocation() {
        return this.mLocation;
    }

    /**
     * Method to call the method in Location which executes the calculation of the linear distance between two locations
     *
     * @param aLocation second geographical area location
     * @return returns the linear distance already calculated
     */
    private double calculateDistance(Location aLocation) {
        return Location.calcLinearDistanceBetweenTwoPoints(this.mLocation, aLocation);
    }


/**/


}





