package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.Tv;
import smarthome.model.devices.TvSpecs;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TvTest {

    @Test
    void setDeviceName() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("", tvSpecs, 15);
        tv.setDeviceName("Smart Tv");

        String expected = "Smart Tv";
        String result = tv.getName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        tvSpecs.setAttributeValue("Capacity", 20);

        Double expected = 20.0;
        Double result = tvSpecs.getAttributeValue("Capacity");

        assertEquals(expected, result);
    }


    @Test
    void getDeviceType() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 15);
        String deviceType = tv.getDeviceType();

        String expected = "Tv";

        assertEquals(expected, deviceType);
    }

    @Test
    void getDeviceSpecs() {
        TvSpecs specs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", specs, 15);
        DeviceSpecs tvSpecs = tv.getDeviceSpecs();

        assertEquals(specs, tvSpecs);
    }

    @Test
    void getNominalPower() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("super Tv", tvSpecs, 15);

        Double expected = 15.0;
        Double result = tv.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 15);

        assertTrue(tv.isActive());
    }

    @Test
    void getActivityLog() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 15);

        ReadingList tvActivityLog = tv.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), tvActivityLog.size());
    }

    @Test
    @DisplayName("getEnergyConsumption execution with no Readings")
    void getEnergyConsumption() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 15);

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = tv.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    @DisplayName("getEnergyConsumption execution with valid Readings")
    void getEnergyConsumption2() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 15);

        int year = 2018;
        int month = 11;
        int day = 31;
        int hour = 0;
        int minutes = 0;
        int meteringPeriod = 15;

        //define Tv metered consumption
        double[] valuesTv = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesTv) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            tv.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }

        Calendar startDate = new GregorianCalendar(2018, Calendar.DECEMBER, 31);
        Calendar endDate = new GregorianCalendar(2019, Calendar.JANUARY, 2);
        double energyConsumption = tv.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63, energyConsumption, 0.001);
    }

    @Test
    void getEstimatedEnergyConsumption(){
        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("Smart Tv", tvSpecs,230);

        tv.setTime(1.5);

        double result = tv.getEstimatedEnergyConsumption();

        double expected = 345;

        assertEquals(expected, result);


    }

    @Test
    void setNominalPower() {
        TvSpecs tvSpecs = new TvSpecs("Tv");

        Tv tv = new Tv("Smart Tv", tvSpecs, 0);
        tv.setNominalPower(15);
        double expected = 15.0;
        double result = tv.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("LG TV", tvSpecs, 2500);

        assertTrue(tv.deactivateDevice());

        assertFalse(tv.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("LG TV", tvSpecs, 2500);

        assertTrue(tv.deactivateDevice());

        assertFalse(tv.deactivateDevice());
    }
}