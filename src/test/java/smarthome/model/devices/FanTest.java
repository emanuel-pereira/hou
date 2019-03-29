package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FanTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Fan 1", 10);
        Fan fan = (Fan) d;

        assertEquals("Fan 1", fan.getName());

        fan.setDeviceName("Whirlpool");

        String expected = "Whirlpool";
        String result = fan.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        assertEquals("Whirlpool", fan.getName());

        fan.setDeviceName(" ");

        String expected = "Whirlpool";
        String result = fan.getName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Fan 1", 10);
        Fan fan = (Fan) d;

        assertEquals("Fan 1", fan.getName());

        fan.setDeviceName("Whirlpool");

        String expected = "Something";
        String result = fan.getName();

        assertNotEquals(expected, result);
    }

/*    @Test
    @DisplayName("Get correct device specs")
    void getDeviceSpecs() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        DeviceSpecs result = fan.getDeviceSpecs();

        assertEquals(fanSpecs, result);
    }*/

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        String expected = "Fan";
        String result = fan.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        String expected = "Fan";
        String result = fan.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 300);
        Fan fan = (Fan) d;

        assertEquals(300, fan.getNominalPower());

        fan.setNominalPower(250);

        double expected = 250;
        double result = fan.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 200);
        Fan fan = (Fan) d;

        assertEquals(200, fan.getNominalPower());

        fan.setNominalPower(-20);

        double expected = 200;
        double result = fan.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        assertTrue(fan.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        boolean result = fan.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, 7, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 7, 26, 13, 0));
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        ReadingList activityLog = fan.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);

        int expected = 2;
        int result = activityLog.size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        assertTrue(fan.deactivateDevice());

        assertFalse(fan.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;

        assertTrue(fan.deactivateDevice());

        assertFalse(fan.deactivateDevice());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading(0.200, new GregorianCalendar(2018, 7, 26, 12, 0));
        Reading r2 = new Reading(0.200, new GregorianCalendar(2018, 7, 26, 12, 15));
        Reading r3 = new Reading(0.000, new GregorianCalendar(2018, 7, 26, 12, 30));
        Reading r4 = new Reading(0.200, new GregorianCalendar(2018, 7, 26, 12, 30));
        Reading r5 = new Reading(0.200, new GregorianCalendar(2018, 7, 26, 12, 45));
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        ReadingList activityLog = fan.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        Calendar startDate = new GregorianCalendar(2018, 7, 26, 12, 15);
        Calendar endDate = new GregorianCalendar(2018, 7, 26, 12, 45);

        double expected = 0.400;
        double result = fan.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        Program slow = fan.createProgram("Slow", 50);
        fan.addProgramToList(slow);
        Program fast = fan.createProgram("Fast", 200);
        fan.addProgramToList(fast);

        List<Program> expected = Arrays.asList(slow, fast);
        List<Program> result = fan.getProgramList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        Program eco = fan.createProgram("Eco", 50);
        fan.addProgramToList(eco);
        Program summer = fan.createProgram("Summer", 200);

        assertTrue(fan.addProgramToList(summer));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        Program eco = fan.createProgram("Eco", 50);
        fan.addProgramToList(eco);

        assertFalse(fan.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        Program eco = fan.createProgram("Eco", 50);
        fan.addProgramToList(eco);
        Program fast = fan.createProgram("Eco", 200);

        assertFalse(fan.addProgramToList(fast));
    }

    @Test
    @DisplayName("Get null if no program is set as metered")
    void getMeteredNullProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        ProgramMode eco = fan.createProgram("Eco", 50);
        fan.addProgramToList(eco);

        assertNull(fan.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set metered program")
    void setMeteredProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        Program eco = fan.createProgram("Eco", 50);
        fan.addProgramToList(eco);
        Program fast = fan.createProgram("Fast", 200);
        fan.addProgramToList(fast);
        fan.setMeteredProgram("Eco");

        Program result = fan.getMeteredProgram();

        assertEquals(eco, result);

        fan.setMeteredProgram("Fast");

        assertEquals(fast, fan.getMeteredProgram());
    }

    @Test
    @DisplayName("Get correct estimated energy consumption")
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        ProgramMode slow = fan.createProgram("Slow", 50);
        fan.addProgramToList(slow);
        Program fast = fan.createProgram("Fast", 200);
        fan.addProgramToList(fast);
        slow.setTime(2);
        fan.setMeteredProgram("Slow");

        double expected = 100;
        double result = fan.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get zero estimated energy consumption if there isn't no program")
    void getEstimatedEnergyConsumptionNoProgram() {
        DeviceType dt = new FanType();
        Device d = dt.createDevice("Whirlpool", 10);
        Fan fan = (Fan) d;
        ProgramMode slow = fan.createProgram("Slow", 50);
        fan.addProgramToList(slow);
        Program fast = fan.createProgram("Fast", 200);
        fan.addProgramToList(fast);

        double expected = 0;
        double result = fan.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}
