package smarthome.io.ui;

import smarthome.controller.GetDailySensorDataCTRL;
import smarthome.dto.ReadingDTO;
import smarthome.model.House;
import smarthome.model.SensorType;

import java.util.GregorianCalendar;

public class GetDailySensorDataUI {

    private GetDailySensorDataCTRL superCTRL;
    private String rainfall = "rainfall";
    private String temperature = "temperature";
    private SensorType rainfallSensorType = new SensorType(this.rainfall);
    private SensorType temperatureSensorType = new SensorType(this.temperature);
    private SensorType sensorType;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;


    public GetDailySensorDataUI(House house) {
        this.superCTRL = new GetDailySensorDataCTRL(house);
    }


    private void requestTimePeriod() {

        System.out.println("Insert the start date (YYYY-MM-DD)");
        this.startDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

        System.out.println("Insert the end date (YYYY-MM-DD)");
        this.endDate = UtilsUI.requestDate("Please insert a valid date in YYYY-MM-DD format.");

    }

    private void calculations(SensorType sensorType, GregorianCalendar startDate, GregorianCalendar endDate, int mode) {
        ReadingDTO readingDTO;
        String msg;
        switch (mode) {
            case 1:
                readingDTO = superCTRL.displayMaximum(sensorType, startDate, endDate);
                msg = ("The first hottest day between " + UtilsUI.dateToString(startDate) +
                        " and " + UtilsUI.dateToString(endDate) +
                        " was at " + UtilsUI.dateToString(readingDTO.getReadingDateAndTime()) +
                        " with a value of " + readingDTO.getReadingValue() + "°C");
                UtilsUI.showInfo("Result", msg);
                break;
            case 2:
                readingDTO = superCTRL.displayMinimum(sensorType, startDate, endDate);
                msg = ("The last coldest day between " + UtilsUI.dateToString(startDate) +
                        " and " + UtilsUI.dateToString(endDate) +
                        " was at " + UtilsUI.dateToString(readingDTO.getReadingDateAndTime()) +
                        " with a value of " + readingDTO.getReadingValue() + "°C");
                UtilsUI.showInfo("Result", msg);
                break;
            case 3:
                readingDTO = superCTRL.displayAmplitude(sensorType, startDate, endDate);
                msg = ("The  day with the highest temperature amplitude between " + UtilsUI.dateToString(startDate) +
                        " and " + UtilsUI.dateToString(endDate) +
                        " was at " + UtilsUI.dateToString(readingDTO.getReadingDateAndTime()) +
                        " with a value of " + UtilsUI.formatDecimal(readingDTO.getReadingValue(), 2) + "°C");
                UtilsUI.showInfo("Result", msg);
                break;
        }
    }

    public void displayFirstMaximum() {
        int mode = 1;

        //request start date and end date user inputs
        requestTimePeriod();

        //set specific sensor data type for this US
        this.sensorType = new SensorType("temperature");
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else
            System.out.println("The available " + this.sensorType.getType() + " sensors in the house area don't have " +
                    "readings in the specified date.\nPlease select a date with registered readings.");
    }

    public void displayLastMaximum() {
        int mode = 2;

        //request start date and end date user inputs
        requestTimePeriod();

        //set specific sensor data type for this US
        this.sensorType = new SensorType("temperature");
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else
            System.out.println("The available " + this.sensorType.getType() + " sensors in the house area don't have " +
                    "readings in the specified date.\nPlease select a date with registered readings.");
    }

    public void displayMaxAmplitude() {
        int mode = 3;

        //request start date and end date user inputs
        requestTimePeriod();

        //set specific sensor data type for this US
        this.sensorType = new SensorType("temperature");
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else
            System.out.println("The available " + this.sensorType.getType() + " sensors in the house area don't have " +
                    "readings in the specified date.\nPlease select a date with registered readings.");
    }
}




