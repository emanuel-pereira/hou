package smarthome.io.ui;


import smarthome.controller.US6CreateSensorCTRL;
import smarthome.model.*;

import java.util.*;

public class US6CreateSensorUI {
    private GAList mGAList;
    private SensorTypeList mSensorTypeList;
    private US6CreateSensorCTRL mCtrl;

    //
    private String mName;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mIndexOfSensorType;
    private GregorianCalendar mCalendar;
    private double mLatitude;
    private double mLongitude;
    private double mAltitude;
    private String mUnit;
    private int mIndexOfGA;
    //to store list of readings inputted by the user before sensor creation
    private ReadingList mReadingList = new ReadingList();
    private Scanner read = new Scanner(System.in);


    public US6CreateSensorUI(SensorTypeList sensorTypeList, GAList listOfGA) {
        mCtrl = new US6CreateSensorCTRL(sensorTypeList, listOfGA);
        mGAList = listOfGA;
        mSensorTypeList = sensorTypeList;
    }




    public void checkIfGAListIsEmtpy() {

        if (mGAList.getGAList().isEmpty()) {
            System.out.println("List of Geographical Areas is empty. Please insert at least one Geographical Area in US3.");
            return;
        }
        this.checkIfSensorTypeListIsEmtpy();
    }

    public void checkIfSensorTypeListIsEmtpy() {
        if (mSensorTypeList.getSensorTypeList().isEmpty()) {
            System.out.println("List of sensor's reading data types is empty. Please insert at least one first in US5.");
            return;
        }
        this.inputName();
    }

    public void inputName() {
        while (true) {
            System.out.println("Insert a name for the sensor:");
            mName = read.nextLine();
            if (mCtrl.nameIsValid(mName)) {
                this.inputStartYear();
                break;
            } else
                System.out.println("Please insert a valid name");
        }
    }

    public void inputStartYear() {
        while (true) {
            System.out.println("Insert the year when the sensor will start:");
            String inputYear = read.nextLine();
            if (mCtrl.yearIsValid(inputYear)) {
                mYear = Integer.parseInt(inputYear);
                this.inputStartMonth();
                break;
            }
        }
    }

    public void inputStartMonth() {
        while (true) {
            System.out.println("Insert the month when the sensor will start(insert values between 1 and 12):");
            String inputMonth = read.nextLine();
            if (mCtrl.monthIsValid(inputMonth)) {
                mMonth = Integer.parseInt(inputMonth);
                this.inputStartDay();
                break;
            }
        }
    }

    public void inputStartDay() {
        while (true) {
            System.out.println("Insert the day when the sensor will start:");
            String inputDay = read.nextLine();
            if (mCtrl.dayIsValid(inputDay, mMonth, mYear)) {
                mDay = Integer.parseInt(inputDay);
                this.createStartDate();
                break;
            }
        }
    }

    public void createStartDate() {
        mCalendar = new GregorianCalendar(mYear, mMonth, mDay);
        this.inputSensorType();
    }

    public void inputSensorType() {
        while (true) {
            System.out.println("Choose a type for the sensor from one of the sensor types below:");
            System.out.println(mCtrl.showSensorTypeListInString());
            mIndexOfSensorType = read.nextInt();
            read.nextLine();
            if (!(mIndexOfSensorType > mSensorTypeList.getSensorTypeList().size())) {
                System.out.println("Please fill in the following fields to add at least a reading to a sensor: \n");
                this.inputReading();
                break;
            }
            System.out.println("Please insert a valid option \n.");
        }
    }

    public void inputReading() {
        int yearOfReading = getYearOfReading();
        int monthOfReading = getMonthOfReading();
        int dayOfReading = getDayOfReading(yearOfReading, monthOfReading);
        int hourOfReading = getHourOfReading();
        System.out.println("Insert the value of the reading:");
        double readingValue = read.nextDouble();
        read.nextLine();
        GregorianCalendar date = new GregorianCalendar(yearOfReading, monthOfReading, dayOfReading, hourOfReading, 0);
        Reading r = mReadingList.newReading(readingValue, date);
        mReadingList.addReading(r);
        this.askToAddNewReadings();
    }

