package Sprint_0;

public class Location {


    private double[] mCoordinates = new double[3];

    public Location() { }

    public Location(double longitude, double latitude, double altitude) {
        this.mCoordinates[0] = longitude;
        this.mCoordinates[1] = latitude;
        this.mCoordinates[2] = altitude;
    }


    public boolean CheckIfInputValid(){
        if (mCoordinates[0] > 90 || mCoordinates[0] < -90) {
            return false;
        }

        if (mCoordinates[1] > 180 || mCoordinates[1] < -180){
            return false;
        }

        if (mCoordinates[2] > 8848 || mCoordinates[2] < -12500){
            return false;
        }

        else {
            return true;
        }
    }

    public double calcLinearDistanceBetweenTwoPoints(Location location1, Location location2){
        double linearDistance = Math.sqrt(Math.pow(location2.mCoordinates[0]-location1.mCoordinates[0],2)+Math.pow(location2.mCoordinates[1]-location1.mCoordinates[1],2)+Math.pow(location2.mCoordinates[2]-location1.mCoordinates[2],2));
        return linearDistance;
    }


   /* public void setLocation(double[] inputCoordinates){
        this.mCoordinates = inputCoordinates;
        if (this.CheckIfInputValid() == true){
            _latitude = mCoordinates[0];
            _longitude = mCoordinates[1];
            _altitude = mCoordinates[2];
        }

    }*/

    public double[] getLocation (){
        return this.mCoordinates;
    }

}



