package sprintzero;

public class Location {


    private double[] mCoordinates = new double[3];

    /**
     * Constructor to set values for the coordinates array
     * @param latitude latitude coordinates
     * @param longitude longitude coordinates
     * @param altitude altitude coordinate
     */
    public Location(double latitude, double longitude, double altitude) {
        this.mCoordinates[0] = latitude;//Coordenadas GPS devem ler-se Latitude primeiro
        this.mCoordinates[1] = longitude;//Longitude em segundo lugar
        this.mCoordinates[2] = altitude;
    }

    /**
     * Method to check and validate in the previously defined coordinates
     * @return true if all coordinates are correct, else if at least one of
     * the coordinates is wrongly placed the method will return false
     */
    public boolean checkIfInputValid(){
        if (mCoordinates[0] > 90 || mCoordinates[0] < -90) {//valores máx e mín de Latitude em Graus. - = Sul & + = Norte
            return false;
        }

        if (mCoordinates[1] > 180 || mCoordinates[1] < -180){//valores máx e mín de Longitude em Graus. - = Oeste & + = Este
            return false;
        }

        if (mCoordinates[2] > 8848 || mCoordinates[2] < -12500){//valores máx e mín de Altitude em Metros
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
        return Math.sqrt(Math.pow(location2.mCoordinates[0]- location1.mCoordinates[0],2)
                +Math.pow(location2.mCoordinates[1]- location1.mCoordinates[1],2)
                +Math.pow(location2.mCoordinates[2]- location1.mCoordinates[2],2));
    }

    /**
     * method that simply return this location coordinates array
     * @return coordinates array for the location
     */
    public double[] getLocation (){
        return this.mCoordinates;
    }

}