    private int getYearOfReading() {
        int yearOfReading;
        while (true) {
            System.out.println("Insert the year when the reading was made:");
            String inputYearOfReading = read.nextLine();
            if (mCtrl.yearIsValid(inputYearOfReading)) {
                yearOfReading = Integer.parseInt(inputYearOfReading);
                break;
            }
        }
        return yearOfReading;
    }

    private int getMonthOfReading() {
        int monthOfReading;
        while (true) {
            System.out.println("Insert the month when the reading was made:");
            String inputMonthOfReading = read.nextLine();
            if (mCtrl.monthIsValid(inputMonthOfReading)) {
                monthOfReading = Integer.parseInt(inputMonthOfReading);
                break;
            }
        }
        return monthOfReading;
    }

    private int getDayOfReading(int yearOfReading, int monthOfReading) {
        int dayOfReading;
        while (true) {
            System.out.println("Insert the day when the reading was made:");
            String inputDayOfReading = read.nextLine();
            if (mCtrl.dayIsValid(inputDayOfReading, monthOfReading, yearOfReading)) {
                dayOfReading = Integer.parseInt(inputDayOfReading);
                break;
            }
        }
        return dayOfReading;
    }

    private int getHourOfReading() {
        int hourOfReading;
        while (true) {
            System.out.println("Insert the hour when the reading was made:");
            String inputHourOfReading = read.nextLine();
            if (mCtrl.hourIsValid(inputHourOfReading)) {
                hourOfReading = Integer.parseInt(inputHourOfReading);
                break;
            }
        }
        return hourOfReading;
    }



    public void askToAddNewReadings() {
        String option;
        while (true) {
            System.out.println("Do you want to insert new readings for the sensor(y/n)?");
            option = read.nextLine();
            if (option.matches("n")) {
                inputSensorUnit();
                break;
            }
            if (option.matches("y")) {
                this.inputReading();
                break;
            }
        }

    }

    public void inputSensorUnit() {
        System.out.println("Insert the unit the sensor will read:");
        mUnit = read.nextLine();
        this.inputGPSLocation();
    }

    public void inputGPSLocation() {
        getLatitude();
        getLongitude();
        getAltitude();
    }

    private void getLatitude() {
        while (true) {
            try {
                System.out.println("Insert the latitude of the new sensor:");
                mLatitude = read.nextDouble();
                if (mCtrl.latitudeIsValid(mLatitude)) {
                    break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getLongitude() {
        while (true) {
            try {
                System.out.println("Insert the longitude of the new sensor:");
                mLongitude = read.nextDouble();
                if (mCtrl.longitudeIsValid(mLongitude))
                    break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void getAltitude() {
        while (true) {
            try {

                System.out.println("Insert the altitude of the new sensor:");
                mAltitude = read.nextDouble();
                if (mCtrl.altitudeIsValid(mAltitude))
                    this.inputGAOfSensor();
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void inputGAOfSensor() {
        while (true) {
            System.out.println("Choose the Geographical Area for which you want add this sensor, from the list below:");
            System.out.println(mCtrl.showGAListInString());
            mIndexOfGA = read.nextInt();
            if (mIndexOfGA > mGAList.getGAList().size())
                System.out.println("Please insert a valid option \n.");
            else this.addSensorToGA();
            break;
        }
    }

    public void addSensorToGA() {
        mCtrl.addNewSensorToGA(mName, mCalendar, mIndexOfSensorType, mUnit, mLatitude, mLongitude, mAltitude, mIndexOfGA, mReadingList);
        System.out.println("The following sensor was successfully created: ");
        System.out.println("NAME: " + mName);
        System.out.println("GEOGRAPHICAL AREA: " + mGAList.get(mIndexOfGA - 1).

                getGeographicalAreaDesignation());
        System.out.println("START DATE: " + mYear + "/" + mMonth + "/" + "/" + mDay);
        System.out.println("TYPE: " + mSensorTypeList.getSensorTypeList().

                get(mIndexOfSensorType - 1).

                getSensorTypeDesignation());
        System.out.println("UNITS: " + mUnit);
        System.out.println("LIST OF READINGS:");
        for (
                Reading r : mReadingList.getReadingList()) {
            System.out.println("[timestamp: " + r.getDateAndTime().getTime() + " value: " + r.returnValueOfReading() + "]");
        }
        System.out.println("GPS LOCATION - [Latitude: " + mLatitude + " | Longitude: " + mLongitude + " | Altitude: " + mAltitude + "]");

    }
}
