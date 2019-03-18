/*
package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.Dishwasher;
import smarthome.model.devices.DishwasherSpecs;


import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("LG Washer", dishwasherSpecs, 100);

        assertEquals("LG Washer", dishwasher.getName());

        dishwasher.setDeviceName("Whirlpool Washer");

        String expected = "Whirlpool Washer";
        String result = dishwasher.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", dishwasherSpecs, 100);

        assertEquals("Whirlpool Washer", dishwasher.getName());

        dishwasher.setDeviceName(" ");

        String expected = "Whirlpool Washer";
        String result = dishwasher.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", dishwasherSpecs, 100);

        assertEquals("Whirlpool Washer", dishwasher.getName());

        dishwasher.setDeviceName("Whirlpool Washer");

        String expected = "Whirlpol";
        String result = dishwasher.getName();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get correct device specs")
    void getDeviceSpecs() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 1500);
        DeviceSpecs dishwasherSpecs = dishwasher.getDeviceSpecs();

        assertEquals(specs, dishwasherSpecs);
    }

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Dishwasher", dishwasherSpecs, 100);

        String expected = "Dishwasher";
        String result = dishwasher.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Lamp");
        Dishwasher dishwasher = new Dishwasher("Dishwasher", dishwasherSpecs, 100);

        String expected = "Dishwasher";
        String result = dishwasher.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Dishwasher", dishwasherSpecs, 200);

        assertEquals(200, dishwasher.getNominalPower());

        dishwasher.setNominalPower(210);

        double expected = 210;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Dishwasher", dishwasherSpecs, 200);

        assertEquals(200, dishwasher.getNominalPower());

        dishwasher.setNominalPower(-210);

        double expected = 200;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 2500);

        assertTrue(dishwasher.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 2500);

        boolean result = dishwasher.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, Calendar.AUGUST, 26, 13, 0));
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 2500);
        ReadingList activityLog = dishwasher.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);

        int expected = 2;
        int result = activityLog.size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 2500);

        assertTrue(dishwasher.deactivateDevice());

        assertFalse(dishwasher.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool", specs, 2500);

        assertTrue(dishwasher.deactivateDevice());

        assertFalse(dishwasher.deactivateDevice());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0));
        Reading r2 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15));
        Reading r3 = new Reading(0.000, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30));
        Reading r4 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30));
        Reading r5 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45));
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", specs, 200);
        ReadingList activityLog = dishwasher.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        Calendar startDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15);
        Calendar endDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45);

        double expected = 0.400;
        double result = dishwasher.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Full", 200);
        dishwasher.addProgramToList(full);

        List<Program> expected = Arrays.asList(eco, full);
        List<Program> result = dishwasher.getProgramList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Full", 200);

        assertTrue(dishwasher.addProgramToList(full));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Whirlpool Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);

        assertFalse(dishwasher.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs("Dishwasher");
        Dishwasher dishwasher = new Dishwasher("Samsung WM", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Eco", 200);

        assertFalse(dishwasher.addProgramToList(full));
    }
}
*/
