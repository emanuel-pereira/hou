package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentTemperatureInHouseAreaCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getSensorTypeListInStringTest() {
         

        SensorTypeList sensorTypeList = new SensorTypeList();

        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");
        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);

        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        String result = ctr.showSensorTypeList();
        String expected = "1 - wind\n2 - temp\n";

        assertEquals(expected, result);
    }


    @Test
    void getSensorTypeByIndexTest() {
         

        SensorTypeList sensorTypeList = new SensorTypeList();

        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");
        SensorType sensorType3 = new SensorType("wind");

        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);
        sensorTypeList.addSensorType(sensorType3);

        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        SensorType result = ctr.getSensorTypeByIndex(0);

        assertEquals(sensorType1, result);
    }

    @Test
    void getListSensorsOfOneTypeTest() {
         

        SensorTypeList sensorTypeList = new SensorTypeList();
         

        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");

        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);

        GregorianCalendar startDate = new GregorianCalendar(1, Calendar.FEBRUARY, 1, 1, 1);
        ReadingList rList = new ReadingList();

        Sensors s1 = new Sensors("S01", "sensorA", startDate, sensorType1, "c", rList);
        Sensors s2 = new Sensors("S02", "sensorB", startDate, sensorType1, "c", rList);
        Sensors s3 = new Sensors("S03", "sensorC", startDate, sensorType2, "c", rList);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);

        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        int result = ctr.getSensorListOfTypeSize(sensorType1);
        int expected = 2;

        assertEquals(expected, result);
    }

    @Test
    void getLastReadingOfSensorTest() {

         

        SensorTypeList sensorTypeList = new SensorTypeList();
         

        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 0);
        ReadingList readingList1 = new ReadingList();
        Reading r1 = readingList1.newReading(17, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 11, 0));
        Reading r2 = readingList1.newReading(15, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 0));
        Reading r3 = readingList1.newReading(14, new GregorianCalendar(2018, Calendar.DECEMBER, 31, 15, 0));
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2 = new ReadingList();
        Reading r4 = readingList2.newReading(27, new GregorianCalendar(2019, Calendar.MARCH, 3, 11, 0));
        Reading r5 = readingList2.newReading(35, new GregorianCalendar(2019, Calendar.MARCH, 3, 12, 0));
        Reading r6 = readingList2.newReading(42, new GregorianCalendar(2019, Calendar.MARCH, 3, 17, 0));
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);


        Location l1 = new Location(25, 15, 2);
        Sensors s4 = new Sensors("W0002", "WindSensor2", startDate, l1, wind, "c", readingList2);

        Location l2 = new Location(20, 20, 2);
        Sensors s5 = new Sensors("W0001", "WindSensor1", startDate, l2, wind, "c", readingList1);

        Sensors s6 = new Sensors("W0003", "WindSensor3", startDate, l2, wind, "c", readingList2);
        Location l3 = new Location(12, 15, 2);

        Sensors s7 = new Sensors("T0001", "TemperatureSensor", startDate, l3, temperature, "c", readingList2);

        g1.getSensorListInGA().addSensor(s4);
        g1.getSensorListInGA().addSensor(s5);
        g1.getSensorListInGA().addSensor(s6);
        g1.getSensorListInGA().addSensor(s7);


        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        Sensors closestSensorToGA = ctr.getClosestSensorWithLatestReadingCTRL(wind);

        double result = ctr.getLastReadingOfSensor(closestSensorToGA);
        double expected = 42;

        assertEquals(expected, result);
    }

    @Test
    void getSensorTypeListSize() {

         

        SensorTypeList sensorTypeList = new SensorTypeList();
         

        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);
        GetCurrentTemperatureInHouseAreaCTRL ctrl = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        int expected = 2;
        int result = ctrl.getSensorTypeListSize();

        assertEquals(expected, result);
    }

    @Test
    void getHouseGATest() {
         

        SensorTypeList sensorTypeList = new SensorTypeList();
         

        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);
        GetCurrentTemperatureInHouseAreaCTRL ctrl = new GetCurrentTemperatureInHouseAreaCTRL(sensorTypeList);

        String expected = "Porto";
        String result = ctrl.getHouseGACTRL().getGAName();

        assertEquals(expected, result);
    }

}
