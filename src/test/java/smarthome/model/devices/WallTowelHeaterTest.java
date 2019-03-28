package smarthome.model.devices;

import org.junit.jupiter.api.Test;
import smarthome.model.*;
import smarthome.model.devices.WallTowelHeater;
import smarthome.model.devices.WallTowelHeaterType;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTowelHeaterTest {

    @Test
    void getDeviceName() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);

        String expected = "warm towels";
        String result = d.getName();

        assertEquals(expected, result);
    }


    /*@Test
    void getDeviceSpecs() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels",3420);

        DeviceSpecs expected = specs;
        DeviceSpecs result = device.getDeviceSpecs();

        assertEquals(expected,result);
    }
*/
    @Test
    void getDeviceType() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);

        String expected = "WallTowelHeater";
        String result = d.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        assertTrue(d.isActive());
    }

    @Test
    void getActivityLogTest() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        ReadingList log = d.getActivityLog();

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
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);

        double expected = 3420;
        double result = d.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void setDeviceName() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        d.setDeviceName("warm towels");

        String expected = "warm towels";
        String result = d.getName();

        assertEquals(expected, result);
    }

    @Test
    void setNominalPower() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        d.setNominalPower(3000);
        double expected = 3000;
        double result = d.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void deactivateActivatedDevice() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        assertTrue(d.deactivateDevice());
    }

    @Test
    void deactivateDeactivatedDevice() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);

        d.deactivateDevice();
        assertFalse(d.deactivateDevice());
    }


    @Test
    void getEnergyConsumptionTestZero() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        Metered m = (Metered) d;
        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = m.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption() {
        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 3420);
        Metered m = (Metered) d;

        ReadingList log = d.getActivityLog();
        Reading r0 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 8, 00));
        Reading r1 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 10, 00));
        Reading r2 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 12, 00));
        Reading r3 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 58));
        Reading r4 = new Reading(100, new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 19, 59));

        log.addReading(r0);
        log.addReading(r1);
        log.addReading(r2);
        log.addReading(r3);
        log.addReading(r4);

        Calendar startDate = new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 10);
        Calendar endDate = new GregorianCalendar(2019, Calendar.MARCH, 1, 1, 20);
        double energyConsumption = m.getEnergyConsumption(startDate, endDate);

        assertEquals(300.0, energyConsumption, 0.001);
    }

    @Test
    void getEstimatedEnergyConsumption() {

        DeviceType dt = new WallTowelHeaterType();
        Device d = dt.createDevice("warm towels", 2520);
        WallTowelHeater m = (WallTowelHeater) d;

        m.setTime(0.5);

        double expected = 1260;
        double result = m.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}