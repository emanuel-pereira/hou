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
    void checkIfPublicPowerGridTestFalse() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        boolean expectedResult = false;
        boolean result;
        result = ps1.CheckIfPublicPowerGrid();
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfPublicPowerGridTestFalseNotEquals() {
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        boolean expectedResult = true;
        boolean result;
        result = ps1.CheckIfPublicPowerGrid();
        assertNotEquals(expectedResult, result);
    }

    @Test
    void checkIfPublicPowerGridTestTrue() {
        PowerSource ps1 = new PowerSource("porto4150175","public",250,14);
        boolean expectedResult = true;
        boolean result;
        result = ps1.CheckIfPublicPowerGrid();
        assertEquals(expectedResult, result);
    }

    @Test
    void checkIfPublicPowerGridTestTrueNotEquals() {
        PowerSource ps1 = new PowerSource("porto4150175","public",250,14);
        boolean expectedResult = false;
        boolean result;
        result = ps1.CheckIfPublicPowerGrid();
        assertNotEquals(expectedResult, result);
    }
}