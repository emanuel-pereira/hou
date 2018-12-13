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

    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeGA)) {
            return false;
        }
        TypeGA typeOfGA = (TypeGA) o;
        if (this.mTypeGA.equals (typeOfGA.getTypeGA ())) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {

        return Objects.hash (mTypeGA);
    }


    /**
     * When there's the need to show to the user the TypeGA inputted by him (example US2).
     * To do so, the toString method from the parent method Object must be Override to accept that simple String
     * rather then the string representation of the object (name of the class, the at-sign character and the unsigned
     * hexadecimal representation of the hash code of the object).
     * It is recommended that all subclasses override this method.
     */
    @Override
    public String toString() {
        String str = mTypeGA;
        return str;
    }
}





