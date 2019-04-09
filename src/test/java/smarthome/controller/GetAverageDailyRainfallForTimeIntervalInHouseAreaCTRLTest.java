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
    @DisplayName("Tests if the list of rainfall sensors in the geographical area of the house is returned")
    void getGARainfallSensors() {

        g1.getSensorListInGA().getSensorList().clear();

        SensorType rain = new SensorType("rainfall");
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2018, Calendar.DECEMBER, 26, 12, 0);
        ReadingList readings = new ReadingList();


        Sensor s1 = new Sensor("S01","sensor1", startDate, rain, "c", readings);
        Sensor s2 = new Sensor("S02","sensor2", startDate, temperature, "c", readings);
        Sensor s3 = new Sensor("S03", "sensor3", startDate, wind, "c", readings);
        Sensor s4 = new Sensor("S04","sensor4", startDate, rain, "c", readings);
        Sensor s5 = new Sensor("S05", "sensor5", startDate, rain, "c", readings);

        g1.getSensorListInGA().addSensor(s1);
        g1.getSensorListInGA().addSensor(s2);
        g1.getSensorListInGA().addSensor(s3);
        g1.getSensorListInGA().addSensor(s4);
        g1.getSensorListInGA().addSensor(s5);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        List<Sensor> expected = Arrays.asList(s1, s4, s5);
        List<Sensor> result = ctrl623.getGARainfallSensors(rain).getSensorList();


        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Tests if the closest rainfall sensors to the house with readings in period are returned")
    void getClosestRainfallSensorsWithReadingsInTimePeriod() {
        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 6, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 6);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 30);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, 5, 30);
        GregorianCalendar date7 = new GregorianCalendar(2017, 6, 7);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);


        Sensor s6 = new Sensor("R0001","RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s7 = new Sensor("R0002","RainSensor2", sDate2, l1, sT, "l/m2", rL2);
        Sensor s8 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s9 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s10 = new Sensor("R0003","RainSensor3", sDate1, l2, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s6);
        g1.getSensorListInGA().addSensor(s7);
        g1.getSensorListInGA().addSensor(s8);
        g1.getSensorListInGA().addSensor(s9);
        g1.getSensorListInGA().addSensor(s10);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        List<Sensor> expected = Arrays.asList(s6, s7);
        List<Sensor> result = ctrl623.getClosestRainfallSensorsWithReadingsInTimePeriod(sT, startDate, endDate).getSensorList();

        assertEquals(expected, result);


    }


    @Test
    @DisplayName("Tests if returns false when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsDoNotHaveReadingsInPeriod() {
        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 5, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 3, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, 10, 2);
        GregorianCalendar date3 = new GregorianCalendar(2017, 8, 3);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, 4, 30);
        GregorianCalendar date7 = new GregorianCalendar(2017, 7, 1);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s11 = new Sensor("R0001","RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s12 = new Sensor("R0002","RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s13 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s14 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s15 = new Sensor("R0003","RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s11);
        g1.getSensorListInGA().addSensor(s12);
        g1.getSensorListInGA().addSensor(s13);
        g1.getSensorListInGA().addSensor(s14);
        g1.getSensorListInGA().addSensor(s15);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if returns true when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsHaveReadingsInPeriod() {

        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 5, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, 5, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, 6, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s16 = new Sensor("R0001","RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s17 = new Sensor("R0002","RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s18 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s19 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s20 = new Sensor("R0003","RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s16);
        g1.getSensorListInGA().addSensor(s17);
        g1.getSensorListInGA().addSensor(s18);
        g1.getSensorListInGA().addSensor(s19);
        g1.getSensorListInGA().addSensor(s20);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is only one closest sensor")
    void calculateAverageOfRainfallReadingsForOneSensor() {
        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 5, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, 5, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, 6, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s21 = new Sensor("R0001","RainSensor", sDate1, l2, sT, "l/m2", rL1);
        Sensor s22 = new Sensor("R0002","RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s23 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s24 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s25 = new Sensor("R0003","RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s21);
        g1.getSensorListInGA().addSensor(s22);
        g1.getSensorListInGA().addSensor(s23);
        g1.getSensorListInGA().addSensor(s24);
        g1.getSensorListInGA().addSensor(s25);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(38.47, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is more than one sensor at same distance")
    void calculateAverageOfRainfallForTwoEquidistantSensors() {

        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 5, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 20);
        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 16);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 30);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date2);
        Reading r3 = new Reading(20.0, date2);
        Reading r4 = new Reading(22.0, date3);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date4 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date5 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date4);
        Reading r6 = new Reading(70.6, date4);
        Reading r7 = new Reading(54.3, date5);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);

        GregorianCalendar date6 = new GregorianCalendar(2017, 5, 12);
        GregorianCalendar date7 = new GregorianCalendar(2017, 6, 24);

        Reading r8 = new Reading(14.5, date6);
        Reading r9 = new Reading(70.6, date7);
        Reading r10 = new Reading(54.3, date7);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s26 = new Sensor("R0001","RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s27 = new Sensor("R0002","RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s28 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s29 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s30 = new Sensor("R0003","RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s26);
        g1.getSensorListInGA().addSensor(s27);
        g1.getSensorListInGA().addSensor(s28);
        g1.getSensorListInGA().addSensor(s29);
        g1.getSensorListInGA().addSensor(s30);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(20.46, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when equidistant sensor have readings with the same date")
    void calculateAverageOfRainfallReadingsWithSameDate() {

        g1.getSensorListInGA().getSensorList().clear();

        SensorType sT = new SensorType("rainfall");
        SensorType sT2 = new SensorType("wind");
        SensorType sT3 = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2017, 5, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, 6, 30);

        GregorianCalendar sDate1 = new GregorianCalendar(2017, 5, 28);
        GregorianCalendar sDate2 = new GregorianCalendar(2017, 4, 28);

        GregorianCalendar date1 = new GregorianCalendar(2017, 5, 20, 12, 00);

        Reading r1 = new Reading(12.3, date1);
        Reading r2 = new Reading(34.2, date1);
        Reading r3 = new Reading(20.0, date1);
        Reading r4 = new Reading(22.0, date1);

        ReadingList rL1 = new ReadingList();
        rL1.addReading(r1);
        rL1.addReading(r2);
        rL1.addReading(r3);
        rL1.addReading(r4);

        GregorianCalendar date2 = new GregorianCalendar(2017, 6, 5);
        GregorianCalendar date3 = new GregorianCalendar(2017, 6, 6);

        Reading r5 = new Reading(14.5, date2);
        Reading r6 = new Reading(70.6, date2);
        Reading r7 = new Reading(54.3, date3);

        ReadingList rL2 = new ReadingList();
        rL2.addReading(r5);
        rL2.addReading(r6);
        rL2.addReading(r7);


        GregorianCalendar date4 = new GregorianCalendar(2017, 5, 20, 14, 00);

        Reading r8 = new Reading(14.5, date4);
        Reading r9 = new Reading(70.6, date4);
        Reading r10 = new Reading(54.3, date4);

        ReadingList rL3 = new ReadingList();
        rL3.addReading(r8);
        rL3.addReading(r9);
        rL3.addReading(r10);

        Location l1 = new Location(45, -12, 200);
        Location l2 = new Location(47, -12, 200);

        Sensor s31 = new Sensor("R0001","RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s32 = new Sensor("R0002","RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s33 = new Sensor("W0001","WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s34 = new Sensor("T0001","TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s35 = new Sensor("R0003","RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        g1.getSensorListInGA().addSensor(s31);
        g1.getSensorListInGA().addSensor(s32);
        g1.getSensorListInGA().addSensor(s33);
        g1.getSensorListInGA().addSensor(s34);
        g1.getSensorListInGA().addSensor(s35);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL();

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(46.46, result, 0.1);
    }

}