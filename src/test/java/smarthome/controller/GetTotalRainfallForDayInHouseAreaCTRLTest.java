package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class GetTotalRainfallForDayInHouseAreaCTRLTest {


    @Test
    @DisplayName("Verify if rainfall sensor exists.")
    void checkIfRequiredSensorTypeExists() {

        House h1 = new House();

        SensorTypeList sL = new SensorTypeList();
        SensorType temperature = sL.newSensorType("temperature");
        SensorType rainfall = sL.newSensorType("rainfall");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temperature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(rainfall));
        assertEquals(2, sL.getSensorTypeList().size());

        GetTotalRainfallForDayInHouseAreaCTRL ctrl620 = new GetTotalRainfallForDayInHouseAreaCTRL(h1, sL);

        boolean result = ctrl620.checkIfSensorTypeExists("rainfall");

        assertTrue(result);

    }

    @Test
    @DisplayName("verify if rainfall sensor does not exists.")
    void checkIfRequiredSensorTypeNotExists() {

        House h1 = new House();
        SensorTypeList sL = new SensorTypeList();
        SensorType temperature = sL.newSensorType("temperature");
        SensorType humidity = sL.newSensorType("humidity");

        assertEquals(0, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(temperature));
        assertEquals(1, sL.getSensorTypeList().size());
        assertTrue(sL.addSensorType(humidity));
        assertEquals(2, sL.getSensorTypeList().size());

        GetTotalRainfallForDayInHouseAreaCTRL ctrl620 = new GetTotalRainfallForDayInHouseAreaCTRL(h1, sL);

        boolean result = ctrl620.checkIfSensorTypeExists("rainfall");

        assertFalse(result);

    }

    @Test
    @DisplayName("Ensure that inputted month and day are invalid")
    void requestReadingRainfall() {

        OccupationArea oc1 = new OccupationArea(1, 1);
        Location loc1 = new Location(1,1,1);

        GeographicalArea ga = new GeographicalArea("POR", "Porto", "City", oc1,loc1);
        House house = new House();
        house.setHouseGA(ga);
        house.setHouseAddress("Street", "15", "4420", 5, 5, 5);
        SensorList houseGASensorList = house.getHouseGA().getSensorListInGA();


        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, 15, 31, 11, 0);
        ReadingList readingList1 = new ReadingList();
        Reading r1 = readingList1.newReading(17, new GregorianCalendar(2018, 12, 31, 11, 0));
        Reading r2 = readingList1.newReading(15, new GregorianCalendar(2018, 12, 31, 12, 0));
        Reading r3 = readingList1.newReading(14, new GregorianCalendar(2019, 2, 3, 15, 0));
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(0, new GregorianCalendar(2019, 2, 3, 11, 0),"C");
        Reading r5 = new Reading(0, new GregorianCalendar(2019, 2, 3, 12, 0),"C");
        Reading r6 = new Reading(0, new GregorianCalendar(2019, 2, 3, 17, 0),"C");
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);
        Location l1= new Location(85, 65, 10);
        Sensor s0 = new Sensor("W2501","WindSensor2", startDate, l1, wind, "c", readingList2);
        Location l2= new Location(80, 50, 10);
        Sensor s1 = new Sensor("W2502","WindSensor1", startDate,l2 , wind, "c", readingList1);
        Sensor s2 = new Sensor("W2503","WindSensor3", startDate, l2, wind, "c", readingList2);
        Location l3= new Location(1.5, 1.5, 1.5);
        Sensor s3 = new Sensor("T2501","TemperatureSensor", startDate, l3, temperature, "c", readingList2);

        houseGASensorList.addSensor(s0);
        houseGASensorList.addSensor(s1);
        houseGASensorList.addSensor(s2);
        houseGASensorList.addSensor(s3);


        GetTotalRainfallForDayInHouseAreaCTRL ctr = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorTypeList);

        GregorianCalendar date = new GregorianCalendar(2019, 2, 3);


        double result = ctr.showTotalValueInADay(date, wind);
        double expected = 0;

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test that house has already a geographical area configured")
    void isHouseGAConfigured() {
        House house = new House();

        OccupationArea oc1 = new OccupationArea(1, 1);
        Location loc1 = new Location(1,1,1);

        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", oc1,loc1);
        house.setHouseGA(ga);
        SensorTypeList sensorTypeList = new SensorTypeList();
        GetTotalRainfallForDayInHouseAreaCTRL ctrl = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorTypeList);
        boolean result = ctrl.isHouseGAConfigured();
        assertTrue(result);
    }

    @Test
    @DisplayName("Test that house doesn't have a geographical area configured")
    void houseWithoutGA() {
        House house = new House();
        SensorTypeList sensorTypeList = new SensorTypeList();
        GetTotalRainfallForDayInHouseAreaCTRL ctrl = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorTypeList);
        boolean result = ctrl.isHouseGAConfigured();
        assertFalse(result);
    }


    @Test
    @DisplayName("Ensure that closest rainfall sensor has readings in 2019-02-03")

    void closestSensorsWithReadingsInDate() {
        OccupationArea oc1 = new OccupationArea(1, 1);
        Location loc1 = new Location(1,1,1);
        GeographicalArea ga = new GeographicalArea("POR", "Porto", "City", oc1,loc1);
        House house = new House();
        house.setHouseGA(ga);
        house.setHouseAddress("Street", "15", "4420", 5, 5, 5);
        SensorList houseGASensorList = house.getHouseGA().getSensorListInGA();


        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType rainfall = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(rainfall);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, 15, 31, 11, 0);
        ReadingList readingList1 = new ReadingList();
        Reading r1 = new Reading(17, new GregorianCalendar(2018, 12, 31, 11, 0),"C");
        Reading r2 = new Reading(15, new GregorianCalendar(2018, 12, 31, 12, 0),"C");
        Reading r3 = new Reading(14, new GregorianCalendar(2019, 2, 3, 15, 0),"C");
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(0, new GregorianCalendar(2019, 2, 3, 11, 0),"C");
        Reading r5 = new Reading(0, new GregorianCalendar(2019, 2, 3, 12, 0),"C");
        Reading r6 = new Reading(0, new GregorianCalendar(2019, 2, 3, 17, 0),"C");
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);

        Location l1= new Location(85, 65, 10);
        Location l2= new Location(80, 50, 10);
        Location l3= new Location(1.5, 1.5, 1.5);

        Sensor s0 = new Sensor("R0001","RainfallSensor2", startDate,l1, rainfall, "c", readingList2);
        Sensor s1 = new Sensor("R0002","RainfallSensor1", startDate, l2, rainfall, "c", readingList1);
        Sensor s2 = new Sensor("W0001","WindSensor3", startDate,l2, rainfall, "c", readingList2);
        Sensor s3 = new Sensor("T0001","TemperatureSensor", startDate, l3, temperature, "c", readingList2);

        houseGASensorList.addSensor(s0);
        houseGASensorList.addSensor(s1);
        houseGASensorList.addSensor(s2);
        houseGASensorList.addSensor(s3);


        GetTotalRainfallForDayInHouseAreaCTRL ctr = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorTypeList);

        GregorianCalendar date = new GregorianCalendar(2019, 2, 3);


        boolean result = ctr.closestSensorsWithLatestReadingsInDate(date, rainfall);

        assertTrue(result);
    }


    @Test
    @DisplayName("Ensure that closest rainfall sensor doesn't have readings in 2019-03-15")

    void closestSensorsWithoutReadingsInDate() {

        OccupationArea oc1 = new OccupationArea(1, 1);
        Location loc1 = new Location(1,1,1);
        GeographicalArea ga = new GeographicalArea("POR", "Porto", "City", oc1,loc1);
        House house = new House();
        house.setHouseGA(ga);
        house.setHouseAddress("Street", "15", "4420", 5, 5, 5);
        SensorList houseGASensorList = house.getHouseGA().getSensorListInGA();


        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType rainfall = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(rainfall);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, 15, 31, 11, 0);
        ReadingList readingList1 = new ReadingList();
        Reading r1 = readingList1.newReading(17, new GregorianCalendar(2018, 12, 31, 11, 0));
        Reading r2 = readingList1.newReading(15, new GregorianCalendar(2018, 12, 31, 12, 0));
        Reading r3 = readingList1.newReading(14, new GregorianCalendar(2019, 2, 3, 15, 0));
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2 = new ReadingList();
        Reading r4 = new Reading(0, new GregorianCalendar(2019, 2, 3, 11, 0),"C");
        Reading r5 = new Reading(0, new GregorianCalendar(2019, 2, 3, 12, 0),"C");
        Reading r6 = new Reading(0, new GregorianCalendar(2019, 2, 3, 17, 0),"C");
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);

        Location l1= new Location(85, 65, 10);
        Location l2= new Location(80, 50, 10);
        Location l3= new Location(1.5, 1.5, 1.5);

        Sensor s0 = new Sensor("R0001","RainfallSensor2", startDate, l1, rainfall, "c", readingList2);
        Sensor s1 = new Sensor("R0002","RainfallSensor1", startDate, l2, rainfall, "c", readingList1);
        Sensor s2 = new Sensor("W0001","WindSensor3", startDate, l2, rainfall, "c", readingList2);
        Sensor s3 = new Sensor("T0001","TemperatureSensor", startDate, l3, temperature, "c", readingList2);

        houseGASensorList.addSensor(s0);
        houseGASensorList.addSensor(s1);
        houseGASensorList.addSensor(s2);
        houseGASensorList.addSensor(s3);


        GetTotalRainfallForDayInHouseAreaCTRL ctr = new GetTotalRainfallForDayInHouseAreaCTRL(house, sensorTypeList);

        GregorianCalendar date = new GregorianCalendar(2019, 3, 15);


        boolean result = ctr.closestSensorsWithLatestReadingsInDate(date, rainfall);

        assertFalse(result);
    }
}
