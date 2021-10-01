package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Whirlpool", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        String expected = "Whirlpool";
        String result = washingMachine.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        assertEquals("Washing Machine 1", washingMachine.getDeviceName());

        washingMachine.setDeviceName(" ");

        String expected = "Washing Machine 1";
        String result = washingMachine.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        assertEquals("Washing Machine 1", washingMachine.getDeviceName());

        washingMachine.setDeviceName("Whirlpool");

        String expected = "Whirlpol";
        String result = washingMachine.getDeviceName();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get device specs and check type")
    void getDeviceSpecs() {

        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);

        String[] attributeNames = {"Capacity"};
        String[] attributeUnits = {"L"};

        DeviceSpecs foo = new GenericSpecs("Washing Machine",attributeNames,attributeUnits);
        DeviceSpecs bar = d.getDeviceSpecs();

        String expected = foo.getDeviceType();
        String result = bar.getDeviceType();

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        String expected = "WashingMachine";
        String result = washingMachine.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        String expected = "WashingMachine";
        String result = washingMachine.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 200);
        WashingMachine washingMachine = (WashingMachine) d;

        assertEquals(200, washingMachine.getNominalPower());

        washingMachine.setNominalPower(210);

        double expected = 210;
        double result = washingMachine.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 200);
        WashingMachine washingMachine = (WashingMachine) d;

        assertEquals(200, washingMachine.getNominalPower());

        washingMachine.setNominalPower(-210);

        double expected = 200;
        double result = washingMachine.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        assertTrue(washingMachine.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;

        boolean result = washingMachine.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, Calendar.AUGUST, 26, 13, 0));
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        ReadingList activityLog = washingMachine.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);

        int expected = 2;
        int result = activityLog.size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        assertTrue(washingMachine.deactivateDevice());

        assertFalse(washingMachine.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        assertTrue(washingMachine.deactivateDevice());

        assertFalse(washingMachine.deactivateDevice());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0),"C");
        Reading r2 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15),"C");
        Reading r3 = new Reading(0.000, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r4 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r5 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45),"C");
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        ReadingList activityLog = washingMachine.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        Calendar startDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15);
        Calendar endDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45);

        double expected = 0.200;
        double result = washingMachine.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        Program eco = washingMachine.createProgram("Eco", 50);
        washingMachine.addProgramToList(eco);
        Program full = washingMachine.createProgram("Full", 200);
        washingMachine.addProgramToList(full);

        List<Program> expected = Arrays.asList(eco, full);
        List<Program> result = washingMachine.getProgramList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        Program eco = washingMachine.createProgram("Eco", 50);
        washingMachine.addProgramToList(eco);
        Program full = washingMachine.createProgram("Full", 200);

        assertTrue(washingMachine.addProgramToList(full));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        Program eco = washingMachine.createProgram("Eco", 50);
        washingMachine.addProgramToList(eco);

        assertFalse(washingMachine.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine washingMachine = (WashingMachine) d;
        Program eco = washingMachine.createProgram("Eco", 50);
        washingMachine.addProgramToList(eco);
        Program full = washingMachine.createProgram("Eco", 200);

        assertFalse(washingMachine.addProgramToList(full));
    }

    @Test
    @DisplayName("Get null if no metered program is set")
    void getMeteredNullProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine wm = (WashingMachine) d;
        ProgramWithTimer eco = wm.createProgram("Eco", 50);
        wm.addProgramToList(eco);

        assertNull(wm.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set a metered program")
    void setMeteredProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine wm = (WashingMachine) d;
        ProgramWithTimer eco = wm.createProgram("Eco", 50);
        wm.addProgramToList(eco);
        ProgramWithTimer fast = wm.createProgram("Fast", 200);
        wm.addProgramToList(fast);
        wm.setMeteredProgram("Eco");

        Program result = wm.getMeteredProgram();

        assertEquals(eco, result);

        wm.setMeteredProgram("Fast");

        assertEquals(fast, wm.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly get the estimated energy consumption")
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine wm = (WashingMachine) d;
        ProgramWithTimer slow = wm.createProgram("Slow", 50);
        wm.addProgramToList(slow);
        ProgramWithTimer fast = wm.createProgram("Fast", 200);
        wm.addProgramToList(fast);
        slow.setDuration(2);
        wm.setMeteredProgram("Slow");

        double expected = 50;
        double result = wm.getEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumptionNoProgram() {
        DeviceType dt = new WashingMachineType();
        Device d = dt.createDevice("Washing Machine 1", 100);
        WashingMachine wm = (WashingMachine) d;
        ProgramWithTimer slow = wm.createProgram("Slow", 50);
        wm.addProgramToList(slow);
        Program fast = wm.createProgram("Fast", 200);
        wm.addProgramToList(fast);

        double expected = 0;
        double result = wm.getEnergyConsumption();

        assertEquals(expected, result);
    }
}
