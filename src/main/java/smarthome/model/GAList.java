package smarthome.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> mGAList;

    /**
     * Constructor method to set the attribute of the GA's List as an ArrayList
     */
    public GAList() {
        mGAList = new ArrayList<>();
    }

    /**
     * Method Constructor to set up new Geographical Areas
     *
     * @param inputDesignation GA String designation
     * @param typeArea         GA Type String designation
     * @param width            GA width
     * @param height           GA height
     * @param latitude         GA latitude coordinate
     * @param longitude        GA longitude coordinate
     * @param altitude         GA altitude coordinate
     * @return the newly created Geographical Area
     */
    public GeographicalArea newGA(String inputID, String inputDesignation, String typeArea, double width, double height, double latitude, double longitude, double altitude) {
        return new GeographicalArea(inputID, inputDesignation, typeArea, width, height, latitude, longitude, altitude);
    }

    /**
     * Method to add a new GA into the GA's List
     * Before the placement the method will first verify the existence of a similar GA in the list
     * If none is found the method truly adds this new one to the list
     *
     * @param inputGA Geographical Area to be added
     * @return boolean value, true if correctly added, false if not added
     */
    public boolean addGA(GeographicalArea inputGA) {
        if (mGAList.contains(inputGA))
            return false;
        return mGAList.add(inputGA);
    }

    /**
     * Method similar to the get method for Lists but as the List is private in the class it is
     * needed to exist a method that publicly returns the List of GA's to other methods to read
     *
     * @return List of Geographical Areas
     */
    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

    /**
     * Method to get a specific Geographical Area in index position i
     *
     * @param i index position of the GA's List
     * @return the specific requested Geographical Area
     */
    public GeographicalArea get(int i) {
        return this.mGAList.get(i);
    }






    /**
     * US04
     * Method that returns a list of Geographical Areas of a certain Type.
     * The user inputs the TypeGA he wishes to obtain, for example, "street" and will receive a list of GAs from that Type.
     * A 'for each' was used for simpler and easier list iteration.
     *
     * @param inputTypeGA string inserted by user
     */
    public List<GeographicalArea> GAFromThisType(String inputTypeGA) {
        List<GeographicalArea> GAFromTypeList = new ArrayList<>();
        for (GeographicalArea ga : mGAList) {
            if (ga.getGeographicalAreaType().equals(inputTypeGA)) {
                GAFromTypeList.add(ga);
            }
        }
        return GAFromTypeList;
    }
}
