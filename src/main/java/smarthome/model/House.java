package smarthome.model;

public class House {

    private String mAddress;
    private String mZipCode;
    private Location mGPSLocation;
    private GeographicalArea mGA;


    public House (String address, String zipCode, Location gpsLocation,GeographicalArea GA ){

        mAddress = address;
        mZipCode = zipCode;
        mGPSLocation = gpsLocation;
        mGA = GA;

    }

    public String getAddress (){

        return mAddress;
    }

    public String getZipCode (){
        return mZipCode;
    }

    public Location getGPSLocation (){
        return mGPSLocation;
    }

    public GeographicalArea getGA(){
        return mGA;
    }


}
