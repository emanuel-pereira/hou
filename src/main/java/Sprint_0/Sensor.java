package Sprint_0;

import java.util.Date;
import java.util.List;

public class Sensor {
    private String _designation;
    private Date _startDate;
    private Location _location;
    private DataType _meteorologicalType;
    private List<Reading> _reading;

    /**
     * Constructor requiring to set a specific designation, startDate, location and metereologicalType for any object of type Sensor created
     * @param designation every object of type sensor must have a designation.
     * @param startDate start date every object of type sensor must have a start date.
     * @param meteorologicalType every object of type sensor must have a metereological type.
     * @param location every object of type sensor must have a location.
     */
    public Sensor(String designation, Date startDate, Location location, DataType meteorologicalType){
        this._designation=designation;
        this._startDate=startDate;
        this._meteorologicalType=meteorologicalType;
        this._location=location;
    }
}
