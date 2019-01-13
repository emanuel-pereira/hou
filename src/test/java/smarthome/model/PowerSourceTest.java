package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerSourceTest {

    @Test
    void getNameTest() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        String expectedResult = "panel002";
        String result;
        result = ps1.getName();
        assertEquals(expectedResult, result);
    }

    @Test
    void getNameTestFalse() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        String expectedResult = "solar";
        String result;
        result = ps1.getName();
        assertNotEquals(expectedResult, result);
    }

    @Test
    void getTypePSTest() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        String expectedResult = "solar";
        String result;
        result = ps1.getTypePS();
        assertEquals(expectedResult, result);
    }

    @Test
    void getTypePSTestFalse() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        String expectedResult = "wind";
        String result;
        result = ps1.getTypePS();
        assertNotEquals(expectedResult, result);
    }

    @Test
    void getMaxPowerTest() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        double expectedResult = 250;
        double result;
        result = ps1.getMaxPower();
        assertEquals(expectedResult, result);
    }

    @Test
    void getMaxPowerTestFalse() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        double expectedResult = 14;
        double result;
        result = ps1.getMaxPower();
        assertNotEquals(expectedResult, result);
    }

    @Test
    void getStorageCapacityTest() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        double expectedResult = 14;
        double result;
        result = ps1.getStorageCapacity();
        assertEquals(expectedResult, result);
    }

    @Test
    void getStorageCapacityTestFalse() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        double expectedResult = 250;
        double result;
        result = ps1.getStorageCapacity();
        assertNotEquals(expectedResult, result);
    }

    @Test
    void nameISValidTestTrue(){
        PowerSource ps1 = new PowerSource("panel002","solar",
                250,14);
        boolean result = ps1.nameIsValid(ps1.getName());
        assertTrue(result);
    }

    @Test
    void nameISValidTestFalse(){
        PowerSource ps1 = new PowerSource("","solar",
                250,14);
        boolean result = ps1.nameIsValid(ps1.getName());
        assertFalse(result);
    }
}