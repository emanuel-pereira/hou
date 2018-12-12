package sprintzero.model;

import java.util.ArrayList;
import java.util.List;

public class GAList {

    private List<GeographicalArea> mGAList;

    //Construtor

    public GAList() {

        mGAList = new ArrayList<>();
    }

    //Métodos

    public GeographicalArea newGA(String inputDesignation, String typeArea, double width, double lenght, double latitude, double longitude, double altitude) {
        return new GeographicalArea(inputDesignation, typeArea, width, lenght, latitude, longitude, altitude);
    }


    public boolean addGA(GeographicalArea inputGA) {
        if (!mGAList.contains(inputGA)) {
            mGAList.add(inputGA);
            return true;
        } else return false;
    }

    public List<GeographicalArea> getGAList() {
        return mGAList;
    }

    /**
     * Method that checks if a specific location is within a Geographical Area
x     * the location is inserted. If no match is found, then this method returns null.
     * @param latitude latitude of the location for which we
     * @param longitude
     * @return
     */
    public GeographicalArea checkIfLocationIsInGAList(double latitude, double longitude) {
        for (int i = 0; i < mGAList.size(); i++) {
            if (mGAList.get(i).locationIsInAG(latitude, longitude) == true) {
                return mGAList.get(i);
            }
        }
        return null;
    }


}
