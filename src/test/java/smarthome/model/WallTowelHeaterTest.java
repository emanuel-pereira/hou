package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {

    @Test
    void getDeviceName() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        String expected = "warm towels";
        String result = device.getDeviceName();

        assertEquals(expected,result);
    }

    @Test
    void getDeviceNameMetered() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        String expected = "warm towels";
        String result = device.getName();

        assertEquals(expected,result);
    }

    @Test
    void getDeviceSpecs() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        DeviceSpecs expected = specs;
        DeviceSpecs result = device.getDeviceSpecs();

        assertEquals(expected,result);
    }

    @Test
    void getDeviceType() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        String expected = "WallTowelHeater";
        String result = device.getDeviceType();

        assertEquals(expected,result);
    }

    @Test
    void isActive() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        assertTrue(device.isActive());
    }

    @Test
    void getActivityLogTest() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);
        ReadingList log = device.getActivityLog();

        Reading r1 = new Reading(100,new GregorianCalendar(2019,2,1,1,1));
        Reading r2 = new Reading(200,new GregorianCalendar(2019,2,1,2,0));
        Reading r3 = new Reading(150,new GregorianCalendar(2019,2,15,5,8));
        Reading r4 = new Reading(250,new GregorianCalendar(2019,2,28,5,8));

        log.addReading(r1);
        log.addReading(r2);
        log.addReading(r3);
        log.addReading(r4);


        List<Reading> expected = Arrays.asList(r1,r2,r3,r4);
        List<Reading> result = log.getReadingsList();

        assertEquals(expected,result);
    }


    @Test
    void getNominalPower() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);

        double expected = 3420;
        double result = device.getNominalPower();

        assertEquals(expected,result);
    }

    @Test
    void setDeviceName() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");
        WallTowelHeater device = new WallTowelHeater("blah", specs, 3420);
        device.setDeviceName("warm towels");

        String expected = "warm towels";
        String result = device.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    void setNominalPower() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");

        WallTowelHeater device = new WallTowelHeater("warm towels", specs,0);
        device.setNominalPower(3000);
        double expected = 3000;
        double result = device.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void deactivateActivatedDevice() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");

        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);
        assertTrue(device.deactivateDevice());
    }

    @Test
    void deactivateDeactivatedDevice() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");

        WallTowelHeater device = new WallTowelHeater("warm towels", specs, 3420);
        device.deactivateDevice();
        assertFalse(device.deactivateDevice());
    }


    @Test
    void getEnergyConsumptionTestZero() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");

        WallTowelHeater device = new WallTowelHeater("super kettle", specs, 1500);

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0 ,energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption() {
        WallTowelHeaterSpecs specs = new WallTowelHeaterSpecs("WallTowelHeater");

        WallTowelHeater device = new WallTowelHeater("super kettle", specs, 1500);

        ReadingList log = device.getActivityLog();
        Reading r0 = new Reading(100,new GregorianCalendar(2019,2,1,1,8,00));
        Reading r1 = new Reading(100,new GregorianCalendar(2019,2,1,1,10,00));
        Reading r2 = new Reading(100,new GregorianCalendar(2019,2,1,1,12,00));
        Reading r3 = new Reading(100,new GregorianCalendar(2019,2,1,1,19,58));
        Reading r4 = new Reading(100,new GregorianCalendar(2019,2,1,1,19,59));

        log.addReading(r0);
        log.addReading(r1);
        log.addReading(r2);
        log.addReading(r3);
        log.addReading(r4);

        Calendar startDate = new GregorianCalendar(2019,2,1,1,10);
        Calendar endDate = new GregorianCalendar(2019,2,1,1,20);
        double energyConsumption = device.getEnergyConsumption(startDate, endDate);

        assertEquals(300.0 ,energyConsumption, 0.001);
    }
}