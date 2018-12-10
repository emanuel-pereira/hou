package sprintzero.controllers;

import sprintzero.model.TypeGAList;
import sprintzero.model.TypeGA;

public class US1CreateTypeGACTRL {

    //Atributos

    private TypeGAList mTypeGAList;


    //Construtor

    public US1CreateTypeGACTRL(TypeGAList inputList){
        mTypeGAList = inputList;
    }


    //Métodos

    public boolean newTypeGA(String inputType){
        TypeGA typeGA = mTypeGAList.newTypeGA (inputType);
        return mTypeGAList.addTypeGA (typeGA);
    }



}
