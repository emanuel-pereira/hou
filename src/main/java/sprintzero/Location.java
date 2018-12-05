package sprintzero;

public class Location {


    private double[] mCoordinates = new double[3];

    public Location(double latitude, double longitude, double altitude) {
        this.mCoordinates[0] = latitude;//Coordenadas GPS devem ler-se Latitude primeiro
        this.mCoordinates[1] = longitude;//Longitude em segundo lugar
        this.mCoordinates[2] = altitude;
    }


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

    public static double calcLinearDistanceBetweenTwoPoints(Location location1, Location location2){
        double linearDistance = Math.sqrt(Math.pow(location2.mCoordinates[0]- location1.mCoordinates[0],2)
                +Math.pow(location2.mCoordinates[1]- location1.mCoordinates[1],2)
                +Math.pow(location2.mCoordinates[2]- location1.mCoordinates[2],2));
        return linearDistance;
    }

    public double[] getLocation (){
        return this.mCoordinates;
    }

}



