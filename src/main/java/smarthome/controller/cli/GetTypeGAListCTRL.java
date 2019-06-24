package smarthome.controller.cli;

import smarthome.model.TypeGA;

import java.util.List;

import static smarthome.model.TypeGAList.getTypeGAList;

public class GetTypeGAListCTRL {

    /** Constructor of User Story 2. It is used to create a list of types which would be created/added by the user*/
    public GetTypeGAListCTRL() {
        /*Constructor is supposed to be empty*/

    }


    /**
     * Get the content from TypeGAList of the TypeGA created
     * @return List of TypeGA
     */
    public List<TypeGA> getTypeGAListCTRL() {
        return getTypeGAList();
    }


    public String showListInString() {
        List<TypeGA> list = this.getTypeGAListCTRL ();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (TypeGA type : list) {
            result.append (number++);
            result.append (element);
            result.append (type.getType());
            result.append ("\n");
        }
        return result.toString ();
    }


}
