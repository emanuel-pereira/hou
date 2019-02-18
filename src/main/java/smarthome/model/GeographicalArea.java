package smarthome.model;

import java.util.Objects;

public class GeographicalArea {
    private String mID;
    private String mDesignation;
    private TypeGA mTypeOfGA;
    private Location mLocation;
    private SensorList mSensorListInGA;
    private OccupationArea mOccupation;
    private GeographicalArea mParentGA;


    /**
     * This constructor method defines a Geographical Area with a designation, type, location as well as length and width
     * to calculate its occupation area
     *
     * @param designation GA name
     * @param typeGA      GA type
     * @param longitude   GA longitude
     * @param latitude    GA latitude
     * @param altitude    GA altitude
     * @param length      GA length
     * @param width       GA width
     */
    public GeographicalArea(String id, String designation, String typeGA, double latitude, double longitude, double altitude, double length, double width) {

        mID = id;
        mDesignation = designation;
        mTypeOfGA = new TypeGAList().newTypeGA(typeGA)/*new TypeGA(typeGA)*/;
        mLocation = new Location(latitude, longitude, altitude);
        mOccupation = new OccupationArea(length, width);
        mSensorListInGA = new SensorList();
    }

    /**
     * method to get this Geographical Area designation
     *
     * @return return this geographical area designation
     */
    public String getGeographicalAreaDesignation() {
        return this.mDesignation;
    }

    /**
     * method to get this Geographical Area Type designation
     *
     * @return return this geographical Area Type designation
     */
    public String getGeographicalAreaType() {
        return this.mTypeOfGA.toString();
    }

    /**
     * method to get this Geographical Area Parent Geographical Area
     *
     * @return return this geographical Area Parent
     */
    public GeographicalArea getGeographicalParentGA() {
        return mParentGA;
    }


    /**
     * Method to get list of Sensors in GA attribute
     *
     * @return the list of sensors in a Geographical Area
     */
    public SensorList getSensorListInGA() {
        return mSensorListInGA;
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
        return mLocation.calcLinearDistanceBetweenTwoPoints(this.mLocation, aLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeographicalArea)) {
            return false;
        }
        GeographicalArea that = (GeographicalArea) o;
        return Objects.equals(mID, that.mID) &&
                Objects.equals(mDesignation, that.mDesignation) &&
                Objects.equals(mTypeOfGA, that.mTypeOfGA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mID, mDesignation, mTypeOfGA);
    }

    /**
     * US07
     * Method that tell´s if a Geographical Area is parented or contained on other.
     *
     * @param ga1 is defined as an geographical area parented with other.
     */


    public void setmParentGA(GeographicalArea ga1) {

        this.mParentGA = ga1;

    }

    public SensorList getGASensorsByType(String type) {
        SensorList currentGASensors = this.mSensorListInGA;

        return currentGASensors.getSensorListOfRequiredSensorPerType(type);
    }
}







