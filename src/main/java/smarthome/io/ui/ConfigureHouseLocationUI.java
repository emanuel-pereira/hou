package smarthome.io.ui;


import smarthome.controller.ConfigureHouseLocationCTRL;
import smarthome.model.GAList;
import smarthome.model.House;

import java.util.Scanner;

public class ConfigureHouseLocationUI {

    //private GPSValidations validations = new GPSValidations();
    //private String invalidCharacters = "^(?![\\s]).*";

    private ConfigureHouseLocationCTRL mCtrlUS101;
    Scanner read = new Scanner(System.in);
    private int indexGA;
    private String streetName;
    private String zipCode;
    private String town;
    private double latitude;
    private double longitude;
    private double altitude;


    public ConfigureHouseLocationUI(GAList listOfGA, House house) {
        mCtrlUS101 = new ConfigureHouseLocationCTRL(listOfGA, house);
    }


    public void configureHouseLocationUS101() {

        System.out.println("Select the Geographical Area where the house is located:");
        System.out.println(mCtrlUS101.showGAListInString());
        indexGA = UtilsUI.requestIntegerInInterval(1, mCtrlUS101.getGAList().size(), "Please, select a valid Geographical Area.");
        this.addressInput();
    }

    private void addressInput() {


        System.out.println("Insert a street name for the house:");
        streetName = UtilsUI.requestText("Please, insert a valid name. Only alphabetic characters are allowed");

        System.out.println("Insert the zip-code of House:");
        zipCode = UtilsUI.requestText("Please, insert a valid code-zip.", "^(?![\\s]).*");

        System.out.println("Insert the village:");
        town = UtilsUI.requestText("Please, insert a valid village. Only alphabetic characters are allowed");
        this.coordinatesInput();
    }


    private void coordinatesInput() {


        System.out.println("Insert the latitude of the new geographical area:");
        latitude = UtilsUI.requestDouble("Please, insert a valid value.");

        System.out.println("Insert the longitude of the new geographical area:");
        longitude = UtilsUI.requestDouble("Please, insert a valid value.");

        System.out.println("Insert the altitude of the new geographical area:");
        altitude = UtilsUI.requestDouble("Please, insert a valid value");

        mCtrlUS101.configureHouseLocation(indexGA, streetName, zipCode, town, latitude, longitude, altitude);
        System.out.println("Success!The house location has been configured.");
    }


}




