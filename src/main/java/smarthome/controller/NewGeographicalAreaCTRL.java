package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.TypeGAList;

public class NewGeographicalAreaCTRL {

    private GAList mGAList;
    private TypeGAList mTypeGAList;

    /**
     * US3 controller constructor
     * @param gaInputList GA's List where the list methods will be invoked from, the List allows to create GA's and addi to a list of GA's
     * #deprecated a second solution using a GA type from a previously created list was created
     */
    public NewGeographicalAreaCTRL(GAList gaInputList) {
        mGAList = gaInputList;
    }

    /**
     * US3 controller constructor which is invoked when by US3UI on start to this is controller two parameters need to be passed:
     * @param gaInputList GA's List where the list methods will be invoked from, the List allows to create GA's and addi to a list of GA's
     * @param gaTypeInputList GA Types List is a List of types (eg. city, country, stree) previously created by the user in US1
     */
    public NewGeographicalAreaCTRL(GAList gaInputList, TypeGAList gaTypeInputList) {
        mGAList = gaInputList;
        mTypeGAList = gaTypeInputList;
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
    public boolean newGA(String id, String inputDesignation, String typeArea, double width, double length, double latitude, double longitude, double altitude) {
        GeographicalArea ga = mGAList.newGA(id, inputDesignation, typeArea, width, length, latitude, longitude, altitude);
        return mGAList.addGA(ga);
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
    public boolean newGA2(String id, String inputDesignation, int typeAreaIndex, double width, double length,
                          double latitude, double longitude, double altitude) {
        String areaType = mTypeGAList.getTypeGAList().get(typeAreaIndex-1).toString();
        GeographicalArea ga = mGAList.newGA(id, inputDesignation, areaType, width, length, latitude, longitude, altitude);
        return mGAList.addGA(ga);
    }

}
