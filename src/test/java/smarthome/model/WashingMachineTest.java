package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine 1", washingMachineSpecs, 100);

        assertEquals ("Washing Machine 1", washingMachine.getDeviceName ());

        washingMachine.setDeviceName ("Whirlpool");

        String expected = "Whirlpool";
        String result = washingMachine.getDeviceName ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Whirlpool", washingMachineSpecs, 100);

        assertEquals ("Whirlpool", washingMachine.getDeviceName ());

        washingMachine.setDeviceName (" ");

        String expected = "Whirlpool";
        String result = washingMachine.getDeviceName ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine 1", washingMachineSpecs, 100);

        assertEquals ("Washing Machine 1", washingMachine.getDeviceName ());

        washingMachine.setDeviceName ("Whirlpool");

        String expected = "Whirlpol";
        String result = washingMachine.getDeviceName ();

        assertNotEquals (expected, result);
    }

    @Test
    @DisplayName("Get correct device specs")
    void getDeviceSpecs() {
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");

        WashingMachine washingMachine = new WashingMachine ("WashingMachine 1", specs, 1500);
        DeviceSpecs washingMachineSpecs = washingMachine.getDeviceSpecs ();

        assertEquals (specs, washingMachineSpecs);
    }

    @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine", washingMachineSpecs, 100);

        String expected = "Washing Machine";
        String result = washingMachine.getDeviceType ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Lamp");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine", washingMachineSpecs, 100);

        String expected = "Washing Machine";
        String result = washingMachine.getDeviceType ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine", washingMachineSpecs, 200);

        assertEquals (200, washingMachine.getNominalPower ());

        washingMachine.setNominalPower (210);

        double expected = 210;
        double result = washingMachine.getNominalPower ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Washing Machine", washingMachineSpecs, 200);

        assertEquals (200, washingMachine.getNominalPower ());

        washingMachine.setNominalPower (-210);

        double expected = 200;
        double result = washingMachine.getNominalPower ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");

        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 2500);

        assertTrue (washingMachine.isActive ());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");

        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 2500);

        boolean result = washingMachine.isActive ();

        assertNotEquals (false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading (15, new GregorianCalendar (2018, 7, 26, 12, 0));
        Reading r2 = new Reading (18, new GregorianCalendar (2018, 7, 26, 13, 0));
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 2500);
        ReadingList activityLog = washingMachine.getActivityLog ();
        activityLog.addReading (r1);
        activityLog.addReading (r2);

        int expected = 2;
        int result = activityLog.size ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 2500);

        assertTrue (washingMachine.deactivateDevice ());

        assertFalse(washingMachine.isActive ());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 2500);

        assertTrue (washingMachine.deactivateDevice ());

        assertFalse (washingMachine.deactivateDevice ());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading (0.200, new GregorianCalendar (2018, 7, 26, 12, 0));
        Reading r2 = new Reading (0.200, new GregorianCalendar (2018, 7, 26, 12, 15));
        Reading r3 = new Reading (0.000, new GregorianCalendar (2018, 7, 26, 12, 30));
        Reading r4 = new Reading (0.200, new GregorianCalendar (2018, 7, 26, 12, 30));
        Reading r5 = new Reading (0.200, new GregorianCalendar (2018, 7, 26, 12, 45));
        WashingMachineSpecs specs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Whirlpool", specs, 200);
        ReadingList activityLog = washingMachine.getActivityLog ();
        activityLog.addReading (r1);
        activityLog.addReading (r2);
        activityLog.addReading (r3);
        activityLog.addReading (r4);
        activityLog.addReading (r5);
        Calendar startDate = new GregorianCalendar (2018, 7, 26,12,15);
        Calendar endDate = new GregorianCalendar (2018, 7, 26, 12, 45);

        double expected = 0.400;
        double result = washingMachine.getEnergyConsumption (startDate, endDate);

        assertEquals (expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Samsung WM", washingMachineSpecs, 200);
        Program eco = washingMachine.createProgram ("Eco", 50);
        washingMachine.addProgramToList (eco);
        Program full = washingMachine.createProgram ("Full", 200);
        washingMachine.addProgramToList (full);

        List<Program> expected = Arrays.asList (eco,full);
        List<Program> result = washingMachine.getProgramList ();

        assertEquals (expected,result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Samsung WM", washingMachineSpecs, 200);
        Program eco = washingMachine.createProgram ("Eco", 50);
        washingMachine.addProgramToList (eco);
        Program full = washingMachine.createProgram ("Full", 200);

        assertTrue (washingMachine.addProgramToList (full));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Samsung WM", washingMachineSpecs, 200);
        Program eco = washingMachine.createProgram ("Eco", 50);
        washingMachine.addProgramToList (eco);

        assertFalse (washingMachine.addProgramToList (eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        WashingMachineSpecs washingMachineSpecs = new WashingMachineSpecs ("Washing Machine");
        WashingMachine washingMachine = new WashingMachine ("Samsung WM", washingMachineSpecs, 200);
        Program eco = washingMachine.createProgram ("Eco", 50);
        washingMachine.addProgramToList (eco);
        Program full = washingMachine.createProgram ("Eco", 200);

        assertFalse (washingMachine.addProgramToList (full));
    }
}
