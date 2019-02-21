package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentTemperatureInHouseAreaCTRLTest {

    @Test
    void getSensorTypeListInStringTest() {
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType sensorType1 = new SensorType("wind");
        SensorType sensorType2 = new SensorType("temp");
        sensorTypeList.addSensorType(sensorType1);
        sensorTypeList.addSensorType(sensorType2);
        House h = new House();
        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(h, sensorTypeList);

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

        House h = new House();
        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(h, sensorTypeList);

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

        GregorianCalendar startdate = new GregorianCalendar(1, 1, 1, 1, 1);
        ReadingList rList = new ReadingList();

        Sensor s1 = new Sensor("sensorA", startdate, sensorType1, "c", rList);
        Sensor s2 = new Sensor("sensorB", startdate, sensorType1, "c", rList);
        Sensor s3 = new Sensor("sensorC", startdate, sensorType2, "c", rList);

        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", 1, 1, 1, 1, 1);        House h = new House();
        h.setHouseGA(ga);
        h.getHouseGA().getSensorListInGA().addSensor(s1);
        h.getHouseGA().getSensorListInGA().addSensor(s2);
        h.getHouseGA().getSensorListInGA().addSensor(s3);

        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(h, sensorTypeList);

        int result = ctr.getSensorListOfTypeSize(sensorType1);
        int expected = 2;

        assertEquals(expected, result);
    }

    @Test
    void getLastReadingOfSensorTest() {
        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", 1, 1, 1, 1, 1);        House house = new House();
        house.setHouseGA(ga);
        house.setHouseAddress("Rua das Flores","15","4430-250",5,5,5);
        SensorList houseGASensorList=house.getHouseGA().getSensorListInGA();


        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 31, 11, 0);
        ReadingList readingList1= new ReadingList();
        Reading r1 = readingList1.newReading(17,new GregorianCalendar(2018,12,31,11,0));
        Reading r2 = readingList1.newReading(15,new GregorianCalendar(2018,12,31,12,0));
        Reading r3 = readingList1.newReading(14,new GregorianCalendar(2018,12,31,15,0));
        readingList1.addReading(r1);
        readingList1.addReading(r2);
        readingList1.addReading(r3);

        ReadingList readingList2= new ReadingList();
        Reading r4 = readingList2.newReading(27,new GregorianCalendar(2019,2,3,11,0));
        Reading r5 = readingList2.newReading(35,new GregorianCalendar(2019,2,3,12,0));
        Reading r6 = readingList2.newReading(42,new GregorianCalendar(2019,2,3,17,0));
        readingList2.addReading(r4);
        readingList2.addReading(r5);
        readingList2.addReading(r6);

        Location l1= new Location(85, 65, 10);
        Sensor s0 = new Sensor("WindSensor2", startDate,l1 , wind, "c", readingList2);
        Location l2= new Location(80, 50, 10);
        Sensor s1 = new Sensor("WindSensor1", startDate, l2, wind, "c", readingList1);
        Sensor s2 = new Sensor("WindSensor3", startDate, l2, wind, "c", readingList2);
        Location l3= new Location(1.5, 1.5, 1.5);
        Sensor s3 = new Sensor("TemperatureSensor", startDate, l3, temperature, "c", readingList2);

        houseGASensorList.addSensor(s0);
        houseGASensorList.addSensor(s1);
        houseGASensorList.addSensor(s2);
        houseGASensorList.addSensor(s3);


        GetCurrentTemperatureInHouseAreaCTRL ctr = new GetCurrentTemperatureInHouseAreaCTRL(house, sensorTypeList);

        Sensor closestSensorToGA = ctr.getClosestSensorWithLatestReading(wind);

        double result = ctr.getLastReadingOfSensor(closestSensorToGA);
        double expected = 42;

        assertEquals(expected, result);
    }

    @Test
    void getSensorTypeListSize() {

        House house = new House();
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);
        GetCurrentTemperatureInHouseAreaCTRL ctrl = new GetCurrentTemperatureInHouseAreaCTRL(house, sensorTypeList);

        int expected=2;
        int result= ctrl.getSensorTypeListSize();

        assertEquals(expected,result);
    }

    @Test
    void getHouseGA() {
        GeographicalArea ga = new GeographicalArea("PORTO", "Porto", "City", 1, 1, 1, 1, 1);
        House house = new House();
        house.setHouseGA(ga);
        SensorTypeList sensorTypeList = new SensorTypeList();
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");
        sensorTypeList.addSensorType(wind);
        sensorTypeList.addSensorType(temperature);
        GetCurrentTemperatureInHouseAreaCTRL ctrl = new GetCurrentTemperatureInHouseAreaCTRL(house, sensorTypeList);

        String expected="Porto";
        String  result= ctrl.getHouseGA().getGAName();

        assertEquals(expected,result);
    }

}
