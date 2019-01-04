package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.TypeGAList;

import java.util.List;

public class US4ReturnGAsWithThisTypeCTRL {

    private GAList mGAList;
    private TypeGAList mTypeGAList;

    /**US4 constructor*/
    public US4ReturnGAsWithThisTypeCTRL(GAList inputGAList, TypeGAList inputTypeGAList){
            mGAList = inputGAList;
            mTypeGAList = inputTypeGAList;
    }
    /**US4 method that calls  list of GAs from the type that was shown from the existing type list*/
    public List<GeographicalArea> getGAListFromType (int typeAreaIndex) {
        String areaType = mTypeGAList.getTypeGAList().get(typeAreaIndex-1).toString();
        return mGAList.GAFromThisType(areaType);
    }

    /**US4 methos that shows the list of GAs returned by the method in a string*/
    public String showListInString(int typeAreaIndex) {
        List<GeographicalArea> list = this.getGAListFromType(typeAreaIndex);
        StringBuilder result = new StringBuilder();
        String element1 = " - ";
        int number = 1;
        for (GeographicalArea position : list){
            result.append (number++);
            result.append (element1);
            result.append (position.getGeographicalAreaDesignation());
            result.append ("\n");
        }
        return result.toString();
    }

}