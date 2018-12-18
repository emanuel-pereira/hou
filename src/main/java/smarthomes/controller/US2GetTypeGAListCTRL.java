package smarthomes.controller;

import smarthomes.model.TypeGA;
import smarthomes.model.TypeGAList;

import java.util.List;

public class US2GetTypeGAListCTRL {


    private TypeGAList mTypeGAList;


    public US2GetTypeGAListCTRL(TypeGAList inputList) {
        mTypeGAList = inputList;
    }


    /**
     * Get the content from TypeGAList of the TypeGA created
     *
     * @return List of TypeGA
     */
    public List<TypeGA> getTypeGAList() {
        return mTypeGAList.getTypeGAList ();
    }

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
