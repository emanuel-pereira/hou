package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class TypeGAList {

    //Atributos

    private List<TypeGA> mTypeGA;


    //Construtor

    public TypeGAList() {
        mTypeGA = new ArrayList<> ();
    }

    //Métodos

    public TypeGA newTypeGA(String inputName) {
        if (this.typeOfGAIsValid (inputName))
            return new TypeGA (inputName);
        return null;
    }


    public boolean addTypeGA(TypeGA inputType) {
        if (!mTypeGA.contains (inputType)) {
            mTypeGA.add (inputType);
            return true;

        } else return false;
    }

    public List<TypeGA> getGAList() {
        return mTypeGA;
    }

    /**
     * Validation of the input used in the method newTypeGA
     *
     * @param inputType The input string of the user
     * @return Don't accept the input if empty (spaces)
     */
    public boolean typeOfGAIsValid(String inputType) {
        return inputType != null && !inputType.trim ().isEmpty ();
    }

}
