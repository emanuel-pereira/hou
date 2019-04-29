package smarthome.io.ui;

import smarthome.controller.NewSensorCTRL;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class NewSensorUI {
    private final NewSensorCTRL ctrl;
    private String id;
    private String name;
    private int indexOfSensorType;
    private GregorianCalendar startDate;
    private double latitude;
    private double longitude;
    private double altitude;
    private String unit;
    private int indexOfGA;
    private int indexOfRoom;
    private final ReadingList readingList = new ReadingList();
    private final Scanner read = new Scanner(System.in);
    private boolean isInternal;


    public NewSensorUI(SensorTypeList sensorTypeList, GAList listOfGA) {
        this.ctrl = new NewSensorCTRL(sensorTypeList, listOfGA);
    }

    void checkIfRoomListIsEmpty() {
        if (!this.ctrl.checkIfHouseAsLocation()) {
            System.out.println("Need to configure the house first. Please insert the location of the house.\n");
            return;
        }
        isInternal = true;
        if (this.ctrl.getRoomListSize() == 0) {
            System.out.println("List of Rooms is empty. Please insert at least one Room.");
            return;
        }
        this.checkIfSensorTypeListIsEmpty();
    }

    void checkIfGAListIsEmpty() {
        isInternal = false;
        if (this.ctrl.getGAListSize() == 0) {
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in System Administration.");
            return;
        }
        this.checkIfSensorTypeListIsEmpty();
    }

    private void checkIfSensorTypeListIsEmpty() {
        if (this.ctrl.getSensorTypeListSize() == 0) {
            System.out.println("List of sensor's reading data types is empty. Please insert at least one first.");
            return;
        }
        this.inputName();
    }

    private void inputName() {
        System.out.println("Insert the sensor id");
        this.id = UtilsUI.requestText("The id inputted isn't valid. Only alphanumeric characters are accepted.", "^[a-zA-Z0-9]*$");
        System.out.println("Insert a name for the sensor");
        this.name = UtilsUI.requestText("The name inputted isn't valid. Only alphanumeric characters, spaces and hyphens are accepted.", "[A-Za-z0-9 \\-]*");
        this.inputStartDate();
    }

    private void inputStartDate() {
        System.out.println("Insert the start date for the sensor in YYYY-MM-DD format.");
        this.startDate = UtilsUI.requestDate("The date inputted is invalid. Please insert a valid date in YYYY-MM-DD" +
                "");
        this.selectSensorType();
    }


    private void selectSensorType() {
        System.out.println("Choose a type for the sensor from one of the sensor types below:");
        System.out.println(this.ctrl.showSensorTypeListInString());
        this.indexOfSensorType = UtilsUI.requestIntegerInInterval(1,
                this.ctrl.getSensorTypeListSize(),
                UtilsUI.INSERTVALIDOPTION
                        + this.ctrl.showSensorTypeListInString());
        indexOfSensorType--;
        this.askToAddReadings();
    }


    private void inputReading() {
        System.out.println("Insert the date and time when the reading was made in YYYY-MM-DD HH:MM format:");
        GregorianCalendar date = UtilsUI.requestDateTime("Insert a valid date and time in YYYY-MM-DD HH:MM format");
        System.out.println("Insert the value of the reading:");

        double readingValue = UtilsUI.requestDouble(UtilsUI.insertValidParameter("decimal value"));
        Reading r = new Reading(readingValue, date);
        this.readingList.addReading(r);
        this.askToAddReadings();
    }

    private void askToAddReadings() {
        if (UtilsUI.confirmOption("Do you want to insert readings for the sensor (y/n)?",
                "Please insert only 'Y' or 'N' characters")) {
            this.inputReading();
        }
        inputSensorUnit();
    }

    private void inputSensorUnit() {
        System.out.println("Insert the unit the sensor will read:");
        this.unit = read.nextLine();
        if (!this.isInternal) {
            this.inputGPSLocation();
        }
        if (this.isInternal) {
            this.selectRoom();
        }
    }

    private void selectRoom() {
        System.out.println("Choose the Room for which you want add this sensor, from the list below:");
        System.out.println(ctrl.showRoomListInStr());
        this.indexOfRoom = UtilsUI.requestIntegerInInterval(1, this.ctrl.getRoomListSize(),
                UtilsUI.INSERTVALIDOPTION + this.ctrl.showRoomListInStr());
        indexOfRoom--;
        this.addSensorToRoom();
    }

    private void inputGPSLocation() {
        System.out.println("Insert the latitude of the new sensor [-90º, 90º]:");
        latitude = UtilsUI.requestDoubleInInterval(-90, 90, "Latitude must be between [-90º,90º]");
        System.out.println("Insert the longitude of the new sensor [-180º, 180º]:");
        longitude = UtilsUI.requestDoubleInInterval(-180, 180, "Longitude must be between [-180º,180º]");
        System.out.println("Insert the altitude of the new sensor (in meters):");
        altitude = UtilsUI.requestDoubleInInterval(-12500, 8848, "Altitude must be between [-12.500m, 8848m]");
        this.inputGAOfSensor();
    }

    private void inputGAOfSensor() {
        System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
        System.out.println(this.ctrl.showGAListInString());
        this.indexOfGA = UtilsUI.requestIntegerInInterval(1, this.ctrl.getGAListSize(),
                UtilsUI.INSERTVALIDOPTION + this.ctrl.showGAListInString());
        indexOfGA--;
        this.addSensorToGA();
    }

    private void addSensorToGA() {
        Location geoLocation = new Location(this.latitude, this.longitude, this.altitude);
        this.ctrl.addNewSensorToGA(this.id, this.name, this.startDate, this.indexOfSensorType, this.unit, geoLocation, this.indexOfGA, this.readingList);
        System.out.println("The following geographical area sensor was successfully created: ");
        System.out.println("ID: " + this.id);
        System.out.println("NAME: " + this.ctrl.getGASensorName(this.indexOfGA));
        System.out.println("GEOGRAPHICAL AREA: " + this.ctrl.getGAName(this.indexOfGA));
        System.out.println("START DATE: " + UtilsUI.dateToString(this.ctrl.getGASensorSDate(indexOfGA)));
        System.out.println("TYPE: " + this.ctrl.getGASensorType(this.indexOfGA));
        System.out.println("UNITS: " + this.ctrl.getGASensorUnit(this.indexOfGA));
        System.out.println("LIST OF READINGS:");
        for (
                Reading r : this.readingList.getReadingsList()) {
            System.out.println("[timestamp: " + r.getDateAndTime().getTime() + " value: " + r.returnValueOfReading() + "]");
        }
        System.out.println("GPS LOCATION - [Latitude: " + this.latitude + " | Longitude: " + this.longitude + " | Altitude: " + this.altitude + "]");
    }

    private void addSensorToRoom() {
        this.ctrl.addNewSensorToRoom(this.id, this.name, this.startDate, this.indexOfSensorType, indexOfRoom, unit, readingList);
        System.out.println("The following internal sensor was successfully created: ");
        System.out.println("ID: " + this.id);
        System.out.println("NAME: " + this.ctrl.getInternalSensorName(this.indexOfRoom));
        System.out.println("ROOM: " + this.ctrl.getRoomName(this.indexOfRoom));
        System.out.println("START DATE: " + UtilsUI.dateToString(this.ctrl.getRoomSensorSDate(indexOfRoom)));
        System.out.println("TYPE: " + this.ctrl.getRoomSensorType(this.indexOfRoom));
        System.out.println("UNITS: " + this.ctrl.getRoomSensorUnit(this.indexOfRoom));
        System.out.println("LIST OF READINGS:");
        for (
                Reading r : this.readingList.getReadingsList()) {
            System.out.println("[timestamp: " + r.getDateAndTime().getTime() + " value: " + r.returnValueOfReading() + "]");
        }
    }

    void selectRoomAndList() {
        System.out.println("Choose a room from the list below to list its sensors:");
        System.out.println(ctrl.showRoomListInStr());
        this.indexOfRoom = UtilsUI.requestIntegerInInterval(1, this.ctrl.getRoomListSize(),
                UtilsUI.INSERTVALIDOPTION + this.ctrl.showRoomListInStr());
        indexOfRoom--;
        this.checkRoomSensorListSize();
    }

    private void checkRoomSensorListSize() {
        if (this.ctrl.sensorListInRoomSize(indexOfRoom) == 0) {
            System.out.println("List of sensors in room is empty.\n");
            UtilsUI.backToMenu();
            return;
        }
        System.out.println("The sensors available in room are the following: ");
        System.out.println(this.ctrl.showSensorListInRoom(indexOfRoom));
    }
}

