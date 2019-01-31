package smarthome.io.ui;

import smarthome.controller.US620GetTotalRainfallOnADayOfHouseCTRL;
import smarthome.model.GeographicalArea;
import smarthome.model.House;
import smarthome.model.SensorTypeList;
import smarthome.model.Validations.DateValidations;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class US620GetTotalRainfallOnaDayOfHouseUI {

    private GeographicalArea mGA;
    private SensorTypeList mSensorTypeList;
    private US620GetTotalRainfallOnADayOfHouseCTRL mCTRL620;
    private House mHouse;
    private DateValidations mValidations;
    Scanner read = new Scanner(System.in);
    private String sensorTypeRainfall = "rainfall";
    private GregorianCalendar inputDate;
    private int inputYear;
    private int inputMonth;
    private int inputDay;

    public US620GetTotalRainfallOnaDayOfHouseUI(House house, SensorTypeList rainSensorList) {

        mGA = house.getHouseGA();
        mSensorTypeList = rainSensorList;
        mHouse = house;
        mCTRL620 = new US620GetTotalRainfallOnADayOfHouseCTRL(house, rainSensorList);
        mValidations = new DateValidations();

    }

    public void run() {


        if (mSensorTypeList.checkIfRequiredSensorTypeExists(sensorTypeRainfall)) {
            this.checkRainfallSensorReadingsList();
        } else System.out.println("Please request to the Administrator to create a Rainfall Sensor Type in the System");
    }

    public void checkRainfallSensorReadingsList() {

        while (true)

            if (mHouse.getHouseGA() == null) {
                System.out.println("No location. Add a location to a house.");
                break;
            } else break;


        System.out.println("The Sensor rainfall is on your current options." +
                " Get total rainfall in a given day? Insert date you want to check. Insert year.");


        while (true) {

            inputYear = read.nextInt();
            String newYear = String.valueOf(inputYear);

            if (mValidations.yearIsValid(newYear)) {
                System.out.println("Success! Insert month.");
                break;
            } else
                System.out.println("Input year not valid. Insert a number year between 2011 and 2099.");

            break;
        }

        while (true) {
            inputMonth = read.nextInt();
            String newMonth = String.valueOf(inputMonth);

            if (mValidations.monthIsValid(newMonth)) {
                System.out.println("Success! Insert day.");
                break;
            } else
                System.out.println("Input month is not valid. Insert a number between 1 and 12.");

            break;

        }

        while (true) {
            inputDay = read.nextInt();
            String newDay = String.valueOf(inputDay);

            if (mValidations.dayIsValid(newDay, inputMonth, inputYear)) {

                inputDate = new GregorianCalendar(inputYear, inputMonth, inputDay);
                System.out.println("Input Date success! The total rainfall in: "
                        + inputDate.get(Calendar.YEAR) + "/" + inputDate.get(Calendar.MONTH) + "/" + inputDate.get(Calendar.DAY_OF_MONTH) + " is " + mCTRL620.requestReadingRainfall(inputDate));

                break;
            } else
                System.out.println("Input day is not valid. Insert a number between 1 and 31.");

            break;
        }


    }
}



