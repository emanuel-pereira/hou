package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest {

    @Test
    @DisplayName("Ensure getType from WashingMachine instance returns correct type")
    void getType() {
        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, 80);

        String expected = "Washing Machine";
        String result = washingMachine.getType();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getTypeFromIndex from WashingMachine instance returns correct type")
    void getTypeFromIndex() {
        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, 80);

        String expected = "Washing Machine";
        String result = washingMachine.getTypeFromIndex(1);

        assertEquals(expected, result);
    }

    @Test
    void setAndGetCapacityValueTest() {
        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, 80);
        washingMachine.setCapacity(90);
        int result = washingMachine.getCapacity();
        assertEquals(90,result);
    }

    @Test
    void showDeviceSpecsListAttributesInString() {
        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, 80);
        String result = washingMachine.showDeviceSpecsListAttributesInString();
        assertEquals("4 - Capacity : " +washingMachine.getCapacity(),result);
    }


}