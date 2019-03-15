package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {

    @Test
    void setDeviceName() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("", specs, 1500);
        dishwasher.setDeviceName("LG Washer");

        String expected = "LG Washer";
        String result = dishwasher.getName();

        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);
        specs.setAttributeValue("Capacity", 10);

        Double expected = 10.0;
        Double result = specs.getAttributeValue("Capacity");

        assertEquals(expected, result);
    }

    @Test
    void deactivateDevice() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        assertTrue(dishwasher.isActive());
        assertTrue(dishwasher.deactivateDevice());
        assertFalse(dishwasher.isActive());
    }

    @Test
    void getDeviceType() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);
        String deviceType = dishwasher.getDeviceType();

        String expected = "Dishwasher";

        assertEquals(expected, deviceType);
    }

    @Test
    void getDeviceSpecs() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);
        DeviceSpecs dishwasherSpecs = dishwasher.getDeviceSpecs();

        assertEquals(specs, dishwasherSpecs);
    }

    @Test
    void getNominalPower() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        double expected = 1500.0;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    void isActive() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        assertTrue(dishwasher.isActive());
    }

    @Test
    void getActivityLog() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        ReadingList dishwasherActivityLog = dishwasher.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), dishwasherActivityLog.size());
    }

    @Test
    @DisplayName("getEnergyConsumption Test with no Readings and no null time interval")
    void getEnergyConsumption() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        Calendar startDate = new GregorianCalendar();
        Calendar endDate = new GregorianCalendar();
        double energyConsumption = dishwasher.getEnergyConsumption(startDate, endDate);

        assertEquals(0.0 ,energyConsumption, 0.001);
    }

    @Test
    @DisplayName("getEnergyConsumption Test with Readings and a time interval")
    void getEnergyConsumption2() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs, 1500);

        ReadingList dishwasherActivityLog = dishwasher.getActivityLog();

        int year = 2018;
        int month = 11;
        int day = 31;
        int hour = 0;
        int minutes = 0;
        int meteringPeriod = 15;

        //define Dishwasher metered consumption
        double[] valuesDishwasher = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesDishwasher) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            dishwasherActivityLog.addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }

        Calendar startDate = new GregorianCalendar(2018,11,31);
        Calendar endDate = new GregorianCalendar(2019,0,2);
        double energyConsumption = dishwasher.getEnergyConsumption(startDate, endDate);

        assertEquals(5.63 ,energyConsumption, 0.001);
    }

    @Test
    void setNominalPower() {
        DishwasherSpecs specs = new DishwasherSpecs("Dishwasher");

        Dishwasher dishwasher = new Dishwasher("LG Washer", specs,0);
        dishwasher.setNominalPower(1500);
        double expected = 1500.0;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");
        Dishwasher dishwasher = new Dishwasher ("LG Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram ("Eco", 50);
        dishwasher.addProgramToList (eco);
        Program full = dishwasher.createProgram ("Full", 200);
        dishwasher.addProgramToList (full);

        List<Program> expected = Arrays.asList (eco,full);
        List<Program> result = dishwasher.getProgramList ();

        assertEquals (expected,result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");
        Dishwasher dishwasher = new Dishwasher ("LG Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram ("Eco", 50);
        dishwasher.addProgramToList (eco);
        Program full = dishwasher.createProgram ("Full", 200);

        assertTrue (dishwasher.addProgramToList (full));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");
        Dishwasher dishwasher = new Dishwasher ("LG Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram ("Eco", 50);
        dishwasher.addProgramToList (eco);

        assertFalse (dishwasher.addProgramToList (eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DishwasherSpecs dishwasherSpecs = new DishwasherSpecs ("Dishwasher");
        Dishwasher dishwasher = new Dishwasher ("LG Washer", dishwasherSpecs, 200);
        Program eco = dishwasher.createProgram ("Eco", 50);
        dishwasher.addProgramToList (eco);
        Program full = dishwasher.createProgram ("Eco", 200);

        assertFalse (dishwasher.addProgramToList (full));
    }
}