package Sprint_0;

public class Location {


    private double[] _coordinates= new double[3];

    public Location(double longitude, double latitude, double altitude) {
        this._coordinates[0] = longitude;
        this._coordinates[1] = latitude;
        this._coordinates[2] = altitude;
    }

    public Location() {
    }




    public double calcLinearDistance(Location location1, Location location2){
        double linearDistance= Math.sqrt(Math.pow(location2._coordinates[0]-location1._coordinates[0],2)+Math.pow(location2._coordinates[1]-location1._coordinates[1],2)+Math.pow(location2._coordinates[2]-location1._coordinates[2],2));
        return linearDistance;
    }

    /* public Location(){
    }*/

  /*  public Location (double[] coordinates) {
        coordinates= new double[3];
        this._coordinates=coordinates;

    }

    /*public Location (double[] center) {
        _latitude = center[0];
        _longitude = center[1];
        _altitude = center[2];
    }*/


  /*  public boolean checkIfCoordinatesValid (){
        if (_latitude > 90 || _latitude < -90) {
            return false;
        }

        if (_longitude > 180 || _longitude < -180){
            return false;
        }

        if (_altitude > 8848 || _altitude < -12500){
            return false;
        }

        else {
            return true;
        }
    }*/


}



