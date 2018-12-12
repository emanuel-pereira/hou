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
        TypeGA typeGA = mTypeGAList.newTypeGA (inputType);
        if (typeGA != null)
            return mTypeGAList.addTypeGA (typeGA);
        else
            return false;
    }


}
