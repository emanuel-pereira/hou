package smarthome.io.ui;


import smarthome.controller.ConfigureHouseCTRL;
import smarthome.model.GAList;

public class ConfigureHouseUI {

    private ConfigureHouseCTRL ctrl;
    private int indexGA;
    private String streetName;
    private String number;
    private String zipCode;
    private String town;
    private String country;


    public ConfigureHouseUI(GAList listOfGA) {
        ctrl = new ConfigureHouseCTRL(listOfGA);
    }

    public void checkIfGAListIsEmpty() {
        if (this.ctrl.getGAListSize() == 0) {
            System.out.println("List of Geographical Areas is empty. Please insert at least one first.");
            return;
        }
        this.selectGA();
    }

    private void selectGA() {

        System.out.println("Select the Geographical Area where the house is located:");
        System.out.println(ctrl.showGAListDTO());
        indexGA = UtilsUI.requestIntegerInInterval(1, ctrl.getGAListDTO().size(), "Please, select a valid Geographical Area.");
        this.addressInput();
    }



    private void addressInput() {


        System.out.println("Insert a street name for the house:");
        streetName = UtilsUI.requestText("Only alphanumeric characters are accepted.", "^[A-Za-z0-9 -,.]+$");

        System.out.println("Insert a door number for the house:");
        number = UtilsUI.requestText("Only alphanumeric characters are accepted.", "^[A-Za-z0-9 -,.]+$");

        System.out.println("Insert the zip-code of House:");
        zipCode = UtilsUI.requestText("Please, insert a valid zip-code.", "[0-9]{4}-[0-9]{3}");

        System.out.println("Insert the village:");
        town = UtilsUI.requestText("Only alphabetic characters are allowed","^[A-Za-z .]+$");

        System.out.println("Insert the country:");
        country = UtilsUI.requestText("Only alphabetic characters are allowed","^[A-Za-z .]+$");
        this.coordinatesInput();
    }


    private void coordinatesInput() {
        System.out.println("Insert the latitude of the house [-90º, 90º]:");
        double latitude = UtilsUI.requestDoubleInInterval(-90, 90, "Latitude must be between [-90º,90º]");
        System.out.println("Insert the longitude of the house [-180º, 180º]:");
        double longitude = UtilsUI.requestDoubleInInterval(-180, 180, "Latitude must be between [-180º,180º]");
        System.out.println("Insert the altitude of the house (in meters):");
        double altitude = UtilsUI.requestDoubleInInterval(-12500, 8848, "Altitude must be between [-12.500m, 8848m]");
        ctrl.configureHouseLocation(indexGA, streetName, number, zipCode, town, country, latitude, longitude, altitude);
        System.out.println("Success! The house location has been configured.");
    }


}




