package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Set new House Grid with incorrect Power Value")
    void setNegativeAndGetContractedMaximumPower() {
        HouseGrid hg = new HouseGrid(-35);
        Double result = hg.getContractedMaximumPower();
        assertEquals(Double.NaN, result, 0.1);
    }

    @Test
    @DisplayName("Set new House Grid with name ID")
    void setContractedMaximumPowerAndGridID() {
        HouseGrid hg = new HouseGrid(2350, "main grid");
        Double result = hg.getContractedMaximumPower();
        assertEquals("main grid", hg.getGridID());
    }
}