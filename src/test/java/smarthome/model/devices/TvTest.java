package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class TvTest {

    @Test
    void setDeviceName() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("boo", 100);

        tv.setDeviceName("Smart Tv");

        String expected = "Smart Tv";
        String result = tv.getName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Tv", 20);

        tv.getDeviceSpecs().setAttributeValue("Capacity", 20);

        Double expected = NaN;
        Double result = tv.getDeviceSpecs().getAttributeValue("Capacity");

        assertEquals(expected, result);
    }


    @Test
    void getDeviceType() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv", 15);
        String deviceType = tv.getDeviceType();

        String expected = "Tv";

        assertEquals(expected, deviceType);
    }

    /*@Test
    void getDeviceSpecs() {

        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv",15);
        DeviceSpecs tvSpecs = tv.getDeviceSpecs();

        assertEquals(specs, tvSpecs);
    }*/

    @Test
    void getNominalPower() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv", 15);

        Double expected = 15.0;
        Double result = tv.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv", 15);

        assertTrue(tv.isActive());
    }

    @Test
    void getActivityLog() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv", 15);

        ReadingList tvActivityLog = tv.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), tvActivityLog.size());
    }

    @Test
    @DisplayName("getEnergyConsumption execution with no Readings")
    void getEnergyConsumption() {
        DeviceType dt = new TvType();
        Device tv = dt.createDevice("Smart Tv", 15);
        Metered tv1 = (Metered) tv;

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();

        double energyConsumption = tv1.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    @DisplayName("getEnergyConsumption execution with valid Readings")
    void getEnergyConsumption2() {
        DeviceType dt = new TvType();
        Device d = dt.createDevice("Smart Tv", 15);
        Tv tv = (Tv) d;

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
            tv.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes),"C"));
        }

        Calendar startDate = new GregorianCalendar(2018, Calendar.DECEMBER, 31);
        Calendar endDate = new GregorianCalendar(2019, Calendar.JANUARY, 2);
        double energyConsumption = tv.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63, energyConsumption, 0.001);
    }

    @Test
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new TvType();
        Device d = dt.createDevice("Smart Tv", 345);
        Tv tv = (Tv) d;
        tv.setTime(2);
        double result = tv.getEstimatedEnergyConsumption();

        double expected = 690;

        assertEquals(expected, result);


    }

    @Test
    void setNominalPower() {
        DeviceType dt = new TvType();
        Device d = dt.createDevice("Smart Tv", 15);
        Tv tv = (Tv) d;

        tv.setNominalPower(15);
        double expected = 15.0;
        double result = tv.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DeviceType dt = new TvType();
        Device d = dt.createDevice("Smart Tv", 15);
        Tv tv = (Tv) d;

        assertTrue(tv.deactivateDevice());

        assertFalse(tv.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DeviceType dt = new TvType();
        Device d = dt.createDevice("Smart Tv", 15);
        Tv tv = (Tv) d;

        assertTrue(tv.deactivateDevice());

        assertFalse(tv.deactivateDevice());
    }
}