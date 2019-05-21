package smarthome.controller.cli;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.TypeGA;

import java.util.List;

import static smarthome.model.TypeGAList.getTypeGAList;

public class GetGAsOfTypeCTRL {

    private final GAList gaList;

    /**
     * US4 constructor
     *
     * @param inputGAList     is the list of GA created on US3
     */
    public GetGAsOfTypeCTRL(GAList inputGAList) {
        gaList = inputGAList;
    }

    /**
     * US4 method that calls the method in the class GAList which creates a new sublist of GAs from the
     * type that was shown from the existing type list.
     *
     * @param typeAreaIndex is a number that matches a position in the list of types from which the user can choose from
     */
    public List<GeographicalArea> getGAListFromType(int typeAreaIndex) {
        String areaType = getTypeGAList().get(typeAreaIndex - 1).getType();
        return gaList.gAFromThisType(areaType);
    }

    //FIXME remove this method and implement alternative solution using UI.showList method
    //FIXME duplicate method across other classes methods
    public String showListOfTypeGA() {
        List<TypeGA> list = getTypeGAList();
        StringBuilder result = new StringBuilder ();
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


    /**
     * US4 method that shows the list of GAs returned by the previous method in a string
     *
     * @param typeAreaIndex is a number that matches a position in the list of types from which the user can choose from
     */
    public String showListGAFromType(int typeAreaIndex) {
        List<GeographicalArea> list = this.getGAListFromType(typeAreaIndex);
        StringBuilder result = new StringBuilder();
        String element1 = " - ";
        int number = 1;
        for (GeographicalArea position : list) {
            result.append(number++);
            result.append(element1);
            result.append(position.getDesignation());
            result.append("\n");
        }
        return result.toString();
    }

}