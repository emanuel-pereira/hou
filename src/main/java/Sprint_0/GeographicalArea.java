package Sprint_0;


public class GeographicalArea {
    String mDesignation;
    TypeOfGeographicalArea mTypeArea;
    Location mLocation;

    public GeographicalArea(String designation, TypeOfGeographicalArea typeArea) {
        mDesignation = designation;
        mTypeArea = typeArea;
    }

    public GeographicalArea(String designation, TypeOfGeographicalArea typeArea, Location location) {
        mDesignation = designation;
        mTypeArea = typeArea;
        mLocation = location;
    }

}

