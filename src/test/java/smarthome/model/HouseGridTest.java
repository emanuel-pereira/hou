package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HouseGridTest {

    @Test
    @DisplayName("Set new House Grid with correct Power Value")
    void setAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid(2350);
        Double result = hg.getContractedMaximumPower();
        assertEquals(2350, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setUpdateAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid(0);
        hg.setContractedMaximumPower(2351.3);
        Double result = hg.getContractedMaximumPower();
        assertEquals(2351.3, result, 0.1);
    }

    @Test
    @DisplayName("Create new Power Source")
    void newPowerSourceTest() {
        HouseGrid newps = new HouseGrid();
        PowerSource ps1 = newps.newPowerSource("panel002", "solar", 250, 14);
        String expectedResult = "panel002";
        String result;
        result = ps1.getName();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Add a Power Source to the Power Source List")
    void addPSTest() {
        HouseGrid houseGrid = new HouseGrid();
        PowerSource ps1 = houseGrid.newPowerSource("panel002","solar",250,14);
        houseGrid.addPS(ps1);
        PowerSource ps2 = houseGrid.newPowerSource("turbine003","eolic",300,20);
        houseGrid.addPS(ps2);
        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = houseGrid.getPSList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Check that a repeated Power Source can not be added")
    void addPSTestNoAddSamePowerSource() {
        HouseGrid houseGrid = new HouseGrid();
        PowerSource ps1 = houseGrid.newPowerSource("panel002","solar",250,14);
        houseGrid.addPS(ps1);
        boolean expectedResult = false;
        boolean result;
        result = houseGrid.addPS(ps1);
        assertEquals(expectedResult, result);
    }


    @Test
    @DisplayName("Check the if both elements were added with a NotEquals")
    void addPSTestNotEquals() {
        HouseGrid houseGrid = new HouseGrid();
        PowerSource ps1 = houseGrid.newPowerSource("panel002","solar",250,14);
        houseGrid.addPS(ps1);
        PowerSource ps2 = houseGrid.newPowerSource("turbine003","wind",300,20);
        houseGrid.addPS(ps2);
        List<PowerSource> expectedResult = Arrays.asList(ps1);
        List<PowerSource> result = houseGrid.getPSList();
        assertNotEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Get a Power Source")
    void getPowerSourceTest (){
        HouseGrid houseGrid = new HouseGrid();
        PowerSource expectedResult = houseGrid.newPowerSource("panel002","solar",250,14);
        PowerSource result = houseGrid.getPowerSource();
        assertEquals(expectedResult, result);
    }


    @Test
    @DisplayName("See the list of Power Sources that were added")
    void getPSListTest() {
        HouseGrid houseGrid = new HouseGrid();
        PowerSource ps1 = houseGrid.newPowerSource("panel002","solar",250,14);
        houseGrid.addPS(ps1);
        PowerSource ps2 = houseGrid.newPowerSource("turbine003","wind",300,20);
        houseGrid.addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = houseGrid.getPSList();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Set new House Grid with zero Power Value")
    void setZeroContractedMaximumPower() {
        HouseGrid hg = new HouseGrid(0);
        hg.setContractedMaximumPower(0.0);
        Double result = hg.getContractedMaximumPower();
        assertEquals(Double.NaN, result, 0.1);
    }
    @Test
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setNegativeAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid();

        hg.setContractedMaximumPower(-35.0);

        Double result = hg.getContractedMaximumPower();
        assertEquals(Double.NaN, result, 0.1);
    }


    @Test
    @DisplayName("Set new House Grid with name ID")
    void setContractedMaximumPowerAndGridID() {
        HouseGrid hg = new HouseGrid(2350, "main grid");
        assertEquals("main grid", hg.getGridID());
    }

    @Test
    @DisplayName("Get the ID of a House Grid")
    void getGridIDTest () {
        HouseGrid hg = new HouseGrid(300,"grid002");

        String expectedResult = "grid002";
        String result = hg.getGridID();
        assertEquals(expectedResult,result);
    }
}