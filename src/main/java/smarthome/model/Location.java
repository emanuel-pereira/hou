package smarthome.model;

public class Location {


    private double mLatitude;
    private double mLongitude;
    private double mAltitude;

    /**
     * Constructor to set values for the coordinates array
     * @param latitude latitude coordinates
     * @param longitude longitude coordinates
     * @param altitude altitude coordinate
     */
    public Location(double latitude, double longitude, double altitude) {
        this.mLatitude= latitude;
        this.mLongitude = longitude;
        this.mAltitude= altitude;
    }

    /**
     * Method to check and validate in the previously defined coordinates
     * @return true if all coordinates are correct, else if at least one of
     * the coordinates is wrongly placed the method will return false
     */
    public boolean checkIfInputValid(){
        if (mLatitude > 90 || mLatitude < -90) {//valores máx e mín de Latitude em Graus. - = Sul & + = Norte
            return false;
        }

        if (mLongitude > 180 || mLongitude < -180){//valores máx e mín de Longitude em Graus. - = Oeste & + = Este
            return false;
        }

        if (mAltitude > 8848 || mAltitude < -12500){//valores máx e mín de Altitude em Metros
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * Method to calculate the linear distance between two location, used in both exercise 1 and 2
     * Given two points in the space  and  the distance between them is defined as the module of the vector AB
     * which can be then calculated using the Pitagoric Theorem
     * @param location1 this is the Location object for the first GA
     * @param location2 this is the Location object for the second GA
     * @return
     */
    public static double calcLinearDistanceBetweenTwoPoints(Location location1, Location location2){
        return Math.sqrt(Math.pow(location2.mLatitude- location1.mLatitude,2)
                +Math.pow(location2.mLongitude- location1.mLongitude,2)
                +Math.pow(location2.mAltitude- location1.mAltitude,2));
    }

    /**
     * Method that simply returns the latitude of a specific location
     * @return the latitude of a specific location
     */
    public double getLatitude (){
        return this.mLatitude;
    }

    /**
     * Method that simply returns the longitude of a specific location
     * @return the longitude of a specific location
     */
    public double getLongitude (){
        return this.mLongitude;
    }


    /**
     * Method that simply returns the altitude of a specific location
     * @return the altitude of a specific location
     */
    public double getAltitude (){
        return this.mAltitude;
    }
}



