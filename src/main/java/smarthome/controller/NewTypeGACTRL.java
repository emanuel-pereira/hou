package smarthome.controller;

import smarthome.model.TypeGA;

import static smarthome.model.TypeGAList.addTypeGA;
import static smarthome.model.TypeGAList.newTypeGA;

public class NewTypeGACTRL {

    /**
     * Controller constructor
     *
     */
    public NewTypeGACTRL() {
        /*Constructor is supposed to be empty*/
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     *
     * @param inputType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean createTypeGA(String inputType) {
        TypeGA typeGA = newTypeGA(inputType);
        if (typeGA == null)
            return false;
        return addTypeGA(typeGA);
    }

}
