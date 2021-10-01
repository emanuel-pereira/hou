package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LampTest {

    @Test
    void setDeviceName() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);

        lamp.setDeviceName("super Lamp");

        String expected = "super Lamp";
        String result = lamp.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);
        DeviceSpecs specs = lamp.getDeviceSpecs();

        specs.setAttributeValue("Illuminance", 1200);

        Double expected = 1200.0;
        Double result = specs.getAttributeValue("Illuminance");

        assertEquals(expected, result);
    }

    @Test
    void deactivateDevice() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);
        DeviceSpecs specs = lamp.getDeviceSpecs();

        assertTrue(lamp.isActive());
        assertTrue(lamp.deactivateDevice());
        assertFalse(lamp.isActive());
    }

    @Test
    void getDeviceType() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);
        DeviceSpecs specs = lamp.getDeviceSpecs();

        String deviceType = lamp.getDeviceType();

        String expected = "Lamp";

        assertEquals(expected, deviceType);
    }

/*    @Test
    void getDeviceSpecs() {
        LampSpecs specs = new LampSpecs("Lamp");

        Lamp lamp = new Lamp("super Lamp", specs, 15);
        DeviceSpecs LampSpecs = lamp.getDeviceSpecs();

        assertEquals(specs, LampSpecs);
    }*/

    @Test
    void getNominalPower() {
        DeviceType dt = new LampType();
        Device d = dt.createDevice("Lamp", 15);
        Lamp lamp = (Lamp) d;

        Double expected = 15.0;
        Double result = lamp.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);
        DeviceSpecs specs = lamp.getDeviceSpecs();

        assertTrue(lamp.isActive());
    }

    @Test
    void getActivityLog() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);
        DeviceSpecs specs = lamp.getDeviceSpecs();


        ReadingList lampActivityLog = lamp.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), lampActivityLog.size());
    }

    @Test
    @DisplayName("getEnergyConsumption execution with no Readings")
    void getEnergyConsumption() {
        DeviceType dt = new LampType();
        Device d = dt.createDevice("Lamp", 15);
        Lamp lamp = (Lamp) d;

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = lamp.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    @DisplayName("getEnergyConsumption execution with valid Readings")
    void getEnergyConsumption2() {
        DeviceType dt = new LampType();
        Device d = dt.createDevice("Lamp", 15);
        Lamp lamp = (Lamp) d;

        ReadingList lampActivityLog = lamp.getActivityLog();

        int year = 2018;
        int month = 11;
        int day = 31;
        int hour = 0;
        int minutes = 0;
        int meteringPeriod = 15;

        //define Lamp metered consumption
        double[] valuesLamp = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesLamp) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            lamp.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes),"C"));
        }

        Calendar startDate = new GregorianCalendar(2018, 11, 31);
        Calendar endDate = new GregorianCalendar(2019, 0, 2);
        double energyConsumption = lamp.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63, energyConsumption, 0.01);
    }

    @Test
    void setNominalPower() {
        DeviceType dt = new LampType();
        Device lamp = dt.createDevice("Lamp", 15);

        lamp.setNominalPower(15);
        double expected = 15.0;
        double result = lamp.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void setTimeAndGetEnergyConsumption() {
        DeviceType dt = new LampType();
        Device d = dt.createDevice("Lamp", 15);
        Lamp lamp = (Lamp) d;

        lamp.setTime(2);
        double expected = 30;
        double result = lamp.getEstimatedEnergyConsumption();

        assertEquals(expected,result);
    }
}