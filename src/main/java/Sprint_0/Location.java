package Sprint_0;

public class Location {


    private double[] _coordinates= new double[3];

    public Location() { }

    public Location(double longitude, double latitude, double altitude) {
        this._coordinates[0] = longitude;
        this._coordinates[1] = latitude;
        this._coordinates[2] = altitude;
    }


    public boolean CheckIfInputValid(){
        if (_coordinates[0] > 90 || _coordinates[0] < -90) {
            return false;
        }

        if (_coordinates[1] > 180 || _coordinates[1] < -180){
            return false;
        }

        if (_coordinates[2] > 8848 || _coordinates[2] < -12500){
            return false;
        }

        else {
            return true;
        }
    }

    public double calcLinearDistanceBetweenTwoPoints(Location location1, Location location2){
        double linearDistance = Math.sqrt(Math.pow(location2._coordinates[0]-location1._coordinates[0],2)+Math.pow(location2._coordinates[1]-location1._coordinates[1],2)+Math.pow(location2._coordinates[2]-location1._coordinates[2],2));
        return linearDistance;
    }


   /* public void setLocation(double[] inputCoordinates){
        this._coordinates = inputCoordinates;
        if (this.CheckIfInputValid() == true){
            _latitude = _coordinates[0];
            _longitude = _coordinates[1];
            _altitude = _coordinates[2];
        }

    }*/

    public double[] getLocation (){
        return this._coordinates;
    }

}



