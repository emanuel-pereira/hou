package smarthome.io.ui;


import smarthome.controller.US101ConfigureHouseLocationCTRL;
import smarthome.model.GAList;
import smarthome.model.GPSValidations;
import smarthome.model.House;

import java.util.Scanner;

public class US101ConfigureHouseLocationUI {

    private US101ConfigureHouseLocationCTRL mCtrlUS101;

    /*
        US101: As Administrator, I want to configure the location of the house

        1. The user selects a GA, from a list of GAs where a House is located in
        2. The user writes an address, a zip code and GPS coordinates
        3. A method validates if the GPS coordinates are included in the GA (already exists)
        4. Message of success/error
   */


    public US101ConfigureHouseLocationUI(GAList listOfGA, House house) {
        mCtrlUS101 = new US101ConfigureHouseLocationCTRL(listOfGA, house);
    }


    Scanner read = new Scanner(System.in);
    GPSValidations validations = new GPSValidations();


    public void configureHouseLocationUS101() {

        System.out.println("Select the Geographical Area where the house is located:");
        System.out.println(mCtrlUS101.showGAListInString());

        int indexGA;
        while (true) {
            Scanner read1 = new Scanner(System.in);
            indexGA = read1.nextInt();
            if (indexGA > mCtrlUS101.getGAList().size())
                System.out.println("Please insert a valid option \n.");
            else
                break;
        }

        String streetName;
        while (true) {
            System.out.println("Insert a street name for the house:");
            streetName = streetNameIsValid();
            if (streetName != null)
                break;
            else
                System.out.println("Please insert a valid street name");
        }
        String zipCode;
        while (true) {
            System.out.println("Insert the zip-code:");
            zipCode = zipCodeIsValid();
            if (zipCode != null)
                break;
        }
        String town;
        while (true) {
            System.out.println("Insert the town:");
            town = townIsValid();
            if (town != null)
                break;
        }

        double latitude;
        double longitude;
        double altitude;

        while (true) {
            try {
                System.out.println("Insert the latitude of the new geographical area:");
                latitude = read.nextDouble();
                if (validations.latitudeIsValid(latitude))
                    break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Insert the longitude of the new geographical area:");
                longitude = read.nextDouble();
                if (validations.longitudeIsValid(longitude))
                    break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Insert the altitude of the new geographical area:");
                altitude = read.nextDouble();
                if (validations.altitudeIsValid(altitude))
                    break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (mCtrlUS101.configureHouseLocation(indexGA, streetName, zipCode, town, latitude, longitude, altitude)) {
            System.out.println("Success!The house location has been configured.");
        } else {
            System.out.println("Fail! Please try again.");
        }
    }


    public String streetNameIsValid() {
        String streetName = read.nextLine();
        if (streetName == null || streetName.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted");
            return null;
        }
        if (!streetName.matches("^(?![\\s]).*")) {
            System.out.println("Please start with words.");
            return null;
        }
        return streetName;
    }

    public String zipCodeIsValid() {
        String zipCode = read.nextLine();
        if (zipCode == null || zipCode.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!zipCode.matches("^(?![\\s]).*")) {
            System.out.println("Please insert only alphanumeric characters.");
            return null;
        }
        return zipCode;
    }

    public String townIsValid() {
        String town = read.nextLine();
        if (town == null || town.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!town.matches("^(?![\\s]).*")) {
            System.out.println("Please start with words.");
            return null;
        }
        return town;
    }
}
