package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;
import smarthome.model.DeviceType;
import smarthome.model.Reading;
import smarthome.model.ReadingList;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class KettleTest {

    @Test
    void setDeviceName() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle", 100);
        Kettle kettle = (Kettle) d;
        kettle.setDeviceName("super kettle");

        String expected = "super kettle";
        String result = kettle.getName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle", 100);
        Kettle kettle = (Kettle) d;

        kettle.getDeviceSpecs().setAttributeValue("Capacity",12);
        Double expected = 12.0;
        Double result = kettle.getDeviceSpecs().getAttributeValue("Capacity");

        assertEquals(expected, result);
    }

    @Test
    void deactivateDevice() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;

        assertTrue(kettle.isActive());
        assertTrue(kettle.deactivateDevice());
        assertFalse(kettle.isActive());
    }

    @Test
    void getDeviceType() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;
        String deviceType = kettle.getDeviceType();

        String expected = "Kettle";

        assertEquals(expected, deviceType);
    }

/*    @Test
    void getDeviceSpecs() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;
        DeviceSpecs kettleSpecs = kettle.getDeviceSpecs();

        assertEquals(specs, kettleSpecs);
    }*/

    @Test
    void getNominalPower() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",1500);
        Kettle kettle = (Kettle) d;

        double expected = 1500.0;
        double result = kettle.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",1500);
        Kettle kettle = (Kettle) d;

        assertTrue(kettle.isActive());
    }

    @Test
    void getActivityLog() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;

        ReadingList kettleActivityLog = kettle.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), kettleActivityLog.size());
    }

    @Test
    @DisplayName("getEnergyConsumption Test with no Readings and no null time interval")
    void getEnergyConsumption() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = kettle.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    @DisplayName("getEnergyConsumption Test with Readings and a time interval")
    void getEnergyConsumption2() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;

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
            kettleActivityLog.addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes),"C"));
        }

        Calendar startDate = new GregorianCalendar(2018, 11, 31);
        Calendar endDate = new GregorianCalendar(2019, 0, 2);
        double energyConsumption = kettle.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63, energyConsumption, 0.001);
    }

    @Test
    void setNominalPower() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;
        kettle.setNominalPower(1500);
        double expected = 1500.0;
        double result = kettle.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new KettleType();
        Device d = dt.createDevice("kettle",100);
        Kettle kettle = (Kettle) d;
        double expected = 0.0;
        double result = kettle.getEstimatedEnergyConsumption();

        assertEquals(expected, result);
    }
}