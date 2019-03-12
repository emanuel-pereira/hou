package smarthome.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StoveTest {

    @Test
    public void setDeviceName() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("", specs, 2500);
        stove.setDeviceName("kitchen Stove");

        String expected = "kitchen Stove";
        String result = stove.getDeviceName();

        assertEquals(expected, result);
    }


    @Test
    public void setNominalPower() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 0);
        stove.setNominalPower(2500);
        double expected = 2500.0;
        double result = stove.getNominalPower();

        assertEquals(expected, result);
    }


    @Test
    public void getDeviceSpecs() {

        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);
        DeviceSpecs stoveSpecs = stove.getDeviceSpecs();

        assertEquals(specs, stoveSpecs);
    }

    @Test
    public void getDeviceType() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);

        String deviceType = stove.getDeviceType();
        String expected = "Stove";

        assertEquals(expected, deviceType);


    }

    @Test
    public void getNominalPower() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);

        double expected = 2500.0;
        double result = stove.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    public void isActive() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);

        assertTrue(stove.isActive());
    }


    @Test
    public void getActivityLog() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);

        ReadingList stoveActivityLog = stove.getActivityLog();
        ReadingList expectedActivityLog = new ReadingList();

        assertEquals(expectedActivityLog.size(), stoveActivityLog.size());
    }


    @Test
    public void setAttributeValue() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);
        stove.setAttributeValue("capacity", 20.0);

        Double expected = 20.0;
        Double result = specs.getAttributeValue("capacity");

        assertEquals(expected, result);

    }

    @Test
    public void deactivateDevice() {

        StoveSpecs specs = new StoveSpecs("Stove");

        Stove stove = new Stove("kitchen stove", specs, 2500);

        assertTrue(stove.isActive());
        assertTrue(stove.deactivateDevice());
        assertFalse(stove.isActive());

    }

    @Test
    public void getEnergyConsumption() {

        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);
        Calendar startHour = new GregorianCalendar();
        Calendar endHour = new GregorianCalendar();
        double energyConsumption = stove.getEnergyConsumption(startHour, endHour);

        assertEquals(0.0, energyConsumption, 0.001);
    }

    @Test
    public void getEnergyConsumption1() {


        Reading r1 = new Reading(0.600, new GregorianCalendar(2018, 3, 3, 14, 39));
        Reading r2 = new Reading(0.400, new GregorianCalendar(2017, 3, 6, 10, 17));
        Reading r3 = new Reading(0.800, new GregorianCalendar(2018, 2, 7, 11, 23));
        Reading r4 = new Reading(0.600, new GregorianCalendar(2017, 1, 8, 19, 33));
        Reading r5 = new Reading(0.400, new GregorianCalendar(2018, 1, 5, 8, 31));

        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);

        ReadingList activityLog = stove.getActivityLog();

        activityLog.addReading(r1);
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);

        Calendar startDate = new GregorianCalendar(2018, 1, 1, 12, 20);
        Calendar endDate = new GregorianCalendar(2018, 12, 31, 12, 30);

        Double expected = 1.80;
        Double result = stove.getEnergyConsumption(startDate, endDate);

        assertEquals(expected, result);

    }

    @Test
    public void createProgram() {

        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);

        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);

        Program fast = stove.createProgram("Fast", 3000);
        stove.addProgramToList(fast);

        List<Program> expected = Arrays.asList(eco, fast);
        List<Program> result = stove.getProgramList();

        assertEquals(expected, result);

    }


    @Test
    @DisplayName("Add program to list with success")
    public void addNewProgram() {
        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);

        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);
        Program full = stove.createProgram("Full", 3000);

        Assertions.assertTrue(stove.addProgramToList(full));
    }

    @Test
    @DisplayName("Add same program to list")
    public void addSameProgram() {
        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);
        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);

        assertFalse(stove.addProgramToList(eco));
    }

    @Test
    @DisplayName("Add program with the same name to the list")
    public void addProgramToList() {

        StoveSpecs specs = new StoveSpecs("Stove");
        Stove stove = new Stove("kitchen stove", specs, 2500);
        Program eco = stove.createProgram("Eco", 1500);
        stove.addProgramToList(eco);
        Program full = stove.createProgram("Eco", 1500);

        assertFalse(stove.addProgramToList(full));
    }

}