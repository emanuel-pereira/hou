package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class KettleTest {

    @Test
    void setDeviceName() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("", specs, 1500);
        kettle.setDeviceName("super kettle");

        String expected = "super kettle";
        String result = kettle.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);
        specs.setAttributeValue("Capacity", 12.0);

        Double expected = 12.0;
        Double result = specs.getAttributeValue("Capacity");

        assertEquals(expected, result);
    }

    @Test
    void deactivateDevice() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        assertTrue(kettle.isActive());
        assertTrue(kettle.deactivateDevice());
        assertFalse(kettle.isActive());
    }

    @Test
    void getDeviceType() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);
        String deviceType = kettle.getDeviceType();

        String expected = "Kettle";

        assertEquals(expected, deviceType);
    }

    @Test
    void getDeviceSpecs() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);
        DeviceSpecs kettleSpecs = kettle.getDeviceSpecs();

        assertEquals(specs, kettleSpecs);
    }

    @Test
    void getNominalPower() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        double expected = 1500.0;
        double result = kettle.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        assertTrue(kettle.isActive());
    }

    @Test
    void getActivityLog() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        ReadingList kettleActivityLog = kettle.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), kettleActivityLog.size());
    }

    @Test
    void getEnergyConsumption() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = kettle.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0 ,energyConsumption, 0.001);
    }

    @Test
    void getEnergyConsumption2() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs, 1500);

        ReadingList kettleActivityLog = kettle.getActivityLog();

        int year = 2018;
        int month = 11;
        int day = 31;
        int hour = 0;
        int minutes = 0;
        int meteringPeriod = 15;

        //define kettle metered consumption
        double[] valuesKettle = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesKettle) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            kettle.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }

        Calendar startDate = new GregorianCalendar(2018,11,31);
        Calendar endDate = new GregorianCalendar(2019,0,2);
        double energyConsumption = kettle.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63 ,energyConsumption, 0.001);
    }

    @Test
    void setNominalPower() {
        KettleSpecs specs = new KettleSpecs("Kettle");

        Kettle kettle = new Kettle("super kettle", specs,0);
        kettle.setNominalPower(1500);
        double expected = 1500.0;
        double result = kettle.getNominalPower();

        assertEquals(expected, result);
    }
}