package smarthome.model;


import smarthome.model.validations.Name;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Name type;

    public SensorType() {
    }

    /**
     * Constructor method that defines a designation for a type of sensor
     *
     * @param type - String that names the type of sensor
     */
    public SensorType(String type) {
        setType(type);
    }


    /**
     * Method to set the type attribute of a SensorType instance if the type parameter complies with nameIsValid criteria.
     * If so, converts the type to lowercase before setting up the attribute value.
     *
     * @param type String parameter to be set as a sensor type.
     */
    public void setType(String type) {
        this.type = new Name(type);
    }

    /**
     * Getter method for sensor type
     *
     * @return the sensor type
     */
    public Name getType() {
        return this.type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SensorType))
            return false;
        SensorType sensorType = (SensorType) o;
        return this.type.equals(sensorType.getType());
    }

    /**
     * hashCode() just returns the object's address in memory
     * This method OverRides the hashCode method defined by class
     * Object does also return distinct integers for distinct objects.
     * This is typically implemented by converting the internal
     * address of the object into an integer
     *
     * @return hashcode int value of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.type);
    }
}


