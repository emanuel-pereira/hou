package smarthome.model;


import javax.persistence.*;

import java.util.Objects;

import static smarthome.model.TypeGAList.newTypeGA;

@Entity
@Table(name = "Geo_Area")
public class GeographicalArea {

    @Id
    @Column(name = "ID")
    private String identification;

    private String designation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private TypeGA type;

    @Embedded
    private Location location;

    @Transient
    private SensorList sensorListInGa;

    @Embedded
    private OccupationArea occupation;

    @Transient
    private GeographicalArea parentGa;


    protected GeographicalArea() {
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
        this.type = newTypeGA(typeGA.toLowerCase());
        this.occupation = occupationArea;
        this.location = location;
        this.sensorListInGa = new SensorList();
    }

    public GeographicalArea(String id, String name, TypeGA typeGA, OccupationArea occupationArea, Location location) {
        this.identification = id;
        this.designation = name;
        this.type = typeGA;
        this.occupation = occupationArea;
        this.location = location;
        this.sensorListInGa = new SensorList();
    }

    public GeographicalArea(String id, String name) {
        this.identification = id;
        this.designation = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String id) {
        this.identification = id;
    }

    /**
     * method to get this Geographical Area designation
     *
     * @return return this geographical area designation
     */
    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Method to return the GA type
     *
     * @return GAType
     */
    public TypeGA getType() {
        return this.type;
    }

    public boolean setType(TypeGA newtype) {
        if (newtype != null) {
            this.type = newtype;
            return true;
        }
        return false;
    }

    /**
     * method to get this Geographical Area Type designation
     *
     * @return return this geographical Area Type designation
     */
    public String getTypeName() {
        return this.type.toString();
    }

    /**
     * method to get this Geographical Area location
     *
     * @return return this geographical area location
     */
    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Method to get list of Sensors in GA attribute
     *
     * @return the list of sensors in a Geographical Area
     */
    public SensorList getSensorListInGa() {
        return this.sensorListInGa;
    }

    public void setSensorListInGa(SensorList sensorListInGa) {
        this.sensorListInGa = sensorListInGa;
    }

    /**
     * Method to get this Geographical Area Parent Geographical Area
     *
     * @return return this geographical Area Parent
     */
    public GeographicalArea getParentGa() {
        return this.parentGa;
    }

    /**
     * US07
     * Method that tell´s if a Geographical Area is parented or contained on other.
     *
     * @param area is defined as an geographical area parented with other.
     */
    public void setParentGa(GeographicalArea area) {
        this.parentGa = area;
    }

    public OccupationArea getOccupation() {
        return this.occupation;
    }

    public void setOccupation(OccupationArea occupation) {
        this.occupation = occupation;
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
     * Method to call the method in Location which executes the calculation of the linear distance between two locations
     *
     * @param aLocation second geographical area location
     * @return returns the linear distance already calculated
     */
    private double calculateDistance(Location aLocation) {
        return this.location.calcLinearDistanceBetweenTwoPoints(this.location, aLocation);
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
        return Objects.equals(this.identification, that.identification) /*&&
                Objects.equals(this.designation, that.designation) &&
                Objects.equals(this.type, that.type)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identification/*, this.designation, this.type*/);
    }


    public String gaInString() {

        StringBuilder output = new StringBuilder();
        String space = "    ";
        String comma = ", ";

        output.append(space);
        output.append(designation);
        output.append(comma);
        output.append(identification);


        return output.toString();
    }

}







