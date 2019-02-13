package smarthome.controller;

import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

public class NewTypeGACTRL {

    private TypeGAList mTypeGAList;

    /**
     * Controller constructor
     * @param inputList the list object on which the user will be able to create new type of GA's
     */
    public NewTypeGACTRL(TypeGAList inputList) {
        mTypeGAList = inputList;
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     * @param inputType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean newTypeGA(String inputType) {
        TypeGA typeGA = mTypeGAList.newTypeGA (inputType);
        if (typeGA != null)
            return mTypeGAList.addTypeGA (typeGA);
        return false;
    }
}
