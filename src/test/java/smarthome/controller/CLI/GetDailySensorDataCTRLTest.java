package smarthome.controller.CLI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.controller.CLI.GetDailySensorDataCTRL;
import smarthome.dto.ReadingDTO;
import smarthome.io.ui.UtilsUI;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetDailySensorDataCTRLTest {

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
    @DisplayName("Tests if filter by type and interval is working")
    void filterByTypeAndInterval() {

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        SensorList sensorList = ctrl.filterByTypeAndInterval(sensorType, startDate, endDate);
        List<Sensor> expected = new ArrayList<>();
        expected.add(sensor);
        assertEquals(expected, sensorList.getSensorList());
    }

    @Test
    void checkIfClosestSensorsHasReadingsInTimePeriod() {

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        assertTrue(ctrl.checkIfClosestSensorsHasReadingsInTimePeriodCTRL(sensorType, startDate, endDate));
    }

    @Test
    void displayMaximum() {

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayMaximum(sensorType, startDate, endDate);
        String expected = "2017-06-01";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayMinimum() {

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayMinimum(sensorType, startDate, endDate);
        String expected = "2017-06-04";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayAmplitude() {

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.MAY, 1);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 30);
        ReadingDTO readingDTO = ctrl.displayAmplitude(sensorType, startDate, endDate);
        String expected = "2017-06-01";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayAmplitude2() {

         

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 2);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 3);
        ReadingDTO readingDTO = ctrl.displayAmplitude(sensorType, startDate, endDate);
        String expected = "2017-06-02";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void displayAmplitude3() {

         

        Reading r0 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 0, 0),"C");
        Reading r1 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 1, 6, 0),"C");
        Reading r2 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 1, 12, 0),"C");
        Reading r3 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 1, 18, 0),"C");
        Reading r4 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 0, 0),"C");
        Reading r5 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 2, 6, 0),"C");
        Reading r6 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 2, 12, 0),"C");
        Reading r7 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 2, 18, 0),"C");
        Reading r8 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 0, 0),"C");
        Reading r9 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 3, 6, 0),"C");
        Reading r10 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 3, 12, 0),"C");
        Reading r11 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 3, 18, 0),"C");
        Reading r12 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 0, 0),"C");
        Reading r13 = new Reading(5, new GregorianCalendar(2017, Calendar.JUNE, 4, 6, 0),"C");
        Reading r14 = new Reading(6, new GregorianCalendar(2017, Calendar.JUNE, 4, 12, 0),"C");
        Reading r15 = new Reading(4, new GregorianCalendar(2017, Calendar.JUNE, 4, 18, 0),"C");

        ReadingList sensorRL = new ReadingList();
        sensorRL.addReading(r0);
        sensorRL.addReading(r1);
        sensorRL.addReading(r2);
        sensorRL.addReading(r3);
        sensorRL.addReading(r4);
        sensorRL.addReading(r5);
        sensorRL.addReading(r6);
        sensorRL.addReading(r7);
        sensorRL.addReading(r8);
        sensorRL.addReading(r9);
        sensorRL.addReading(r10);
        sensorRL.addReading(r11);
        sensorRL.addReading(r12);
        sensorRL.addReading(r13);
        sensorRL.addReading(r14);
        sensorRL.addReading(r15);

        GregorianCalendar sensorStartDate = new GregorianCalendar(2017, Calendar.APRIL, 28);
        Location sensorLocation = new Location(47, -12, 200);
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);

        ExternalSensor sensor = new ExternalSensor("", "TempSensor", sensorStartDate, sensorLocation, sensorType, "Celsius", sensorRL);

        g1.getSensorListInGA().addSensor(sensor);

        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        GregorianCalendar startDate = new GregorianCalendar(2017, Calendar.JUNE, 3);
        GregorianCalendar endDate = new GregorianCalendar(2017, Calendar.JUNE, 3);
        ReadingDTO readingDTO = ctrl.displayAmplitude(sensorType, startDate, endDate);
        String expected = "2017-06-03";
        String result = UtilsUI.dateToString(readingDTO.getReadingDateAndTime());
        assertEquals(expected, result);
    }

    @Test
    void checkIfSensorTypeExistsTrue() {
         
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        assertTrue(ctrl.checkIfSensorTypeExists("temperature"));
    }

    @Test
    void checkIfSensorTypeExistsFalse() {
         
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        assertFalse(ctrl.checkIfSensorTypeExists("rainfall"));
    }

    @Test
    void isHouseGAConfiguredTrue() {
         
        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        assertTrue(ctrl.isHouseGAConfigured());
    }

    @Test
    void isHouseGAConfiguredFalse() {
         

        SensorType sensorType = new SensorType("temperature");
        SensorTypeList sensorTypeList = new SensorTypeList();
        sensorTypeList.addSensorType(sensorType);
        GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

        //TODO not possible to test false case scenario
        assertFalse(!ctrl.isHouseGAConfigured());
    }

    @Test
    void filterByType() {

            SensorType sensorType = new SensorType("temperature");
            SensorTypeList sensorTypeList = new SensorTypeList();
            sensorTypeList.addSensorType(sensorType);
            GetDailySensorDataCTRL ctrl = new GetDailySensorDataCTRL(sensorTypeList);

            assertEquals(0, ctrl.filterByType(sensorType));
        }


}