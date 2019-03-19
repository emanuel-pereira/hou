package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.devices.Tv;
import smarthome.model.devices.TvSpecs;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TvTest {

    @Test
    @DisplayName("Set a name for the Tv and obtain the correct one")
    void getTvDeviceName() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);

        tv.setDeviceName("PremiumTV");

        String result = tv.getName();


        assertEquals("PremiumTV", result);

    }

    @Test
    @DisplayName("Obtain the correct device specs for the Tv")
    void getTvDeviceSpecs() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);

        DeviceSpecs result = tv.getDeviceSpecs();

        assertEquals(tvSpecs, result);

    }

    @Test
    @DisplayName("Obtain the correct device type for the Tv")
    void getTvDeviceType() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);

        String result = tv.getDeviceType();

        assertEquals("Tv", result);
    }

    @Test
    @DisplayName("Edit the nominal power value and obtain the new one")
    void getTvNominalPower() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);

        tv.setNominalPower(245);

        double result = tv.getNominalPower();

        assertEquals(245, result);
    }

    @Test
    @DisplayName("Check if an active Tv is active")
    void tvDeviceIsActive() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);

        assertTrue(tv.isActive());
    }

    @Test
    @DisplayName("Check that a deactivated Tv is not active")
    void tvDeviceIsNotActive() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTv", tvSpecs, 123);


        tv.isActive();
        assertTrue(tv.deactivateDevice());
        assertFalse(tv.isActive());
    }


    @Test
    @DisplayName("Obtain the activity log of a Tv")
    void getTvActivityLog() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTV", tvSpecs, 123);

        Reading r1 = new Reading(234, new GregorianCalendar(2018, Calendar.JANUARY, 1, 12, 0));
        Reading r2 = new Reading(670, new GregorianCalendar(2018, Calendar.JANUARY, 31, 13, 0));

        ReadingList tvActivityLog = tv.getActivityLog();

        tvActivityLog.addReading(r1);
        tvActivityLog.addReading(r2);

        List<Reading> expected = Arrays.asList(r1, r2);
        List<Reading> result = tv.getActivityLog().getReadingsList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Obtain the correct value of a Tv's energy consumption")
    void getTvEnergyConsumption() {

        TvSpecs tvSpecs = new TvSpecs("Tv");
        Tv tv = new Tv("SmartTV", tvSpecs, 123);

        Calendar startDate = new GregorianCalendar(2018, Calendar.JANUARY, 1, 12, 0);
        Calendar endDate = new GregorianCalendar(2018, Calendar.FEBRUARY, 20, 13, 0);

        Reading r1 = new Reading(234, new GregorianCalendar(2018, Calendar.JANUARY, 2, 12, 0));
        Reading r2 = new Reading(670, new GregorianCalendar(2018, Calendar.FEBRUARY, 28, 13, 0));

        ReadingList tvActivityLog = tv.getActivityLog();

        tvActivityLog.addReading(r1);
        tvActivityLog.addReading(r2);

        double energyConsumption = tv.getEnergyConsumption(startDate, endDate);

        assertEquals(234.0, energyConsumption, 0.001);
    }


}