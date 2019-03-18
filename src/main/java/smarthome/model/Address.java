package smarthome.model;

public class Address {

    private String street;
    private String zipCode;
    private String town;
    private Location gpsLocation;

    /**
     * This constructor sets up the Address that will be used in the House
     *
     * @param streetName  Required street name
     * @param zipCode     Required zip code
     * @param town        Required town
     * @param gpsLocation Required GPS location
     */
    public Address(String streetName, String zipCode, String town, Location gpsLocation) {
        this.street = streetName;
        this.zipCode = zipCode;
        this.town = town;
        this.gpsLocation = gpsLocation;
    }

    public Address(String streetName, String zipCode, String town, double latitude, double longitude, double altitude) {
        this.street = streetName;
        this.zipCode = zipCode;
        this.town = town;
        this.gpsLocation = new Location (latitude, longitude, altitude);
    }

    public void setStreet(String streetName) {
        if (this.validateName (streetName)) {
            this.street = streetName;
        }
    }


    public void setZipCode(String zipCode) {
        if (this.validateZipCode (zipCode)) {
            this.zipCode = zipCode;
        }
    }

    public void setTown(String town) {
        if (this.validateTown (town)) {
            this.town = town;
        }
    }

    /**
     * Validates the name of the street
     *
     * @param name The name of the street
     * @return False if nulls, empty spaces and texts that start with spaces
     */
    public boolean validateName(String name) {
        if (name == null || name.trim ().isEmpty ()) {
            return false;
        }
        return this.noStartWithSpaces (name);
    }

    /**
     * Get de street name
     *
     * @return Name of the street
     */
    public String getName() {
        return this.street;
    }

    /**
     * Validates the number of the house. It accepts only numbers with no spaces
     *
     * @param town The town of the house
     * @return False if nulls, empty spaces and numbers that starts and ends with spaces
     */
    public boolean validateTown(String town) {
        if (town == null || town.trim ().isEmpty ()) {
            return false;
        }
        return this.noStartWithSpaces (town);
    }

    /**
     * Get house number
     *
     * @return Number of the house
     */
    public String getTown() {
        return this.town;
    }

    /**
     * Validates the zip code of the house
     *
     * @param code The zip code of the house
     * @return False if nulls, empty spaces and if it starts with spaces
     */
    public boolean validateZipCode(String code) {
        if (code == null || code.trim ().isEmpty ()) {
            return false;
        }
        return this.noStartWithSpaces (code);
    }


    public boolean noStartWithSpaces(String text) {
        return text.matches ("^(?![\\s]).*");
    }

    /**
     * Get de zip code
     *
     * @return The zip code of the house
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Get GPS location
     *
     * @return The GPS location of the house
     */
    public Location getGPSLocation() {
        return this.gpsLocation;
    }


}




