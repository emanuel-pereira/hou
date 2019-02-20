package smarthome.io.ui;


import smarthome.controller.NewSensorCTRL;
import smarthome.model.*;

import java.util.*;

public class NewSensorUI {
    private NewSensorCTRL ctrl;
    private String name;
    private int indexOfSensorType;
    private GregorianCalendar startDate;
    private double latitude;
    private double longitude;
    private double altitude;
    private String unit;
    private int indexOfGA;
    private ReadingList readingList = new ReadingList();
    private Scanner read = new Scanner(System.in);
    private boolean condition;


    public NewSensorUI(SensorTypeList sensorTypeList, GAList listOfGA) {
        this.ctrl = new NewSensorCTRL(sensorTypeList, listOfGA);

    }

    public void checkIfGAListIsEmpty() {

        if (this.ctrl.getGAListSize() == 0) {
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
            return;
        }
        this.checkIfSensorTypeListIsEmpty();
    }

    public void checkIfSensorTypeListIsEmpty() {
        if (this.ctrl.getSensorTypeListSize() == 0) {
            System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
            return;
        }
        this.inputName();
    }

    public void inputName() {
        System.out.println("Insert a name for the sensor");
        this.name = UtilsUI.requestText("The name inputted isn't valid. Only alphanumeric characters, spaces and hyphens are accepted.");
        this.inputStartDate();
    }

    public void inputStartDate() {
        System.out.println("Insert the start date for the sensor in yyyy-MM-dd format.");
        this.startDate = UtilsUI.requestDate("The date inputted is invalid. Please insert a valid date in yyyy-MM-dd");
        this.selectSensorType();
    }


    public void selectSensorType() {
        System.out.println("Choose a type for the sensor from one of the sensor types below:");
        System.out.println(this.ctrl.showSensorTypeListInString());
        this.indexOfSensorType = UtilsUI.requestIntegerInInterval(1, this.ctrl.getSensorTypeListSize(), "Please insert a valid input.\n"+this.ctrl.showSensorTypeListInString());
        indexOfSensorType--;
        System.out.println("Please fill in the following fields to add at least a reading to a sensor.");
        this.inputReading();
    }


    public void inputReading() {
        System.out.println("Insert the date and time when the reading was made in yyyy-MM-dd hh:mm format:");
        GregorianCalendar date = UtilsUI.requestDateTime("Insert a valid date and time in yyyy-MM-dd hh:mm format");
        System.out.println("Insert the value of the reading:");

        double readingValue =  UtilsUI.requestDouble(UtilsUI.insertValidParameter("decimal value"));
        Reading r = this.readingList.newReading(readingValue, date);
        this.readingList.addReading(r);
        this.askToAddNewReadings();
    }

    public void askToAddNewReadings() {
        String option;
        System.out.println("Do you want to insert new readings for the sensor(y/n)?");
        option = read.nextLine();
        if (option.matches("n")) {
            inputSensorUnit();
        }
        if (option.matches("y")) {
            this.inputReading();
        }
    }

    public void inputSensorUnit() {
        System.out.println("Insert the unit the sensor will read:");
        this.unit = read.nextLine();
        this.inputGPSLocation();
    }

    public void inputGPSLocation() {
        getLatitude();
        getLongitude();
        getAltitude();
    }

    private void getLatitude() {
        this.condition=true;
        while (this.condition) {
            try {
                System.out.println("Insert the latitude of the new sensor:");
                this.latitude=UtilsUI.requestDouble("Input invalid. Please insert a valid latitude.");
                if (this.ctrl.latitudeIsValid(this.latitude)) {
                    this.condition=false;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getLongitude() {
        this.condition=true;
        while (this.condition) {
            try {
                System.out.println("Insert the longitude of the new sensor:");
                this.longitude=UtilsUI.requestDouble("Input invalid. Please insert a valid longitude.");
                if (this.ctrl.longitudeIsValid(this.longitude))
                    this.condition=false;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getAltitude() {
        this.condition=true;
        while (this.condition) {
            try {
                System.out.println("Insert the altitude of the new sensor:");
                this.altitude = UtilsUI.requestDouble("Input invalid. Please insert a valid altitude.");
                if (this.ctrl.altitudeIsValid(this.altitude))
                    this.inputGAOfSensor();
                this.condition=false;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void inputGAOfSensor() {
        System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
        System.out.println(this.ctrl.showGAListInString());
        this.indexOfGA = UtilsUI.requestIntegerInInterval(1,this.ctrl.getGAListSize(),"Please insert a valid option.\n"+this.ctrl.showGAListInString());
        indexOfGA--;
        this.addSensorToGA();
    }

    public void addSensorToGA() {
        Location geoLocation= new Location(this.latitude,this.longitude,this.altitude);
        this.ctrl.addNewSensorToGA(this.name, this.startDate, this.indexOfSensorType, this.unit, geoLocation, this.indexOfGA, this.readingList);
        System.out.println("The following sensor was successfully created: ");
        System.out.println("NAME: " + this.ctrl.getSensorName(this.indexOfGA));
        System.out.println("GEOGRAPHICAL AREA: " + this.ctrl.getGAName(this.indexOfGA));
        System.out.println("START DATE: "+UtilsUI.dateToString(this.ctrl.getStartDate(indexOfGA)));
        System.out.println("TYPE: " + this.ctrl.getSensorType(this.indexOfGA));
        System.out.println("UNITS: " + this.ctrl.getUnit(this.indexOfGA));
        System.out.println("LIST OF READINGS:");
        for (
                Reading r : this.readingList.getReadingList()) {
            System.out.println("[timestamp: " + r.getDateAndTime().getTime() + " value: " + r.returnValueOfReading() + "]");
        }
        System.out.println("GPS LOCATION - [Latitude: " + this.latitude + " | Longitude: " + this.longitude + " | Altitude: " + this.altitude + "]");
    }
}

