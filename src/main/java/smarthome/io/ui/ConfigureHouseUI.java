package smarthome.io.ui;


import org.json.simple.parser.ParseException;
import smarthome.controller.ConfigureHouseCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;

import java.io.IOException;
import java.util.List;

public class ConfigureHouseUI {

    private final ConfigureHouseCTRL ctrl;
    private String idGeoArea;
    private String streetName;
    private String number;
    private String zipCode;
    private String town;
    private String country;
    private double latitude;
    private double longitude;
    private double altitude;


    public ConfigureHouseUI(GAList listOfGA) {
        ctrl = new ConfigureHouseCTRL(listOfGA);
    }


    public void configHouseLocationManually() {
        List<GeographicalAreaDTO> gaListDTO = ctrl.getGAListDTO();
        if (gaListDTO.isEmpty()) {
            System.out.println("There are no Geographical Areas. Please add at least one.");
            UtilsUI.backToMenu();
            return;
        }

        this.selectGA();
        this.addressInput();

        ctrl.configureHouseLocation(idGeoArea, streetName, number, zipCode, town, country, latitude, longitude, altitude);
        System.out.println("Success! The house location has been configured.");

    }


    public void configHouseFromFile() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        List<GeographicalAreaDTO> gaListDTO = ctrl.getGAListDTO();
        if (gaListDTO.isEmpty()) {
            System.out.println("There are no Geographical Areas. Please add at least one.");
            UtilsUI.backToMenu();
            return;
        }
        this.selectGA();
        this.coordinatesInput();
        this.createHouseFromFile();
    }

    private void selectGA() {

        System.out.println("Select the Geographical Area where the house is located:\n" + ctrl.showGAListDTO());
        int indexGA = UtilsUI.requestIntegerInInterval(1, ctrl.getGAListDTO().size(), "Please choose a valid option");
        idGeoArea = ctrl.getIdFromIndex(indexGA);
    }


    private void addressInput() {

        System.out.println("Insert a street name for the house:");
        streetName = UtilsUI.requestText("Only alphanumeric characters are accepted.", "^[A-Za-z0-9 -,.]+$");

        System.out.println("Insert a door number for the house:");
        number = UtilsUI.requestText("Only alphanumeric characters are accepted.", "^[A-Za-z0-9 -,.]+$");

        System.out.println("Insert the zip-code of House (0000-000):");
        zipCode = UtilsUI.requestText("Please, insert a valid zip-code (0000-000).", "[0-9]{4}-[0-9]{3}");

        System.out.println("Insert the village:");
        town = UtilsUI.requestText("Only alphabetic characters are allowed", "^[A-Za-z .]+$");

        System.out.println("Insert the country:");
        country = UtilsUI.requestText("Only alphabetic characters are allowed", "^[A-Za-z .]+$");
        this.coordinatesInput();
    }


    private void coordinatesInput() {
        System.out.println("Insert the latitude of the house [-90º, 90º]:");
        this.latitude = UtilsUI.requestDoubleInInterval(-90, 90, "Latitude must be between [-90º,90º]");
        System.out.println("Insert the longitude of the house [-180º, 180º]:");
        this.longitude = UtilsUI.requestDoubleInInterval(-180, 180, "Latitude must be between [-180º,180º]");
        System.out.println("Insert the altitude of the house (in meters):");
        this.altitude = UtilsUI.requestDoubleInInterval(-12500, 8848, "Altitude must be between [-12.500m, 8848m]");
    }

    private void createHouseFromFile() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {

        ctrl.configureHouseFromFileCTRL(idGeoArea, this.latitude, this.longitude, this.altitude);
        System.out.println("Success! The House has " + ctrl.getRoomListSizeCTRL() + " Rooms, " + ctrl.getGridListSizeCTRL() + " Grids," + " and the following location:\n"                + " | Geographical Area:\n"
                + ctrl.showGAInString()
                + "\n | Address:\n"
                + ctrl.showAddressInString());
        UtilsUI.backToMenu();
    }


}




