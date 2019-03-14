package smarthome.io.ui;

import smarthome.controller.GetDailySensorDataCTRL;
import smarthome.dto.ReadingDTO;
import smarthome.model.House;
import smarthome.model.SensorType;

import java.util.GregorianCalendar;

public class GetDailySensorDataUI {

    private GetDailySensorDataCTRL superCTRL;
    private SensorType sensorType;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String temperature = "temperature";
    private String msgTitle = "Data not found";
    private String msgNoData = ("The available " + (this.sensorType != null ? this.sensorType.getType() : null) +
            " sensors in the house area don't have readings in the specified date. \n    Please select a date with " +
            "registered readings. You can also import readings from a CSV File");
    private String msgNoSensor = "There are still no sensors in the geographical area of the house. \n    Please ask the " +
            "system administrator to add some sensors first. You can also import geographical areas and sensors from a JSONFile";


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
        String entrance;
        String unit;
        switch (mode) {
            case 1:
                readingDTO = superCTRL.displayMaximum(sensorType, startDate, endDate);
                entrance = "The first hottest day";
                unit = "°C";
                this.buildMessage(entrance, readingDTO, unit);
                break;
            case 2:
                readingDTO = superCTRL.displayMinimum(sensorType, startDate, endDate);
                entrance = "The last coldest day";
                unit = "°C";
                this.buildMessage(entrance, readingDTO, unit);
                break;
            case 3:
                readingDTO = superCTRL.displayAmplitude(sensorType, startDate, endDate);
                entrance = "The day with the highest temperature amplitude";
                unit = "°C";
                this.buildMessage(entrance, readingDTO, unit);
                break;
            default:
                //no action needed
        }
    }

    private void buildMessage(String entrance, ReadingDTO readingDTO, String unit) {
        String msg;
        msg = (entrance + " between " + UtilsUI.dateToString(this.startDate) +
                " and " + UtilsUI.dateToString(this.endDate) +
                " was at " + UtilsUI.dateToString(readingDTO.getReadingDateAndTime()) +
                " with a value of " + readingDTO.getReadingValue() + unit);
        UtilsUI.showInfo("Result", msg);
    }

    public void displayFirstMaximum() {
        int mode = 1;
        if (this.superCTRL.filterByTypeAndInterval(sensorType, startDate, endDate).size() == 0) {
            UtilsUI.showError(this.msgTitle, this.msgNoSensor);
            return;
        }
        //request start date and end date user inputs
        requestTimePeriod();
        //set specific sensor data type for this US
        this.sensorType = new SensorType(temperature);
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else {
            UtilsUI.showError(this.msgTitle, this.msgNoData);
        }
    }

    public void displayLastMaximum() {
        int mode = 2;
        if (this.superCTRL.filterByTypeAndInterval(sensorType, startDate, endDate).size() == 0) {
            UtilsUI.showError(this.msgTitle, this.msgNoSensor);
            return;
        }
        //request start date and end date user inputs
        requestTimePeriod();
        //set specific sensor data type for this US
        this.sensorType = new SensorType(temperature);
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else
            UtilsUI.showError(this.msgTitle, this.msgNoData);
    }

    public void displayMaxAmplitude() {
        int mode = 3;
        if (this.superCTRL.filterByTypeAndInterval(sensorType, startDate, endDate).size() == 0) {
            UtilsUI.showError(this.msgTitle, this.msgNoSensor);
            return;
        }
        //request start date and end date user inputs
        requestTimePeriod();
        //set specific sensor data type for this US
        this.sensorType = new SensorType(temperature);
        if (this.superCTRL.checkIfClosestSensorsHasReadingsInTimePeriod(sensorType, startDate, endDate))
            calculations(this.sensorType, this.startDate, this.endDate, mode);
        else
            UtilsUI.showError(this.msgTitle, this.msgNoData);
    }
}




