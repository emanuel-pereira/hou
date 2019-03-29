package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OvenTest {

    @Test
    void getDeviceName() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        String expected = "baker";
        String result = device.getName();

        assertEquals(expected, result);
    }


    /*@Test
    void getDeviceSpecs() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        DeviceSpecs result = device.getDeviceSpecs();

        assertEquals(specs, result);
    }*/

    @Test
    void getDeviceType() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        String expected = "Oven";
        String result = device.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        assertTrue(device.isActive());
    }

    @Test
    void getActivityLog() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

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
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        double expected = 420;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void setDeviceName() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        device.setDeviceName("baker");

        String expected = "baker";
        String result = device.getName();

        assertEquals(expected, result);
    }

    @Test
    void setNominalPower() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);
        device.setNominalPower(3000);
        double expected = 3000;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void deactivateActivatedDevice() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        assertTrue(device.deactivateDevice());
    }

    @Test
    void deactivateDeactivatedDevice() {
        DeviceType dt = new OvenType();
        Device device = dt.createDevice("baker", 420);

        device.deactivateDevice();
        assertFalse(device.deactivateDevice());
    }

    @Test
    void getEnergyConsumptionZero() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);
        Metered device = (Metered) d;

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);
        Metered device = (Metered) d;

        ReadingList log = d.getActivityLog();
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
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        ProgramMode lightOn = device.createProgram("LightOn", 250);
        ProgramMode grill = device.createProgram("Grill", 300);

        device.addProgramToList(lightOn);
        device.addProgramToList(grill);

        grill.setTime(2);

        device.setMeteredProgram("Grill");

        double expected = 600;
        double result = device.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get zero estimated energy consumption if there is no set program")
    void getEstimatedEnergyConsumptionNoProgram() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        ProgramMode lightOn = device.createProgram("LightOn", 250);
        ProgramMode grill = device.createProgram("Grill", 300);

        device.addProgramToList(lightOn);
        device.addProgramToList(grill);

        grill.setTime(2);

        double expected = 0;
        double result = device.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    void createProgram() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        Program grill = device.createProgram("Grill", 300);

        String expected = "Grill";
        String result = grill.getProgramName();

        assertEquals(expected, result);
    }

    @Test
    void addProgramToList() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        Program p1 = device.createProgram("Grill", 300);

        boolean result = device.addProgramToList(p1);

        assertTrue(result);
    }

    @Test
    void addSameProgramToList() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        Program p1 = device.createProgram("Grill", 300);
        Program p3 = device.createProgram("Light On", 20);

        device.addProgramToList(p1);
        device.addProgramToList(p3);

        boolean result = device.addProgramToList(p1);

        assertFalse(result);
    }

    @Test
    void getProgramList() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

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
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        ProgramMode lightOn = device.createProgram("LightOn", 250);
        ProgramMode grill = device.createProgram("Grill", 300);

        device.addProgramToList(lightOn);
        device.addProgramToList(grill);

        assertNull(device.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set metered program")
    void setMeteredProgram() {
        DeviceType dt = new OvenType();
        Device d = dt.createDevice("baker", 420);

        Oven device = (Oven) d;

        ProgramMode lightOn = device.createProgram("LightOn", 250);
        ProgramMode grill = device.createProgram("Grill", 300);


        device.addProgramToList(lightOn);
        device.addProgramToList(grill);

        device.setMeteredProgram("Grill");

        Program expected = grill;
        Program result = device.getMeteredProgram();

        assertEquals(expected, result);
    }

}