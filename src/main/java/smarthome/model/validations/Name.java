package smarthome.model.validations;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    String name;

    public Name(String name) {
        setName(name);
    }


    protected Name() {
    }

    /**
     * Method that checks if the parameter name meets the regex criteria. If not, throws an IllegalArgumentException
     * to prevent instantiating a Name object with name attribute as null.
     *
     * @param name String parameter
     * @return true if the name parameter meets the regex criteria, otherwise throws an IllegalArgumentException
     */

    private boolean nameIsValid(String name) {
        if (name.matches("^[A-Za-z][A-Za-z -]+$")) {
            return true;
        }
        throw new IllegalArgumentException("Type must contain a valid alphabetic regular expression.");
    }


    /**
     * Method to set the name attribute of a GeoAreaType instance if the name parameter complies with nameIsValid criteria.
     * If so, converts the name to lowercase before setting up the attribute value.
     *
     * @param name String parameter to be set as a GeoAreaType.
     */
    private void setName(String name) {
        if (nameIsValid(name))
            this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
