package sprintzero.model;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class TypeGA {

    //Atributos

    private String mTypeGA;


    //Construtores


    public TypeGA(String inputTypeGA) {
        this.mTypeGA = inputTypeGA;
    }

    //Métodos

    public String getTypeGA() {
        return this.mTypeGA;
    }


    //Método Equals (para ser usado na TypeGAList)

    /**
     *  When two objects (o1 and o2) with the same data are compare, the result is that they are different objects.
     *  If there's the need to check for equality of values inside the objects the inherit equals method need to be override.
     *  First: check if the argument is a reference to this object.
     *  Second: check if o is an instance of TypeGA or not, it allows for subclasses to be equal.
     *  Final: typecast o to TypeGA so that we can compare data member (cast the argument to the correct type so that
     *  we can compare data members). Then compare the data members and return accordingly.
     * @param o Any kind og object
     * @return If the object is compared with itself then return true.Check if the argument has the correct type. If not, return false.
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
        if (this.mTypeGA.equals (typeOfGA.getTypeGA ())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Equal objects may get different hash-values, so when equal() is override, the hash value must also be override.
     * @return Equal objects must produce the same hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash (mTypeGA);
    }


    /**
     * When there's the need to show to the user the TypeGA inputted by him (example US2).
     * To do so, the toString method from the parent method Object must be Override to accept that simple String
     * rather then the string representation of the object (name of the class, the at-sign character and the unsigned
     * hexadecimal representation of the hash code of the object).
     */
    @Override
    public String toString() {
        String str = mTypeGA;
        return str;
    }
}





