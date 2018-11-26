package Sprint_0;

import java.util.Date;
import java.util.List;

public class Sensor {
    private String _designation;
    private Date _startDate;
    private Location _location;
    private DataType _dataTypeDesignation;
    private List<Reading> _reading;


    /**
     * Constructor requiring to set only a specific designation for any object of type Sensor created
     * @param designation every object of type sensor must have a designation.
     */
    public Sensor(String designation) {
        this._designation = designation;
    }

    /**
     *  Method to check if the string given to designate the sensor meets the criteria defined to be considered a valid designation, namely:
     *  1) designation cannot be empty or null
     *  2) designation must have only alphabetic characters and a maximum length of 40 characters.
     * @param designation
     * @return
     */

    public boolean designationIsValid(String designation) {
        if (designation != null && designation != "") {
            return designation.matches("[a-zA-Z0-9]*") && designation.length() < 40;
        }
        return false;
    }

    public void set_designation(String designation){
        if(designationIsValid(designation)){
            this._designation=designation;
        }
    }
    public String get_designation(){
        return this._designation;
    }

}
