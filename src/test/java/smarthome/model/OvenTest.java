package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.Oven;
import smarthome.model.devices.OvenSpecs;
import smarthome.model.devices.OvenType;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OvenTest {

    @Test
    void getDeviceName() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        String expected = "baker";
        String result = device.getName();

        assertEquals(expected, result);
    }


    @Test
    void getDeviceSpecs() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        DeviceSpecs result = device.getDeviceSpecs();

        assertEquals(specs, result);
    }

    @Test
    void getDeviceType() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        String expected = "Oven";
        String result = device.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        assertTrue(device.isActive());
    }

    @Test
    void getActivityLog() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        ReadingList log = device.getActivityLog();

        Reading r1 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 1));
        Reading r2 = new Reading(200, new GregorianCalendar(2019, Calendar.MARCH, 1, 2, 0));
        Reading r3 = new Reading(150, new GregorianCalendar(2019, Calendar.MARCH, 15, 5, 8));
        Reading r4 = new Reading(250, new GregorianCalendar(2019, Calendar.MARCH, 28, 5, 8));

        log.addReading(r1);
        log.addReading(r2);
        log.addReading(r3);
        log.addReading(r4);


        List<Reading> expected = Arrays.asList(r1, r2, r3, r4);
        List<Reading> result = log.getReadingsList();

        assertEquals(expected, result);
    }

    @Test
    void getNominalPower() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        double expected = 420;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void setDeviceName() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("blah", specs, 420);

        device.setDeviceName("baker");

        String expected = "baker";
        String result = device.getName();

        assertEquals(expected, result);
    }

    @Test
    void setNominalPower() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        device.setNominalPower(3000);
        double expected = 3000;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void deactivateActivatedDevice() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        assertTrue(device.deactivateDevice());
    }

    @Test
    void deactivateDeactivatedDevice() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        device.deactivateDevice();
        assertFalse(device.deactivateDevice());
    }

    @Test
    void getEnergyConsumptionZero() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);


        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        ReadingList log = device.getActivityLog();
        Reading r0 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 8, 0),"C");
        Reading r1 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 10, 0),"C");
        Reading r2 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 12, 0),"C");
        Reading r3 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 58),"C");
        Reading r4 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 59),"C");

        log.addReading(r0);
        log.addReading(r1);
        log.addReading(r2);
        log.addReading(r3);
        log.addReading(r4);

        Calendar startDate = new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 10);
        Calendar endDate = new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 20);
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(300.0, energyConsumption, 0.001);
    }

    @Test
    @DisplayName("Get correct estimated energy consumption")
    void getEstimatedEnergyConsumption() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs (type.getDeviceType());
        Oven device = new Oven ("baker", specs, 420);

        ProgramMode lightOn = device.createProgram ("LightOn",  250);
        ProgramMode grill = device.createProgram ("Grill",  300);

        device.addProgramToList (lightOn);
        device.addProgramToList (grill);

        grill.setTime (2);

        device.setMeteredProgram ("Grill");

        double expected = 600;
        double result = device.getEstimatedEnergyConsumption();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Get zero estimated energy consumption if there is no set program")
    void getEstimatedEnergyConsumptionNoProgram() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs (type.getDeviceType());
        Oven device = new Oven ("baker", specs, 420);

        ProgramMode lightOn = device.createProgram ("LightOn",  250);
        ProgramMode grill = device.createProgram ("Grill",  300);

        device.addProgramToList (lightOn);
        device.addProgramToList (grill);

        grill.setTime (2);

        double expected = 0;
        double result = device.getEstimatedEnergyConsumption();

        assertEquals (expected, result);
    }

    @Test
    void createProgram() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        Program grill = device.createProgram("Grill", 300);

        String expected = "Grill";
        String result = grill.getProgramName();

        assertEquals(expected, result);
    }

    @Test
    void addProgramToList() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        Program p1 = device.createProgram("Grill", 300);

        boolean result = device.addProgramToList(p1);

        assertTrue(result);
    }

    @Test
    void addSameProgramToList() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        Program p1 = device.createProgram("Grill", 300);
        Program p3 = device.createProgram("Light On", 20);

        device.addProgramToList(p1);
        device.addProgramToList(p3);

        boolean result = device.addProgramToList(p1);

        assertFalse(result);
    }

    @Test
    void getProgramList() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs(type.getDeviceType());
        Oven device = new Oven("baker", specs, 420);

        Program p1 = device.createProgram("Grill", 300);
        Program p2 = device.createProgram("Fan", 250);
        Program p3 = device.createProgram("Light On", 20);

        device.addProgramToList(p1);
        device.addProgramToList(p2);
        device.addProgramToList(p3);

        List<Program> expected = Arrays.asList(p1, p2, p3);
        List<Program> result = device.getProgramList();

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get null if no program is set as metered")
    void getMeteredNullProgram() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs (type.getDeviceType());
        Oven device = new Oven ("baker", specs, 420);

        ProgramMode lightOn = device.createProgram ("LightOn",  250);
        ProgramMode grill = device.createProgram ("Grill",  300);

        device.addProgramToList (lightOn);
        device.addProgramToList (grill);

        assertNull (device.getMeteredProgram ());
    }

    @Test
    @DisplayName("Correctly set metered program")
    void setMeteredProgram() {
        OvenType type = new OvenType();
        OvenSpecs specs = new OvenSpecs (type.getDeviceType());
        Oven device = new Oven ("baker", specs, 420);

        ProgramMode lightOn = device.createProgram ("LightOn",  250);
        ProgramMode grill = device.createProgram ("Grill",  300);


        device.addProgramToList (lightOn);
        device.addProgramToList (grill);

        device.setMeteredProgram ("Grill");

        Program expected = grill;
        Program result = device.getMeteredProgram ();

        assertEquals (expected, result);
    }

}