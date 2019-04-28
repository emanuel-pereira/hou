package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRLTest {


    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
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
    @DisplayName("Tests that the rainfall type of sensor exists")
    void checkIfSensorTypeExistsTrue() {

        SensorType sensorType = new SensorType("rainfall");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sensorTypeList);

        assertTrue(ctrl.checkIfSensorTypeExists("rainfall"));
    }

    @Test
    @DisplayName("Tests that the rainfall type of sensor does not exist")
    void checkIfSensorTypeExistsFalse() {

        SensorType sensorType = new SensorType("rainfall");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sensorTypeList);

        assertFalse(ctrl.checkIfSensorTypeExists("temperature"));
    }

    @Test
    @DisplayName("Tests that the House location is configured")
    void isHouseGAConfiguredTrue() {

        SensorType sensorType = new SensorType("rainfall");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sensorTypeList);

        assertTrue(ctrl.isHouseGAConfigured());
    }

    @Test
    @DisplayName("Tests if the list of rainfall sensors in the geographical area of the house is returned")
    void getGARainfallSensors() {

        SensorType rain = new SensorType("rainfall");
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(rain);
        sTList.addSensorType(wind);
        sTList.addSensorType(temperature);

        GregorianCalendar startDate = new GregorianCalendar(2018, Calendar.DECEMBER, 26, 12, 0);
        ReadingList readings = new ReadingList();

        Location loc = new Location(12,45,67);

        Sensor s1 = new ExternalSensor("S01", "sensor1", startDate, loc, rain, "c", readings);
        Sensor s2 = new ExternalSensor("S02", "sensor2", startDate, loc, temperature, "c", readings);
        Sensor s3 = new ExternalSensor("S03", "sensor3", startDate, loc,  wind, "c", readings);
        Sensor s4 = new ExternalSensor("S04", "sensor4", startDate, loc,  rain, "c", readings);
        Sensor s5 = new ExternalSensor("S05", "sensor5", startDate, loc, rain, "c", readings);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);
        g1.getSensorListInGA().addSensor(s4);
        g1.getSensorListInGA().addSensor(s5);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        List<Sensor> expected = Arrays.asList(s1, s4, s5);
        List<Sensor> result = ctrl623.getGARainfallSensors(rain).getSensorList();


        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Tests if the closest rainfall sensors to the house with readings in period are returned")
    void getClosestRainfallSensorsWithReadingsInTimePeriod() {

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JULY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, Calendar.JUNE, 30);
        GregorianCalendar date7 = new GregorianCalendar(2017, Calendar.JULY, 7);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);


        Sensor s6 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s7 = new ExternalSensor("R0002", "RainSensor2", sDate2, l1, sT, "l/m2", rL2);
        Sensor s8 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s9 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s10 = new ExternalSensor("R0003", "RainSensor3", sDate1, l2, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s6);
        g1.getSensorListInGA().addSensor(s7);
        g1.getSensorListInGA().addSensor(s8);
        g1.getSensorListInGA().addSensor(s9);
        g1.getSensorListInGA().addSensor(s10);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        List<Sensor> expected = Arrays.asList(s6, s7);
        List<Sensor> result = ctrl623.getClosestRainfallSensorsWithReadingsInTimePeriod(sT, startDate, endDate).getSensorList();


        assertEquals(expected, result);


    }


    @Test
    @DisplayName("Tests if returns false when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsDoNotHaveReadingsInPeriod() {


        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.APRIL, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.NOVEMBER, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, Calendar.MAY, 30);
        GregorianCalendar date7 = new GregorianCalendar(2017, Calendar.AUGUST, 1);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s11 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s12 = new ExternalSensor("R0002", "RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s13 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s14 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s15 = new ExternalSensor("R0003", "RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s11);
        g1.getSensorListInGA().addSensor(s12);
        g1.getSensorListInGA().addSensor(s13);
        g1.getSensorListInGA().addSensor(s14);
        g1.getSensorListInGA().addSensor(s15);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if returns true when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsHaveReadingsInPeriod() {

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, Calendar.JUNE, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, Calendar.JULY, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s16 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s17 = new ExternalSensor("R0002", "RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s18 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s19 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s20 = new ExternalSensor("R0003", "RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s16);
        g1.getSensorListInGA().addSensor(s17);
        g1.getSensorListInGA().addSensor(s18);
        g1.getSensorListInGA().addSensor(s19);
        g1.getSensorListInGA().addSensor(s20);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is only one closest sensor")
    void calculateAverageOfRainfallReadingsForOneSensor() {


        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, Calendar.JUNE, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, Calendar.JULY, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(21, 21, 200);

        Sensor s21 = new ExternalSensor("R0001", "RainSensor", sDate1, l2, sT, "l/m2", rL1);
        Sensor s22 = new ExternalSensor("R0002", "RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s23 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s24 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s25 = new ExternalSensor("R0003", "RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s21);
        g1.getSensorListInGA().addSensor(s22);
        g1.getSensorListInGA().addSensor(s23);
        g1.getSensorListInGA().addSensor(s24);
        g1.getSensorListInGA().addSensor(s25);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(22.83, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is more than one sensor at same distance")
    void calculateAverageOfRainfallForTwoEquidistantSensors() {

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, Calendar.JUNE, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, Calendar.JULY, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s26 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s27 = new ExternalSensor("R0002", "RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s28 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s29 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s30 = new ExternalSensor("R0003", "RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s26);
        g1.getSensorListInGA().addSensor(s27);
        g1.getSensorListInGA().addSensor(s28);
        g1.getSensorListInGA().addSensor(s29);
        g1.getSensorListInGA().addSensor(s30);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(22.83, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when equidistant sensor have readings with the same date")
    void calculateAverageOfRainfallReadingsWithSameDate() {

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        SensorTypeList sTList = new SensorTypeList();

        sTList.addSensorType(sT);
        sTList.addSensorType(sT2);
        sTList.addSensorType(sT3);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JULY, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, Calendar.JUNE, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, Calendar.MAY, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, Calendar.JUNE, 20, 12, 00);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date1);
        Reading r3 = new Reading(20.0, date1);
        Reading r4 = new Reading(22.0, date1);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date2 = new GregorianCalendar(2017, Calendar.JULY, 5);
        GregorianCalendar date3 = new GregorianCalendar(2017, Calendar.JULY, 6);

        Reading r5 = new Reading(14.5, date2);
        Reading r6 = new Reading(70.6, date2);
        Reading r7 = new Reading(54.3, date3);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);


        GregorianCalendar date4 = new GregorianCalendar(2017, Calendar.JUNE, 20, 14, 00);

        Reading r8 = new Reading(14.5, date4);
        Reading r9 = new Reading(70.6, date4);
        Reading r10 = new Reading(54.3, date4);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s31 = new ExternalSensor("R0001", "RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s32 = new ExternalSensor("R0002", "RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s33 = new ExternalSensor("W0001", "WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s34 = new ExternalSensor("T0001", "TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s35 = new ExternalSensor("R0003", "RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s31);
        g1.getSensorListInGA().addSensor(s32);
        g1.getSensorListInGA().addSensor(s33);
        g1.getSensorListInGA().addSensor(s34);
        g1.getSensorListInGA().addSensor(s35);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(sTList);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(14.5, result, 0.1);
    }

}