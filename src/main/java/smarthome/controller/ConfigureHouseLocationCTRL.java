package smarthome.controller;

import smarthome.model.*;

import java.util.List;


public class ConfigureHouseLocationCTRL {

    private GAList gaList;
    private House house;


    public ConfigureHouseLocationCTRL(GAList listOfGA, House house) {
        gaList = listOfGA;
        this.house = house;
    }

    public List<GeographicalArea> getGAList() {
        return gaList.getGAList();
    }

    public String showGAList() {
        List<GeographicalArea> list = gaList.getGAList();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalArea ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getGAName());
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @return the number of elements in the geographical areas list as an integer value
     */
    public int getGAListSize() {
        return this.gaList.size();
    }


    public boolean configureHouseLocation(int indexOfGA, String streetName, String zipCode, String town,  double latitude, double longitude, double altitude) {
        GeographicalArea ga = gaList.getGAList().get(indexOfGA-1);
        house.setHouseGA(ga);
        house.setHouseAddress(streetName, zipCode, town, latitude, longitude, altitude);
        return house.getAddress() != null;

    }
}
