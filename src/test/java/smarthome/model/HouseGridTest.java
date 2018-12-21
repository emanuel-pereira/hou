package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseGridTest {

    @Test
    void getmContractedMaximumPower() {
        HouseGrid hg = new HouseGrid(2350);
        hg.setmContractedMaximumPower(2351.3);
        Double result = hg.getmContractedMaximumPower();
        assertEquals(2351.3, result, 0.1);
    }
}