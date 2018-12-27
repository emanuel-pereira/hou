package smarthome.model;

public class Address {

    private String mStreetName;
    private Integer mHouseNumber;
    private String mFloor;
    private String mApartmentNumber;
    private String mZipCode;
    private String mCounty; //concelho
    private Location mGPSLocation;

    /**
     * This constructor sets up the Address that will be used in the House
     * @param streetName Required street name
     * @param houseNumber Required house number
     * @param floor If necessary the apartment floor
     * @param apartmentNumber If necessary the apartment number
     * @param zipCode Required zip code
     * @param county Required county (eg. Paranhos)
     * @param gpsLocation Required GPS location
     */
    public Address(String streetName, Integer houseNumber, String floor, String apartmentNumber, String zipCode, String county, Location gpsLocation) {
        if (this.validateName (streetName)) {
            mStreetName = streetName;
        }
        if (this.validateNumber (houseNumber)) {
            mHouseNumber = houseNumber;
        }
        mFloor = floor;
        mApartmentNumber = apartmentNumber;
        if(this.validateZipCode (zipCode)) {
            mZipCode = zipCode;
        }
        if(this.validateCounty (county)){
            mCounty = county;
        }
        mGPSLocation = gpsLocation;
    }


    /**
     * Validates the name of the street
     * @param name The name of the street
     * @return False if nulls, empty spaces and texts that start with spaces
     */
    public boolean validateName(String name) {
        if (name == null || name.trim ().isEmpty ()) {
            return false;
        }
        if (!name.matches ("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    public String getName() {
        return mStreetName;
    }

    /**
     * Validates the number of the house. It accepts only numbers with no spaces
     * @param number The number of the house
     * @return False if nulls, empty spaces and numbers that start and end with spaces
     */
    public boolean validateNumber(Integer number) {
        if (number == null || number.toString ().trim ().isEmpty ()) {
            return false;
        }
        if (!number.toString ().matches ("^(?![\\s])\\d*")) {
            return false;
        }
        return true;
    }

    public Integer getHouseNumber() {
        return mHouseNumber;
    }

    public boolean validateZipCode(String code) {
        if (code == null || code.trim ().isEmpty ()) {
            return false;
        }
        if (!code.matches ("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public boolean validateCounty(String county) {
        if (county == null || county.trim ().isEmpty ()) {
            return false;
        }
        if (!county.matches ("^(?![\\s]).*")) {
            return false;
        }
        return true;
    }

    public String getCounty() {
        return mCounty;
    }


    public Location getGPSLocation(){
        return mGPSLocation;
    }

}


