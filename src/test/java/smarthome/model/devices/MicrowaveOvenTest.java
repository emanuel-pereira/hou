package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {

        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertEquals("Samsung", microwave.getName());

        microwave.setDeviceName("Samsung Microwave");

        String expected = "Samsung Microwave";
        String result = microwave.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung Microwave", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertEquals("Samsung Microwave", microwave.getName());

        microwave.setDeviceName(" ");

        String expected = "Samsung Microwave";
        String result = microwave.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertEquals("Samsung", microwave.getName());

        microwave.setDeviceName("Samsung Microwave");

        String expected = "Samsng microwace";
        String result = microwave.getName();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get device specs and check type")
    void getDeviceSpecs() {

        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Heat Ray 9001", 100);

        DeviceSpecs foo = new GenericNoSpecs("MicrowaveOven");
        DeviceSpecs bar = d.getDeviceSpecs();

        String expected = foo.getDeviceType();
        String result = bar.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        String expected = "MicrowaveOven";
        String result = microwave.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        String expected = "MicrowaveOven";
        String result = microwave.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 300);
        MicrowaveOven microwave = (MicrowaveOven) d;
        assertEquals(300, microwave.getNominalPower());

        microwave.setNominalPower(250);

        double expected = 250;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 200);
        MicrowaveOven microwave = (MicrowaveOven) d;
        assertEquals(200, microwave.getNominalPower());

        microwave.setNominalPower(-20);

        double expected = 200;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertTrue(microwave.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        boolean result = microwave.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, Calendar.AUGUST, 26, 13, 0));

        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        ReadingList activityLog = microwave.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);

        int expected = 2;
        int result = activityLog.size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertTrue(microwave.deactivateDevice());

        assertFalse(microwave.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;

        assertTrue(microwave.deactivateDevice());

        assertFalse(microwave.deactivateDevice());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0),"C");
        Reading r2 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15),"C");
        Reading r3 = new Reading(0.000, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r4 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r5 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45),"C");
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        ReadingList activityLog = microwave.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        Calendar startDate = new GregorianCalendar(2018, 7, 26, 12, 15);
        Calendar endDate = new GregorianCalendar(2018, 7, 26, 12, 45);

        double expected = 0.400;
        double result = microwave.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        Program slow = microwave.createProgram("Slow", 50);
        microwave.addProgramToList(slow);
        Program fast = microwave.createProgram("Fast", 200);
        microwave.addProgramToList(fast);

        List<Program> expected = Arrays.asList(slow, fast);
        List<Program> result = microwave.getProgramList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);
        Program summer = microwave.createProgram("Summer", 200);

        assertTrue(microwave.addProgramToList(summer));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);

        assertFalse(microwave.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);
        Program fast = microwave.createProgram("Eco", 200);

        assertFalse(microwave.addProgramToList(fast));
    }

    @Test
    @DisplayName("Get null if no program is set as metered")
    void getMeteredNullProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        ProgramMode eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);

        assertNull(microwave.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set metered program")
    void setMeteredProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);
        Program fast = microwave.createProgram("Fast", 200);
        microwave.addProgramToList(fast);
        microwave.setMeteredProgram("Eco");

        Program result = microwave.getMeteredProgram();

        assertEquals(eco, result);

        microwave.setMeteredProgram("Fast");

        assertEquals(fast, microwave.getMeteredProgram());
    }

    @Test
    @DisplayName("Get correct estimated energy consumption")
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        ProgramMode slow = microwave.createProgram("Slow", 50);
        microwave.addProgramToList(slow);
        Program fast = microwave.createProgram("Fast", 200);
        microwave.addProgramToList(fast);
        slow.setTime(2);
        microwave.setMeteredProgram("Slow");

        double expected = 100;
        double result = microwave.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get zero estimated energy consumption if there isn't no program")
    void getEstimatedEnergyConsumptionNoProgram() {
        DeviceType dt = new MicrowaveOvenType();
        Device d = dt.createDevice("Samsung", 10);
        MicrowaveOven microwave = (MicrowaveOven) d;
        ProgramMode slow = microwave.createProgram("Slow", 50);
        microwave.addProgramToList(slow);
        Program fast = microwave.createProgram("Fast", 200);
        microwave.addProgramToList(fast);

        double expected = 0;
        double result = microwave.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}
