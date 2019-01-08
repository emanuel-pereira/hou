package smarthome.controller;

import smarthome.model.TypeGA;
import smarthome.model.TypeGAList;

import java.util.List;

public class US2GetTypeGAListCTRL {

    private TypeGAList mTypeGAList;

    /** Constructor of User Story 2. It is used to create a list of types which would be created/added by the user*/
    public US2GetTypeGAListCTRL(TypeGAList inputList) {
        mTypeGAList = inputList;
    }


    /**
     * Get the content from TypeGAList of the TypeGA created
     * @return List of TypeGA
     */
    public List<TypeGA> getTypeGAList() {
        return mTypeGAList.getTypeGAList ();
    }

    /**Method that shows/prints the list that is originated by the previous method(which is called on line 29) */
    public String showListInString() {
        List<TypeGA> list = this.getTypeGAList ();
        StringBuilder result = new StringBuilder ();
        String element = " - ";
        int number = 1;
        for (TypeGA position : list) {
            result.append (number++);
            result.append (element);
            result.append (position);
            result.append ("\n");
        }
        return result.toString ();
    }


}
