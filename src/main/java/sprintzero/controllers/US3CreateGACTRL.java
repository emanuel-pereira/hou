package sprintzero.controllers;

import sprintzero.model.GAList;
import sprintzero.model.GeographicalArea;

public class US3CreateGACTRL {

    //Atributos

    private GAList mGAList;

    //Construtor

    public US3CreateGACTRL(GAList inputList) {
        mGAList = inputList;
    }

    //Métodos

    public boolean newGA(String inputDesignation, String typeArea, double width, double lenght, double latitude, double longitude, double altitude) {
        GeographicalArea GA = mGAList.newGA(inputDesignation, typeArea, width, lenght, latitude, longitude, altitude);
        return mGAList.addGA(GA);
    }

}
