package pt.ipp.isep.dei.smarthome.controller;

import pt.ipp.isep.dei.smarthome.model.GAList;
import pt.ipp.isep.dei.smarthome.model.GeographicalArea;
import pt.ipp.isep.dei.smarthome.model.TypeGAList;

public class US3CreateGACTRL {


    private GAList mGAList;
    private TypeGAList mTypeGAList;

    /**
     * US3 controller constructor
     * @param GAInputList GA's List where the list methods will be invoked from, the List allows to create GA's and addi to a list of GA's
     * #deprecated a second solution using a GA type from a previously created list was created
     */
    public US3CreateGACTRL(GAList GAInputList) {
        mGAList = GAInputList;
    }

    /**
     * US3 controller constructor which is invoked when by US3UI on start to this is controller two parameters need to be passed:
     * @param GAInputList GA's List where the list methods will be invoked from, the List allows to create GA's and addi to a list of GA's
     * @param GATypeInputList GA Types List is a List of types (eg. city, country, stree) previously created by the user in US1
     */
    public US3CreateGACTRL(GAList GAInputList, TypeGAList GATypeInputList) {
        mGAList = GAInputList;
        mTypeGAList = GATypeInputList;
    }

    /**
     * This newGA method exists to get the data from the users input in the UI in order to create a new GA
     * @param inputDesignation String GA designation
     * @param typeArea String GA type (eg. city, street)
     * @param width double width of the GA being created
     * @param length double length of the GA being created
     * @param latitude double latitude of the GA being created
     * @param longitude double longitude of the GA being created
     * @param altitude double altitude of the GA being created
     * @return the prior information is used to invoke the newGA method from the GA's List class making a request to create a new GA with the users input
     * #deprecated a second solution using a GA type from a previously created list was created, where instead of receiving the GAType input from a String it is received as an index from the US2 List
     */
    public boolean newGA(String inputDesignation, String typeArea, double width, double length, double latitude, double longitude, double altitude) {
        GeographicalArea GA = mGAList.newGA(inputDesignation, typeArea, width, length, latitude, longitude, altitude);
        return mGAList.addGA(GA);
    }

    /**
     * This newGA2 method exists to get the data from the users input in the UI in order to create a new GA.
     * Here, instead of receiving the GAType input from a String it is received as an index from the US2 List
     * @param inputDesignation String GA designation
     * @param typeAreaIndex int GA type as an index from a list (eg. 1 - city, 2 - street)
     * @param width double width of the GA being created
     * @param length double length of the GA being created
     * @param latitude double latitude of the GA being created
     * @param longitude double longitude of the GA being created
     * @param altitude double altitude of the GA being created
     * @return the prior information is used to invoke the newGA method from the GA's List class making a request to create a new GA with the users input
     */
    public boolean newGA2(String inputDesignation, int typeAreaIndex, double width, double length, double latitude, double longitude, double altitude) {
        String AreaType = mTypeGAList.getTypeGAList().get(typeAreaIndex-1).toString();
        GeographicalArea GA = mGAList.newGA(inputDesignation, AreaType, width, length, latitude, longitude, altitude);
        return mGAList.addGA(GA);
    }

}
