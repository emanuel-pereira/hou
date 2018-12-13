package sprintzero.controllers;

import sprintzero.model.TypeGA;
import sprintzero.model.TypeGAList;

import java.util.List;

public class US2GetTypeGAListCTRL {

    //Atributos

    private TypeGAList mTypeGAList;


    //Construtor

    public US2GetTypeGAListCTRL(TypeGAList inputList) {
        mTypeGAList = inputList;
    }


    /**
     * Get the content of the list
     *
     * @return
     */
    public List<TypeGA> getTypeGAList() {
        return mTypeGAList.getGAList ();
    }


}
