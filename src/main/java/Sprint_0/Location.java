package Sprint_0;

public class Location {

    private double _latitude;
    private double _longitude;
    private double _altitude;

   /* public Location(){
    }*/

    public Location (double latitude, double longitude, double altitude) {
      this._latitude = latitude;
      this._longitude = longitude;
      this._altitude = altitude;
    }

    /*public Location (double[] center) {
        _latitude = center[0];
        _longitude = center[1];
        _altitude = center[2];
    }*/


    public boolean checkIfCoordinatesValid (){
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
    }
}



