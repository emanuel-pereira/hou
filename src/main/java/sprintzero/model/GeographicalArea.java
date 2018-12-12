package sprintzero.model;


import java.util.ArrayList;
import java.util.List;

public class GeographicalArea {
    String mDesignation;
    TypeGA mTypeArea;
    Location mLocation;
    List<Sensor> mSensorList;
    OccupationArea mOccupation;

    TypeGAList TGAList = new TypeGAList();

    /**
     * This constructor method does set up the Geographical Area name and it's type
     *
     * @param designation GA name
     * @param typeArea    GA type
     */
    public GeographicalArea(String designation, TypeGA typeArea) {
        mDesignation = designation;
        mTypeArea = typeArea;
    }

    /**
     * This constructor method does set up the Geographical Area name as well as it's type and a location composed of coordinates and altitude
     *
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
     * This constructor method defines a Geographical Area with a designation, type, location as well as height and width
     * to calculate its occupation area
     *
     * @param designation GA name
     * @param typeGA      GA type
     * @param longitude   GA longitude
     * @param latitude    GA latitude
     * @param altitude    GA altitude
     * @param height      GA height
     * @param width       GA width
     */
    public GeographicalArea(String designation, String typeGA, double latitude, double longitude, double altitude, double height, double width) {
        mDesignation = designation;
        mTypeArea = new TypeGA(typeGA);
        mLocation = new Location(latitude, longitude, altitude);
        mOccupation = new OccupationArea(height, width);
    }

    /**
     * Method that returns the longitude at the top left corner of a Geographical Area
     * based on its central location longitude and the height of its occupation area.
     * Assumptions:
     * 1) No inclination of geographical areas is assumed;
     * 2) Attributes Occupation.Height and Location.Longitude are in the same dimension (vertical)
     *
     * @return longitude at the top left corner of a Geographical Area
     */
    public double getLongitudeTopLeftCornerGA() {
        return this.mLocation.getLongitude() + this.mOccupation.getmHeight() / 2;
    }

    /**
     * Method that returns the latitude at the top left corner of a Geographical Area
     * based on its central location latitude and the width of its occupation area.
     * Assumptions:
     * 1) No inclination of geographical areas is assumed;
     * 2) Attributes Occupation.Width and Location.Latitude are in the same dimension (horizontal)
     *
     * @return latitude at the top left corner of a Geographical Area
     */
    public double getLatitudeTopLeftCornerGA() {
        return this.mLocation.getLatitude() - this.mOccupation.getmWidth() / 2;
    }
//====================================

    /**
     * Method that returns the longitude at the bottom right corner of a Geographical Area
     * based on its central location longitude and the height of its occupation area.
     * Assumptions:
     * 1) No inclination of geographical areas is assumed;
     * 2) Attributes Occupation.Height and Location.Longitude are in the same dimension (vertical)
     *3) Longitude and height have the same unit of measure
     * @return longitude at the bottom right corner of a Geographical Area
     */
    public double getLongitudeBottomRightCornerGA() {
        return this.mLocation.getLongitude() - this.mOccupation.getmHeight() / 2;
    }

    /**
     * Method that returns the latitude at the bottom right corner of a Geographical Area
     * based on its central location latitude and the width of its occupation area.
     * Assumptions:
     * 1) No inclination of geographical areas is assumed;
     * 2) Attributes Occupation.Width and Location.Latitude are in the same dimension (horizontal)
     * 3) Width and Latitude have the same unit of measure
     *
     * @return latitude at the bottom right corner of a Geographical Area
     */
    public double getLatitudeBottomRightCornerGA() {
        return this.mLocation.getLatitude() + this.mOccupation.getmWidth() / 2;
    }

    //====

    /**
     * Checks if a longitude coordinate is within the longitude range of a Geographical Area
     * @param longitude
     * @return true if longitude coordinate is within the longitude range of a Geographical Area.
     * False otherwise
     */
    private boolean longitudeIsInAG(double longitude) {
        if (getLongitudeBottomRightCornerGA()<=longitude && longitude<=getLongitudeTopLeftCornerGA()) {
            return true;}
        else return false;
    }

    /**
     * Checks if a latitude coordinate is within the latitude range of a Geographical Area
     * @param latitude
     * @return true if latitude coordinate is within the latitude range of a Geographical Area.
     *False otherwise.
     */
    private boolean latitudeIsInAG(double latitude) {
        if (getLatitudeTopLeftCornerGA() <= latitude && latitude<=getLatitudeBottomRightCornerGA()) {
            return true;}
        else return false;
    }

    /**Checks if latitude and longitude coordinates are within a geographical area.
     * @return true if coordinates are within a geographical area and false otherwise.
     */
    public boolean locationIsInAG(double latitude, double longitude) {

        if (latitudeIsInAG(latitude)==true && longitudeIsInAG(longitude)==true) {
            return true;
        }
        else return false;
    }

      /* If (latitudeSupEsqAG<=latitudeSensor<=latitudeInfDirAG && longitudeSupEsqAG<=longitudeSensor<=longitudeInfDirAG)
        Return true;
        Else return false;*/


    /**
     * Constructor requiring to set a specific the designation, type, location
     * for a given Geographical Area, as well as its sensor list.
     *
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
     *
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
     *
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
}





