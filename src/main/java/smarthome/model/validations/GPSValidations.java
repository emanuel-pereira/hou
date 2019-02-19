package smarthome.model.validations;

public class GPSValidations {

    /**
     * Method that checks if latitude inputted by the user is valid
     * @param latitude user input
     * @return true if latitude is within defined range, otherwise throws IllegalArgumentException
     */
    public boolean latitudeIsValid(double latitude) {
        if (latitude >= -90 && latitude <= 90)
            return true;
        throw new IllegalArgumentException("latitude must be between [-90,90]");
    }

    /**
     * Method that checks if longitude inputted by the user is valid
     * @param longitude user input
     * @return longitude is within defined range, otherwise throws IllegalArgumentException
     */
    public boolean longitudeIsValid(double longitude) {
        if (longitude >= -180 && longitude <= 180)
            return true;
        throw new IllegalArgumentException("longitude must be between [-180,180]");
    }

    /**
     * Method that checks if altitude inputted by the user is valid
     * @param altitude user input
     * @return true if altitude is within defined range, otherwise throws IllegalArgumentException
     */
    public boolean altitudeIsValid(double altitude) {
        if (altitude >= -12500 && altitude <= 8848)
            return true;
        throw new IllegalArgumentException("altitude must be between [-12.500, 8848]");
    }
}
