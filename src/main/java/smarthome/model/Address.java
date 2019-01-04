package smarthome.model;

public class Address {

    private String mStreet;
    private String mZipCode;
    private String mTown;
    private Location mGPSLocation;

    /**
     * This constructor sets up the Address that will be used in the House
     *
     * @param streetName  Required street name
     * @param zipCode     Required zip code
     * @param town        Required town
     * @param gpsLocation Required GPS location
     */
    public Address(String streetName, String zipCode, String town, Location gpsLocation) {
        mStreet = streetName;
        mZipCode = zipCode;
        mTown = town;
        mGPSLocation = gpsLocation;
    }

    public Address(String streetName, String zipCode, String town, double latitude, double longitude, double altitude) {
        mStreet = streetName;
        mZipCode = zipCode;
        mTown = town;
        mGPSLocation = new Location(latitude, longitude, altitude);
    }

    public void setStreet(String streetName) {
        if (this.validateName(streetName)) {
            mStreet = streetName;
        }
    }


    public void setZipCode(String zipCode) {
        if (this.validateZipCode(zipCode)) {
            mZipCode = zipCode;
        }
    }

    public void setTown(String town) {
        if (this.validateTown (town)) {
            mTown = town;
        }
    }

    /**
     * Validates the name of the street
     *
     * @param name The name of the street
     * @return False if nulls, empty spaces and texts that start with spaces
     */
    public boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (!name.matches("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    /**
     * Get de street name
     *
     * @return Name of the street
     */
    public String getName() {
        return mStreet;
    }

    /**
     * Validates the number of the house. It accepts only numbers with no spaces
     *
     * @param town The town of the house
     * @return False if nulls, empty spaces and numbers that starts and ends with spaces
     */
    public boolean validateTown(String town) {
        if (town == null || town.trim().isEmpty()) {
            return false;
        }
        if (!town.matches("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    /**
     * Get house number
     *
     * @return Number of the house
     */
    public String getTown() {
        return mTown;
    }

    /**
     * Validates the zip code of the house
     *
     * @param code The zip code of the house
     * @return False if nulls, empty spaces and if it starts with spaces
     */
    public boolean validateZipCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        if (!code.matches("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    /**
     * Get de zip code
     *
     * @return The zip code of the house
     */
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * Get GPS location
     *
     * @return The GPS location of the house
     */
    public Location getGPSLocation() {
        return mGPSLocation;
    }


}




