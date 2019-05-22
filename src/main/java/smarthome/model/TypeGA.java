package smarthome.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.Contract;
import smarthome.model.validations.Name;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonSerialize
public class TypeGA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name type;

    public TypeGA() {
    }

    public TypeGA(String newType) {
        setType(newType);
    }


    /**
     * When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     * If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     * First: check if the argument is a reference to this object.
     * Second: check if o is an instance of TypeGA or not, it allows for subclasses to be equal.
     * Final: typecast o to TypeGA so that we can compare data member (cast the argument to the correct type so that
     * we can compare data members). Then compare the data members and return accordingly.
     *
     * @param o Any kind of object
     * @return If the object is compared with itself then return true. Check if the argument has the correct type. If not, return false.
     * Check if that field of the argument matches the corresponding field of this object.
     */
    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        if (this == o) { //
            return true;
        }
        if (!(o instanceof TypeGA)) {
            return false;
        }
        TypeGA typeOfGA = (TypeGA) o; //
        return this.type.equals(typeOfGA.getType());
    }


    /**
     * Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     *
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.type);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type.getName();
    }

    public void setType(String newtype) {
        this.type = new Name(newtype);
    }
}





