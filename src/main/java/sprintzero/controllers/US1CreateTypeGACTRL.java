package sprintzero.controllers;

import sprintzero.model.TypeGA;
import sprintzero.model.TypeGAList;

public class US1CreateTypeGACTRL {

    //Atributos

    private TypeGAList mTypeGAList;


    //Construtor

    public US1CreateTypeGACTRL(TypeGAList inputList) {
        mTypeGAList = inputList;
    }


    //Métodos

    public boolean newTypeGA(String inputType) {
        if (this.typeOfGAIsValid (inputType)) {
            TypeGA typeGA = mTypeGAList.newTypeGA (inputType);
            return mTypeGAList.addTypeGA (typeGA);
        }
        return false;
    }

    /**
     * Validation of the input used in the method newTypeGA
     * @param inputType The input string of the user
     * @return Don't accept the input if empty (spaces)
     */
    public boolean typeOfGAIsValid(String inputType) {
        return inputType != null && !inputType.trim ().isEmpty ();
    }


}
