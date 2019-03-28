package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.MicrowaveOven;
import smarthome.model.devices.MicrowaveOvenSpecs;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung", microwaveSpecs, 10);

        assertEquals("Samsung", microwave.getName());

        microwave.setDeviceName("Samsung Microwave");

        String expected = "Samsung Microwave";
        String result = microwave.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 10);

        assertEquals("Samsung Microwave", microwave.getName());

        microwave.setDeviceName(" ");

        String expected = "Samsung Microwave";
        String result = microwave.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung", microwaveSpecs, 20);

        assertEquals("Samsung", microwave.getName());

        microwave.setDeviceName("Samsung Microwave");

        String expected = "Samsng microwace";
        String result = microwave.getName();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get correct device specs")
    void getDeviceSpecs() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 1.5);

        DeviceSpecs result = microwave.getDeviceSpecs();

        assertEquals(microwaveSpecs, result);
    }

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 100);

        String expected = "MicrowaveOven";
        String result = microwave.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("Lamp");
        MicrowaveOven microwave = new MicrowaveOven("Washing Machine", microwaveSpecs, 100);

        String expected = "MicrowaveOven";
        String result = microwave.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("MicrowaveOven", microwaveSpecs, 300);

        assertEquals(300, microwave.getNominalPower());

        microwave.setNominalPower(250);

        double expected = 250;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("MicrowavOven", microwaveSpecs, 200);

        assertEquals(200, microwave.getNominalPower());

        microwave.setNominalPower(-20);

        double expected = 200;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");

        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 0.78);

        assertTrue(microwave.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");

        MicrowaveOven microwave = new MicrowaveOven("Whirlpool", microwaveSpecs, 76);

        boolean result = microwave.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, Calendar.AUGUST, 26, 13, 0));
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 2500);
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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 2500);

        assertTrue(microwave.deactivateDevice());

        assertFalse(microwave.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 50);

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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 20);
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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("MicrowaveOven", microwaveSpecs, 200);
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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung MicrowaveOven", microwaveSpecs, 100);
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);
        Program summer = microwave.createProgram("Summer", 200);

        assertTrue(microwave.addProgramToList(summer));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung MicrowaveOven", microwaveSpecs, 200);
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);

        assertFalse(microwave.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 200);
        Program eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);
        Program fast = microwave.createProgram("Eco", 200);

        assertFalse(microwave.addProgramToList(fast));
    }

    @Test
    @DisplayName("Get null if no program is set as metered")
    void getMeteredNullProgram() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 200);
        ProgramMode eco = microwave.createProgram("Eco", 50);
        microwave.addProgramToList(eco);

        assertNull(microwave.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set metered program")
    void setMeteredProgram() {
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("Samsung Microwave", microwaveSpecs, 200);
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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("MicrowaveOven", microwaveSpecs, 200);
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
        MicrowaveOvenSpecs microwaveSpecs = new MicrowaveOvenSpecs("MicrowaveOven");
        MicrowaveOven microwave = new MicrowaveOven("MicrowaveOven", microwaveSpecs, 200);
        ProgramMode slow = microwave.createProgram("Slow", 50);
        microwave.addProgramToList(slow);
        Program fast = microwave.createProgram("Fast", 200);
        microwave.addProgramToList(fast);

        double expected = 0;
        double result = microwave.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}
