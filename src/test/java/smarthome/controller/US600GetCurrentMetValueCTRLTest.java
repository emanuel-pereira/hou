package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class US600GetCurrentMetValueCTRLTest {

    @Test
    void getSensorTypeListInStringTest() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");
        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);
        House h = new House();
        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);

        String result = ctr.getSensorTypeListInString();
        String expected = "1 - wind\n2 - temp\n";

        assertEquals(expected, result);
    }

    @Test
    void getSensorTypeListTest() {

        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");
        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);
        House h = new House();
        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);

        List<SensorType> result = ctr.getSensorTypeList();
        List<SensorType> expected = Arrays.asList(sensorType1, sensorType2);

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

        House h = new House();
        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);

        SensorType result = ctr.getSensorTypeByIndex(1);
        SensorType expected = sensorType1;

        assertEquals(expected, result);
    }

    @Test
    void getListSensorsOfOneTypeTest() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");

        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);

        GregorianCalendar startdate = new GregorianCalendar(1, 1, 1, 1, 1);
        ReadingList rList = new ReadingList();

        Sensor s1 = new Sensor("sensorA", startdate, sensorType1, "c", rList);
        Sensor s2 = new Sensor("sensorB", startdate, sensorType1, "c", rList);
        Sensor s3 = new Sensor("sensorC", startdate, sensorType2, "c", rList);

        GeographicalArea ga = new GeographicalArea("A", "b", "c", 1, 1, 1, 1, 1);
        House h = new House();
        h.setHouseGA(ga);
        h.getHouseGA().getSensorListInGA().addSensor(s1);
        h.getHouseGA().getSensorListInGA().addSensor(s2);
        h.getHouseGA().getSensorListInGA().addSensor(s3);

        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);

        List<Sensor> result = ctr.getListSensorsOfOneType(sensorType1);
        List<Sensor> expected = Arrays.asList(s1, s2);

        assertEquals(expected, result);
    }

    @Test
    void getTheClosestSensorToGA() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");

        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);

        GregorianCalendar startdate = new GregorianCalendar(1, 1, 1, 1, 1);
        ReadingList rList = new ReadingList();

        Sensor s1 = new Sensor("sensorA", startdate, 90, 100, 1, sensorType1, "c", rList);
        Sensor s2 = new Sensor("sensorB", startdate, 2, 2, 2, sensorType1, "c", rList);
        Sensor s3 = new Sensor("sensorC", startdate, 1.5, 1.5, 1.5, sensorType2, "c", rList);

        GeographicalArea ga = new GeographicalArea("A", "b", "c", 1, 1, 1, 1, 1);
        House h = new House();
        h.setHouseGA(ga);
        h.getHouseGA().getSensorListInGA().addSensor(s1);
        h.getHouseGA().getSensorListInGA().addSensor(s2);
        h.getHouseGA().getSensorListInGA().addSensor(s3);

        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);
        List<Sensor> sensorList = ctr.getListSensorsOfOneType(sensorType1);


        Sensor result = ctr.getTheClosestSensorToGA(sensorList);
        Sensor expected = s2;

        assertEquals(expected, result);
    }
    @Test
    void getLastReadingOfSensorTest() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");

        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);

        GregorianCalendar startdate = new GregorianCalendar(1, 1, 1, 1, 1);
        ReadingList rList = new ReadingList();

        Reading r1 = rList.newReading(200,startdate);
        Reading r2 = rList.newReading(150,startdate);
        Reading r3 = rList.newReading(300,startdate);

        rList.addReading(r1);
        rList.addReading(r2);
        rList.addReading(r3);

        Sensor s1 = new Sensor("sensorA", startdate, 90, 100, 1, sensorType1, "c", rList);
        //Sensor s2 = new Sensor("sensorB", startdate, 2, 2, 2, sensorType1, "c", rList);
        //Sensor s3 = new Sensor("sensorC", startdate, 1.5, 1.5, 1.5, sensorType2, "c", rList);

        GeographicalArea ga = new GeographicalArea("A", "b", "c", 1, 1, 1, 1, 1);
        House h = new House();
        h.setHouseGA(ga);

        h.getHouseGA().getSensorListInGA().addSensor(s1);
        //h.getHouseGA().getSensorListInGA().addSensor(s2);
        //h.getHouseGA().getSensorListInGA().addSensor(s3);

        US600GetCurrentMeteoValueHouseAreaCTRL ctr = new US600GetCurrentMeteoValueHouseAreaCTRL(h, sensorTypeList);
        List<Sensor> sensorList = ctr.getListSensorsOfOneType(sensorType1);
        Sensor closestSensorToGA = ctr.getTheClosestSensorToGA(sensorList);

        double result = ctr.getLastReadingOfSensor(closestSensorToGA);
        double expected = 300;

        assertEquals(expected, result);
    }

}
