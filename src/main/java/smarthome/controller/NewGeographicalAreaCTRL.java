package smarthome.controller;

import org.apache.log4j.Logger;
import smarthome.model.*;
import smarthome.repository.Repositories;

import java.util.List;

import static smarthome.model.TypeGAList.*;

public class NewGeographicalAreaCTRL {

    private final GAList gaList;
    static final Logger log = Logger.getLogger(NewGeographicalAreaCTRL.class);


    /**
     * US3 controller constructor which is invoked when by US3UI on start to this is controller two parameters need to be passed:
     *
     * @param gaInputList     GA's List where the list methods will be invoked from, the List allows to create GA's and addi to a list of GA's
     */
    public NewGeographicalAreaCTRL(GAList gaInputList) {
        this.gaList = gaInputList;
    }

    /**
     * This newGA method exists to get the data from the users input in the UI in order to create a new GA
     *
     * @param inputDesignation String GA designation
     * @param typeGAIndex      integer value representing the type of GA in the index position of the type of GA List.
     * @param occupationArea   occupation area of the geographical area as a result of multiplying the length and the width inputs
     * @param location         central location of the Geographical Area represented by GPS coordinates
     * @return the prior information is used to invoke the newGA method from the GA's List class making a request to create a new GA with the users input
     */
    public boolean newGA(String id, String inputDesignation, int typeGAIndex, OccupationArea occupationArea, Location location) {
        TypeGA typeGA = get(typeGAIndex);
        GeographicalArea ga = this.gaList.newGA(id, inputDesignation, typeGA, occupationArea, location);
        if (!this.gaList.addGA(ga)) {
            return false;
        } else {
            //Repository call
            try {
                Repositories.saveGA(ga);
            } catch (NullPointerException e) {
                log.warn("Repository unreachable");
            }
            return true;
        }
    }


    /**
     * @return an integer value correspondent to the number of elements in the type of geographical area list.
     */
    public int typeGAListSize() {
        return size();
    }


    /**
     * Method that shows the list of types of geographical areas in a string format
     */
    public String showTypeGAListInStr() {
        List<TypeGA> list = getTypeGAList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (TypeGA position : list) {
            result.append(number++);
            result.append(element);
            result.append(position);
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @return the created geographical area name
     */
    public String getGAName() {
        GeographicalArea createdGA = this.gaList.getLastGA();
        return createdGA.getGAName();
    }

    /**
     * @return the type of GA as string of the created Geographical Area
     */
    public String getGAType() {
        GeographicalArea createdGA = this.gaList.getLastGA();
        return createdGA.getTypeName();
    }

}
