package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;

import java.util.List;

public class ListParentGACTRL {

    private GAList mGAList;

    /**
     * Constructor method to set up the attributes for the class
     *
     * @param gaInputList GA's List for the class to work on
     */
    public ListParentGACTRL(GAList gaInputList) {
        mGAList = gaInputList;
    }

    /**
     * Method that will retrieve the Parent GA from one selected from a List
     *
     * @param inputIndex List index that represents the GA for which we will
     *                   look for the Parent GA
     * @return if a Parent GA is found, it's designation will be retrieved,
     * else a null value will be returned
     */
    public String isParentOf(int inputIndex) {
        GeographicalArea parentGA = mGAList.getGAList().get(inputIndex - 1).getGeographicalParentGA();
        StringBuilder parents = new StringBuilder();
        while (parentGA != null) { //while there is a parent GA
            parents.append(parentGA.getGAName()); //append parent GA to list
            if (parentGA.getGeographicalParentGA() != null) {
                parents.append(", "); //append parent GA to list
                parentGA = parentGA.getGeographicalParentGA();
            } //next/upper parentGA
            else break;
        }
        return parents.toString(); //return direct/indirect parents GA's list as String
    }

    /**
     * Method to return the GA's List size in int form
     *
     * @return int parameter size of list, if 0/null the list is empty
     * if the list is not empty then the showListInString() method will be called next
     */
    public int getGAListSize() {
        return mGAList.getGAList().size();
    }

    /**
     * This method only occurs if the previous one return a value different than zero/null
     * which means the GA's List is not empty. In this method we will return the GA's List
     * in an user friendly manner (ordered list) for it to select one to get its parent GA
     *
     * @return List of GA's designations as a number ordered List
     */
    public String showListInString() {
        List<GeographicalArea> gaList = mGAList.getGAList();
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (GeographicalArea GA : gaList) {
            result.append(number++);
            result.append(" - ");
            result.append(GA.getGAName());
            result.append("\n");
        }
        return result.toString();
    }
}
