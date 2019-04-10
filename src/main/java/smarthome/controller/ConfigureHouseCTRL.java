package smarthome.controller;

import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.House;

import java.util.List;

import static smarthome.model.House.*;


public class ConfigureHouseCTRL {

    private GAList gaList;


    public ConfigureHouseCTRL(GAList listOfGA) {
        gaList = listOfGA;
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
        setHouseGA(ga);
        setHouseAddress(streetName, zipCode, town, latitude, longitude, altitude);
        return getAddress() != null;

    }
}
