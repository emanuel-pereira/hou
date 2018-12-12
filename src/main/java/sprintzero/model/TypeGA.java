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
}





