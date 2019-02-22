package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRLTest {


    @Test
    @DisplayName("Tests if the list of rainfall sensors in the geographical area of the house is returned")
    void getGARainfallSensors() {
        Address a1 = new Address("Rua Fernandes Tomás,345", "4200-734", "Porto", 85, 120, -2300);
        GeographicalArea ga = new GeographicalArea("PT", "Porto", "city", 86, 120, -2300, 234, 345);
        House h1 = new House(a1, ga);


        SensorType rain = new SensorType("rainfall");
        SensorType wind = new SensorType("wind");
        SensorType temperature = new SensorType("temperature");

        GregorianCalendar startDate = new GregorianCalendar(2018, 12, 26, 12, 0);
        ReadingList readings = new ReadingList();

        Sensor s1 = new Sensor("sensor1", startDate, rain, "c", readings);
        Sensor s2 = new Sensor("sensor2", startDate, temperature, "c", readings);
        Sensor s3 = new Sensor("sensor3", startDate, wind, "c", readings);
        Sensor s4 = new Sensor("sensor4", startDate, rain, "c", readings);
        Sensor s5 = new Sensor("sensor5", startDate, rain, "c", readings);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(h1);

        List<Sensor> expected = Arrays.asList(s1, s4, s5);
        List<Sensor> result = ctrl623.getGARainfallSensors(rain).getSensorList();


        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Tests if the closest rainfall sensors to the house with readings in period are returned")
    void getClosestRainfallSensorsWithReadingsInTimePeriod() {

        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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


        Sensor s1 = new Sensor("RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l1, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l2, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);

        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        List<Sensor> expected = Arrays.asList(s1, s2);
        List<Sensor> result = ctrl623.getClosestRainfallSensorsWithReadingsInTimePeriod(sT, startDate, endDate).getSensorList();

        assertEquals(expected, result);


    }


    @Test
    @DisplayName("Tests if returns false when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsDoNotHaveReadingsInPeriod() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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

        Sensor s1 = new Sensor("RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if returns true when the closest rainfall sensors do not have readings in period")
    void checkThatClosestRainfallSensorsHaveReadingsInPeriod() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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

        Sensor s1 = new Sensor("RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        boolean result = ctrl623.checkIfClosestRainfallSensorsHaveReadingsInPeriod(sT, startDate, endDate);

        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is only one closest sensor")
    void calculateAverageOfRainfallReadingsForOneSensor() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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

        Sensor s1 = new Sensor("RainSensor", sDate1, l2, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(38.47, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when there is more than one sensor at same distance")
    void calculateAverageOfRainfallForTwoEquidistantSensors() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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

        Sensor s1 = new Sensor("RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(20.46, result, 0.1);
    }

    @Test
    @DisplayName("Tests if calculates daily average rainfall when equidistant sensor have readings with the same date")
    void calculateAverageOfRainfallReadingsWithSameDate() {
        Address a1 = new Address("Rua de Cedofeita", "4000-678", "Porto", 40, -12, 200);
        GeographicalArea ga = new GeographicalArea("Pt", "Porto", "city", 40, -12, 200, 23, 45);
        House house = new House(a1, ga);

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

        Sensor s1 = new Sensor("RainSensor", sDate1, l1, sT, "l/m2", rL1);
        Sensor s2 = new Sensor("RainSensor2", sDate2, l2, sT, "l/m2", rL2);
        Sensor s3 = new Sensor("WindSensor", sDate1, l2, sT2, "km/h", rL1);
        Sensor s4 = new Sensor("TempSensor", sDate2, l2, sT3, "C", rL2);
        Sensor s5 = new Sensor("RainSensor3", sDate1, l1, sT, "l/m2", rL3);


        ga.getSensorListInGA().addSensor(s1);
        ga.getSensorListInGA().addSensor(s2);
        ga.getSensorListInGA().addSensor(s3);
        ga.getSensorListInGA().addSensor(s4);
        ga.getSensorListInGA().addSensor(s5);


        GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL ctrl623 = new GetAverageDailyRainfallForTimeIntervalInHouseAreaCTRL(house);

        double result = ctrl623.calculateAverageOfRainfallReadings(sT, startDate, endDate);

        assertEquals(46.46, result, 0.1);
    }

}