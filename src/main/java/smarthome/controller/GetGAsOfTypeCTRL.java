package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.TypeGAList;

import java.util.List;

public class GetGAsOfTypeCTRL {

    private GAList mGAList;
    private TypeGAList mTypeGAList;

    /**
     * US4 constructor
     *
     * @param inputGAList     is the list of GA created on US3
     * @param inputTypeGAList is the list of types that was created on US1 and printed on US2
     */
    public GetGAsOfTypeCTRL(GAList inputGAList, TypeGAList inputTypeGAList) {
        mGAList = inputGAList;
        mTypeGAList = inputTypeGAList;
    }

    /**
     * US4 method that calls the method in the class GAList which creates a new sublist of GAs from the
     * type that was shown from the existing type list.
     *
     * @param typeAreaIndex is a number that matches a position in the list of types from which the user can choose from
     */
    public List<GeographicalArea> getGAListFromType(int typeAreaIndex) {
        String areaType = mTypeGAList.getTypeGAList().get(typeAreaIndex - 1).toString();
        return mGAList.gAFromThisType(areaType);
    }

    /**
     * US4 method that shows the list of GAs returned by the previous method in a string
     *
     * @param typeAreaIndex is a number that matches a position in the list of types from which the user can choose from
     */
    public String showListInString(int typeAreaIndex) {
        List<GeographicalArea> list = this.getGAListFromType(typeAreaIndex);
        StringBuilder result = new StringBuilder();
        String element1 = " - ";
        int number = 1;
        for (GeographicalArea position : list) {
            result.append(number++);
            result.append(element1);
            result.append(position.getGAName());
            result.append("\n");
        }
        return result.toString();
    }

}