package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DishwasherTest {

    @Test
    @DisplayName("Set and get correct device name")
    void setDeviceName() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;


        assertEquals("Whirlpool Washer", dishwasher.getDeviceName());

        dishwasher.setDeviceName("Whirlpool");

        String expected = "Whirlpool";
        String result = dishwasher.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set empty device name and get first name")
    void setEmptyDeviceName() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        assertEquals("Whirlpool Washer", dishwasher.getDeviceName());

        dishwasher.setDeviceName(" ");

        String expected = "Whirlpool Washer";
        String result = dishwasher.getDeviceName();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get incorrect device name")
    void getIncorrectDeviceName() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        assertEquals("Whirlpool Washer", dishwasher.getDeviceName());

        dishwasher.setDeviceName("Whirlpool");

        String expected = "Whirlpol";
        String result = dishwasher.getDeviceName();

        assertNotEquals(expected, result);
    }

    @Test
    @DisplayName("Get device specs and check type")
    void getDeviceSpecs() {

        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("I have a clean, than one day...", 100);

        String[] attributeNames = {"Capacity"};
        String[] attributeUnits = {"Dish Sets"};

        DeviceSpecs foo = new GenericSpecs("Dishwasher",attributeNames,attributeUnits);
        DeviceSpecs bar = d.getDeviceSpecs();

        String expected = foo.getDeviceType();
        String result = bar.getDeviceType();

        assertEquals(expected, result);

    }


   @Test
    @DisplayName("Get correct device type")
    void getDeviceType() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        String expected = "Dishwasher";
        String result = dishwasher.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Change the device type but return correct device type")
    void changeDeviceType() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        String expected = "Dishwasher";
        String result = dishwasher.getDeviceType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set and get correct nominal power")
    void getNominalPower() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;

        assertEquals(200, dishwasher.getNominalPower());

        dishwasher.setNominalPower(210);

        double expected = 210;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set negative nominal power and get first nominal power")
    void getInvalidNominalPower() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;

        assertEquals(200, dishwasher.getNominalPower());

        dishwasher.setNominalPower(-210);

        double expected = 200;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Set nominal power to zero and get first nominal power")
    void getZeroNominalPower() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer", 200);
        Dishwasher dishwasher = (Dishwasher) d;

        assertEquals(200, dishwasher.getNominalPower());

        dishwasher.setNominalPower(0);

        double expected = 0;
        double result = dishwasher.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is correctly active")
    void isActive() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        assertTrue(dishwasher.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is incorrectly active")
    void isIncorrectlyActive() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        boolean result = dishwasher.isActive();

        assertNotEquals(false, result);
    }

    @Test
    @DisplayName("Confirm correct activity log size")
    void getActivityLog() {
        Reading r1 = new Reading(15, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0),"C");
        Reading r2 = new Reading(18, new GregorianCalendar(2018, Calendar.AUGUST, 26, 13, 0),"C");
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;
        ReadingList activityLog = dishwasher.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);

        int expected = 2;
        int result = activityLog.size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Confirm if a device is deactivate and if is not active")
    void deactivateDevice() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        assertTrue(dishwasher.deactivateDevice());

        assertFalse(dishwasher.isActive());
    }

    @Test
    @DisplayName("Confirm if a device is not deactivate twice")
    void deactivateDeviceTwice() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",100);
        Dishwasher dishwasher = (Dishwasher) d;

        assertTrue(dishwasher.deactivateDevice());

        assertFalse(dishwasher.deactivateDevice());
    }

    @Test
    @DisplayName("Get correct energy consumption")
    void getEnergyConsumption() {
        Reading r1 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 0),"C");
        Reading r2 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15),"C");
        Reading r3 = new Reading(0.000, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r4 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 30),"C");
        Reading r5 = new Reading(0.200, new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45),"C");
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        ReadingList activityLog = dishwasher.getActivityLog();
        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        Calendar startDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 15);
        Calendar endDate = new GregorianCalendar(2018, Calendar.AUGUST, 26, 12, 45);

        double expected = 0.200;
        double result = dishwasher.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result, 0.001);
    }

    @Test
    @DisplayName("Create, add and get correct program list")
    void createProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Full", 200);
        dishwasher.addProgramToList(full);

        List<Program> expected = Arrays.asList(eco, full);
        List<Program> result = dishwasher.getProgramList();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add program to list with success")
    void addNewProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Full", 200);

        assertTrue(dishwasher.addProgramToList(full));
    }

    @Test
    @DisplayName("Add same program to list")
    void addSameProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);

        assertFalse(dishwasher.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    void addProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        Program eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        Program full = dishwasher.createProgram("Eco", 200);

        assertFalse(dishwasher.addProgramToList(full));
    }

    @Test
    @DisplayName("Get null if no metered program is set")
    void getMeteredNullProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        ProgramWithTimer eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);

        assertNull(dishwasher.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly set a metered program")
    void setMeteredProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        ProgramWithTimer eco = dishwasher.createProgram("Eco", 50);
        dishwasher.addProgramToList(eco);
        ProgramWithTimer fast = dishwasher.createProgram("Fast", 200);
        dishwasher.addProgramToList(fast);
        dishwasher.setMeteredProgram("Eco");

        Program result = dishwasher.getMeteredProgram();

        assertEquals(eco, result);

        dishwasher.setMeteredProgram("Fast");

        assertEquals(fast, dishwasher.getMeteredProgram());
    }

    @Test
    @DisplayName("Correctly get the estimated energy consumption")
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        ProgramWithTimer slow = dishwasher.createProgram("Slow", 50);
        dishwasher.addProgramToList(slow);
        ProgramWithTimer fast = dishwasher.createProgram("Fast", 200);
        dishwasher.addProgramToList(fast);
        slow.setDuration(2);
        dishwasher.setMeteredProgram("Slow");

        double expected = 50;
        double result = dishwasher.getEnergyConsumption();

        assertEquals(expected, result);
    }

    @Test
    void getEstimatedEnergyConsumptionNoProgram() {
        DeviceType dt = new DishwasherType();
        Device d = dt.createDevice("Whirlpool Washer",200);
        Dishwasher dishwasher = (Dishwasher) d;
        ProgramWithTimer slow = dishwasher.createProgram("Slow", 50);
        dishwasher.addProgramToList(slow);
        Program fast = dishwasher.createProgram("Fast", 200);

        dishwasher.addProgramToList(fast);

        double expected = 0;
        double result = dishwasher.getEnergyConsumption();

        assertEquals(expected, result);
    }
}
