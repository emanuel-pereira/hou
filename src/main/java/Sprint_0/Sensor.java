package Sprint_0;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sensor {
    private String _designation;
    private SimpleDateFormat _startDate = new SimpleDateFormat ("dd-MM-yyyy 'at' hh 'hours'");
    private Location _location;
    private DataType _dataTypeDesignation;
    private List<Reading> _reading;

    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     *
     * @param designation every object of type sensor must have a designation.
     */
    public Sensor(String designation) {
        this._designation = designation;
    }

    public boolean designationIsValid(String designation) {
        if (designation != null && designation != "") {
            return designation.matches("[a-zA-Z0-9]*") && designation.length() < 40;
        }
        return false;
    }


    public void set_designation(String designation) {
        if (designationIsValid(designation)) {
            this._designation = designation;
        }
    }

    public String get_designation() {
        return this._designation;
    }

    public void set_location(Location location) {
        if (location.checkIfCoordinatesValid()) {
            this._location = location;
        }
    }
    public Location get_location(){
        return this._location;
    }

   public void set_dataTypeDesignation(DataType dataType){
       //inserir método de validação de datatype na classe datatype fora do set;
       this._dataTypeDesignation=dataType;
   }

   public DataType get_dataTypeDesignation(){
        return this._dataTypeDesignation;
   }
}
