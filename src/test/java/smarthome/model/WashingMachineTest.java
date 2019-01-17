package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineTest {

    @Test
    void setAndGetCapacityValueTest() {
        WashingMachine washingMachine = new WashingMachine(80);
        washingMachine.setCapacity(90);
        int result = washingMachine.getCapacity();
        assertEquals(90,result);
    }

    @Test
    void showDeviceSpecsListAttributesInString() {
        WashingMachine washingMachine = new WashingMachine(80);
        String result = washingMachine.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Capacity : " +washingMachine.getCapacity(),result);
    }


}