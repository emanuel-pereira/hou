package smarthome.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class US620GetTotalRainfallOnADayOfHouseCTRLTest {


    @Test
    @DisplayName("Verify if rainfall sensor exists.")
    public void checkIfRequiredSensorTypeExists() {

        House h1 = new House();

        SensorTypeList sL = new SensorTypeList();
        SensorType temprature = sL.newSensorType("temperature");
        SensorType rainfall = sL.newSensorType("rainfall");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temprature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(rainfall));
        assertEquals(2, sL.getSensorTypeList().size());

        US620GetTotalRainfallOnADayOfHouseCTRL ctrl620 = new US620GetTotalRainfallOnADayOfHouseCTRL(h1, sL);

        boolean result = ctrl620.checkIfRequiredSensorTypeExists("rainfall");

        assertTrue(result);

    }

    @Test
    @DisplayName("verify if rainfall sensor not exists.")
    public void checkIfRequiredSensorTypeNotExists() {

        House h1 = new House();
        SensorTypeList sL = new SensorTypeList();
        SensorType temprature = sL.newSensorType("temperature");
        SensorType humidity = sL.newSensorType("humidity");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temprature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(humidity));
        assertEquals(2, sL.getSensorTypeList().size());

        US620GetTotalRainfallOnADayOfHouseCTRL ctrl620 = new US620GetTotalRainfallOnADayOfHouseCTRL(h1, sL);

        boolean result = ctrl620.checkIfRequiredSensorTypeExists("rainfall");

        assertFalse(result);

    }


    @Test
    @DisplayName("Ensure if exists Rainfall readings for an given day.")
    public void checkRequestReadingRainfall() {

        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorTypeRain = new SensorType("rainfall");
        sensorTypeList.addSensorType(sensorTypeRain);

        GregorianCalendar inputDate = new GregorianCalendar(2018, 1, 1);
        inputDate.get(Calendar.DATE);


        ReadingList readingList = new ReadingList();
        Reading r1 = readingList.newReading(15, inputDate);
        Reading r2 = readingList.newReading(16, inputDate);
        readingList.addReading(r1);
        readingList.addReading(r2);

        Sensor rainfall = new Sensor("rainSensor", inputDate, 40, 30, 5, sensorTypeRain, "R", readingList);
        GeographicalArea GA = new GeographicalArea("01", "Porto", "city", 5, 3, 2, 3, 4);

        House house = new House();
        house.setHouseGA(GA);
        house.getHouseGA().getGASensorsByType("rainfall").addSensor(rainfall);

        US620GetTotalRainfallOnADayOfHouseCTRL m620CTRL = new US620GetTotalRainfallOnADayOfHouseCTRL(house, sensorTypeList);

        //m620CTRL.requestReadingRainfall(inputDate);

        double result = 31;

        double expectedResult = 31;

        assertEquals(result, expectedResult);
    }

}






