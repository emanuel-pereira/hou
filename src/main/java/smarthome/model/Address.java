package smarthome.model;

public class Address {

    private String mStreetName;
    private Integer mHouseNumber;
    private String mFloor;
    private String mApartmentNumber;
    private String mZipCode;
    private String mCounty; //concelho
    private Location mGPSLocation;


    public Address(String streetName, Integer houseNumber, String floor, String apartmentNumber, String zipCode, String county, Location gpsLocation) {
        mStreetName = streetName;
        mHouseNumber = houseNumber;
        mFloor = floor;
        mApartmentNumber = apartmentNumber;
        mZipCode = zipCode;
        mCounty = county;
        mGPSLocation = gpsLocation;
    }

}
