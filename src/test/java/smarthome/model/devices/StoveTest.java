package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoveTest {

    @Test
    void setDeviceName() {


        DeviceType dt = new StoveType();
        Device d = dt.createDevice("Stove xpto", 1000);

        d.setDeviceName("kitchen Stove");

        String expected = "kitchen Stove";
        String result = d.getName();

        assertEquals(expected, result);
    }


    @Test
    void setNominalPower() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);

        stove.setNominalPower(2500);
        double expected = 2500.0;
        double result = stove.getNominalPower();

        assertEquals(expected, result);
    }


/*    @Test
    void getDeviceSpecs() {
        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);

        DeviceSpecs ds = stove.getDeviceSpecs();
    }*/


    @Test
    void getDeviceType() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);


        String deviceType = stove.getDeviceType();
        String expected = "Stove";

        assertEquals(expected, deviceType);


    }

    @Test
    void getNominalPower() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 2500);

        double expected = 2500.0;
        double result = stove.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);
        assertTrue(stove.isActive());
    }


    @Test
    void getActivityLog() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);

        ReadingList stoveActivityLog = stove.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), stoveActivityLog.size());
    }


    @Test
    void setAttributeValue() {

        DeviceType dt = new StoveType();
        Device s = dt.createDevice("kitchen stove", 0);
        DeviceSpecs stove = s.getDeviceSpecs();

        stove.setAttributeValue("capacity", 20.0);

        Double expected = 20.0;
        Double result = stove.getAttributeValue("capacity");

        assertEquals(expected, result);

    }

    @Test
    void deactivateDevice() {

        DeviceType dt = new StoveType();
        Device stove = dt.createDevice("kitchen stove", 0);

        assertTrue(stove.isActive());
        assertTrue(stove.deactivateDevice());
        assertFalse(stove.isActive());

    }

    @Test
    void getEnergyConsumption() {

        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Metered stove = (Metered) d;

        Calendar startHour = new GregorianCalendar();
        Calendar endHour = new GregorianCalendar();
        double energyConsumption = stove.getEnergyConsumption(startHour, endHour);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption1() {


        Reading r1 = new Reading(0.600, new GregorianCalendar(2018, Calendar.APRIL, 3, 14, 39));
        Reading r2 = new Reading(0.400, new GregorianCalendar(2017, Calendar.APRIL, 6, 10, 17));
        Reading r3 = new Reading(0.800, new GregorianCalendar(2018, Calendar.MARCH, 7, 11, 23));
        Reading r4 = new Reading(0.600, new GregorianCalendar(2017, Calendar.FEBRUARY, 8, 19, 33));
        Reading r5 = new Reading(0.400, new GregorianCalendar(2018, Calendar.FEBRUARY, 5, 8, 31));

        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Metered stove = (Metered) d;

        ReadingList activityLog = d.getActivityLog();

        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);

        Calendar startDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 1, 12, 20);
        Calendar endDate = new GregorianCalendar(2018, Calendar.DECEMBER, 31, 12, 30);

        Double expected = 1.80;
        Double result = stove.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result);

    }

    @Test
    void createProgram() {

        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Programmable stove = (Programmable) d;

        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);

        Program fast = stove.createProgram("Fast", 3000);
        stove.addProgramToList(fast);

        List<Program> expected = Arrays.asList(eco, fast);
        List<Program> result = stove.getProgramList();

        assertEquals(expected, result);

    }


    @Test
    void addNewProgram() {
        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Programmable stove = (Programmable) d;

        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);
        Program full = stove.createProgram("Full", 3000);

        assertTrue(stove.addProgramToList(full));
    }

    @Test
    void addSameProgram() {
        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Programmable stove = (Programmable) d;
        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);

        assertFalse(stove.addProgramToList(eco));
    }

    @Test
    void addProgramToList() {
        DeviceType dt = new StoveType();
        Device d = dt.createDevice("kitchen stove", 2500);
        Programmable stove = (Programmable) d;
        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);
        Program full = stove.createProgram("Eco", 1500);

        assertFalse(stove.addProgramToList(full));
    }
}