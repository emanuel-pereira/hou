package smarthome.model;

import java.util.Objects;

public class GeographicalArea {
    private String identification;
    private String designation;
    private TypeGA typeOfGa;
    private Location location;
    private SensorList sensorListInGa;
    private OccupationArea occupation;
    private GeographicalArea parentGa;


    /**
     * This constructor method defines a Geographical Area with a designation, type, location as well as length and width
     * to calculate its occupation area
     *
     * @param name      GA name
     * @param typeGA    GA type
     * @param longitude GA longitude
     * @param latitude  GA latitude
     * @param altitude  GA altitude
     * @param length    GA length
     * @param width     GA width
     */
    public GeographicalArea(String id, String name, String typeGA, double latitude, double longitude, double altitude, double length, double width) {

        this.identification = id;
        this.designation = name;
        this.typeOfGa = new TypeGA(typeGA);
        this.location = new Location(latitude, longitude, altitude);
        this.occupation = new OccupationArea(length, width);
        this.sensorListInGa = new SensorList();
    }

    /**
     * Constructor required to create a new Geographical Area
     *
     * @param id             String parameter representing the id of the Geographical Area
     * @param name           String parameter representing the name of the Geographical Area
     * @param typeGA         String parameter representing the type of geographical area
     * @param location       central location of the Geographical Area represented by GPS coordinates
     * @param occupationArea Oc
     */
    public GeographicalArea(String id, String name, String typeGA, OccupationArea occupationArea, Location location) {
        this.identification = id;
        this.designation = name;
        this.typeOfGa = new TypeGAList().newTypeGA(typeGA);
        this.occupation = occupationArea;
        this.location = location;
        this.sensorListInGa = new SensorList();

    }


    /**
     * method to get this Geographical Area designation
     *
     * @return return this geographical area designation
     */
    public String getGAName() {
        return this.designation;
    }

    /**
     * method to get this Geographical Area Type designation
     *
     * @return return this geographical Area Type designation
     */
    public String getGeographicalAreaType() {
        return this.typeOfGa.toString();
    }

    /**
     * method to get this Geographical Area Parent Geographical Area
     *
     * @return return this geographical Area Parent
     */
    public GeographicalArea getGeographicalParentGA() {
        return parentGa;
    }


    /**
     * Method to get list of Sensors in GA attribute
     *
     * @return the list of sensors in a Geographical Area
     */
    public SensorList getSensorListInGA() {
        return sensorListInGa;
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
        return this.location;
    }

    /**
     * Method to call the method in Location which executes the calculation of the linear distance between two locations
     *
     * @param aLocation second geographical area location
     * @return returns the linear distance already calculated
     */
    private double calculateDistance(Location aLocation) {
        return location.calcLinearDistanceBetweenTwoPoints(this.location, aLocation);
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
        return Objects.equals(identification, that.identification) &&
                Objects.equals(designation, that.designation) &&
                Objects.equals(typeOfGa, that.typeOfGa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, designation, typeOfGa);
    }

    /**
     * US07
     * Method that tell´s if a Geographical Area is parented or contained on other.
     *
     * @param ga1 is defined as an geographical area parented with other.
     */


    public void setParentGA(GeographicalArea ga1) {

        this.parentGa = ga1;

    }

    public OccupationArea getOccupation() {
        return occupation;
    }
}







