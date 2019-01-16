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
        HouseGrid hg = new HouseGrid("grid01",0);
        hg.setContractedMaximumPower(2351.3);
        Double result = hg.getContractedMaximumPower();
        assertEquals(2351.3, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with zero Power Value")
    void setZeroContractedMaximumPower() {
        HouseGrid hg = new HouseGrid("grid01",0);
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
        HouseGrid hg = new HouseGrid( "main grid",2350);
        assertEquals("main grid", hg.getGridID());
    }

    @Test
    @DisplayName("Get the ID of a House Grid")
    void getGridIDTest () {
        HouseGrid hg = new HouseGrid("grid002",300);

        String expectedResult = "grid002";
        String result = hg.getGridID();
        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Get the list of Previously added Power Sources")
    void getPSListTest () {
        HouseGrid houseGrid = new HouseGrid();
        PowerSource ps1 = new PowerSource("panel002","solar",250,14);
        PowerSource ps2 = new PowerSource("panel003","solar",250,14);
        houseGrid.getPSListInHG().addPS(ps1);
        houseGrid.getPSListInHG().addPS(ps2);

        List<PowerSource> expectedResult = Arrays.asList(ps1,ps2);
        List<PowerSource> result = houseGrid.getPSListInHG().getPSList();

        assertEquals(expectedResult,result);

    }
    
}

