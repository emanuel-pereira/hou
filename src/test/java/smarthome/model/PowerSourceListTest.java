package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PowerSourceListTest {

    @Test
    @DisplayName("Create new Power Source")
    void newPowerSourceTest() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002", "solar", 250, 14);
        String expectedResult = "panel002";
        String result;
        result = ps1.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Add a Power Source to the Power Source List")
    void addPSTest() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        PowerSource ps2 = pslist.newPowerSource("turbine003","eolic",300,20);
        pslist.addPS(ps2);
        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = pslist.getPSList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Add a Power Source to the Power Source List")
    void addPSTestSize() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        PowerSource ps2 = pslist.newPowerSource("turbine003","eolic",300,20);
        pslist.addPS(ps2);
        int expectedResult = 2;
        int result = pslist.getPSListSize();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Add a Power Source to the Power Source List")
    void addPSTestSizeZero() {
        PowerSourceList pslist = new PowerSourceList();
        /*PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        PowerSource ps2 = pslist.newPowerSource("turbine003","eolic",300,20);
        pslist.addPS(ps2);*/
        int expectedResult = 0;
        int result = pslist.getPSListSize();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check that a repeated Power Source can not be added")
    void addPSTestNoAddSamePowerSource() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        boolean expectedResult = false;
        boolean result;
        result = pslist.addPS(ps1);
        assertEquals(expectedResult, result);
    }


    @Test
    @DisplayName("Check the if both elements were added with a NotEquals")
    void addPSTestNotEquals() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        PowerSource ps2 = pslist.newPowerSource("turbine003","wind",300,20);
        pslist.addPS(ps2);
        List<PowerSource> expectedResult = Arrays.asList(ps1);
        List<PowerSource> result = pslist.getPSList();
        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("See the list of Power Sources that were added")
    void getPSListTest() {
        PowerSourceList pslist = new PowerSourceList();
        PowerSource ps1 = pslist.newPowerSource("panel002","solar",250,14);
        pslist.addPS(ps1);
        PowerSource ps2 = pslist.newPowerSource("turbine003","wind",300,20);
        pslist.addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = pslist.getPSList();
        assertEquals(expectedResult, result);
    }
}