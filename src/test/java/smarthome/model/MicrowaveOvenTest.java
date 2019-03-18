package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveOvenTest {

    @Test
    void getDeviceName() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        String expected = "Whirlpool Oven";
        String result = device.getName();

        assertEquals(expected, result);
    }


    @Test
    void getDeviceSpecs() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        DeviceSpecs result = device.getDeviceSpecs();

        assertEquals(specs, result);
    }

    @Test
    void getDeviceType() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        String expected = "MicrowaveOven";
        String result = device.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        assertTrue(device.isActive());
    }

    @Test
    void getActivityLog() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

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
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        double expected = 420;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void setDeviceName() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("blah", specs, 420);

        device.setDeviceName("Whirlpool Oven");

        String expected = "Whirlpool Oven";
        String result = device.getName();

        assertEquals(expected, result);
    }

    @Test
    void setNominalPower() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        device.setNominalPower(3000);
        double expected = 3000;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void deactivateActivatedDevice() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        assertTrue(device.deactivateDevice());
    }

    @Test
    void deactivateDeactivatedDevice() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        device.deactivateDevice();
        assertFalse(device.deactivateDevice());
    }

    @Test
    void getEnergyConsumptionZero() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);


        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        ReadingList log = device.getActivityLog();
        Reading r0 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 8, 0));
        Reading r1 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 10, 0));
        Reading r2 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 12, 0));
        Reading r3 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 58));
        Reading r4 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 59));

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
    void createProgram() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        Program grill = device.createProgram("Grill", 300);

        String expected = "Grill";
        String result = grill.getProgramName();

        assertEquals(expected, result);
    }

    @Test
    void addProgramToList() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        Program p1 = device.createProgram("Grill", 300);

        boolean result = device.addProgramToList(p1);

        assertTrue(result);
    }

    @Test
    void addSameProgramToList() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

        Program p1 = device.createProgram("Grill", 300);
        Program p3 = device.createProgram("Light On", 20);

        device.addProgramToList(p1);
        device.addProgramToList(p3);

        boolean result = device.addProgramToList(p1);

        assertFalse(result);
    }

    @Test
    void getProgramList() {
        MicrowaveOvenType type = new MicrowaveOvenType();
        MicrowaveOvenSpecs specs = new MicrowaveOvenSpecs(type.getDeviceType());
        MicrowaveOven device = new MicrowaveOven("Whirlpool Oven", specs, 420);

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
}