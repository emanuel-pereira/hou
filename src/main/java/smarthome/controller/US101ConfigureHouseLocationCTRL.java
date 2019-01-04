package smarthome.controller;

import smarthome.model.*;

import java.util.List;


public class US101ConfigureHouseLocationCTRL {

    private GAList mGAList;
    private House mHouse;


    public US101ConfigureHouseLocationCTRL(GAList listOfGA, House house) {
        mGAList = listOfGA;
        mHouse = house;
    }

    public List<GeographicalArea> getGAList() {
        return mGAList.getGAList();
    }

    public String showGAListInString() {
        List<GeographicalArea> list = mGAList.getGAList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalArea ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getGeographicalAreaDesignation());
            result.append("\n");
        }
        return result.toString();
    }


    public boolean configureHouseLocation(int indexOfGA, String streetName, String zipCode, String town,  double latitude, double longitude, double altitude) {
        GeographicalArea ga = mGAList.getGAList().get(indexOfGA-1);
        mHouse.setHouseGA(ga);
        mHouse.setHouseAddress(streetName, zipCode, town, latitude, longitude, altitude);
        return mHouse.getAddress() != null;

    }
}
