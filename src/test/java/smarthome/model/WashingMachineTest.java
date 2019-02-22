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
    void setAttributeValueTest() {
        WashingMachine wm = new WashingMachine(4);
        String Capacity = "Washing Machine Capacity";
        wm.setAttributeValue(Capacity,"20");
        assertEquals(20,wm.getCapacity());
    }


        @Test
    void setAndGetTypeTest() {
        WashingMachine wm = new WashingMachine();
        DeviceType deviceType = new DeviceType("WashingMachine");
        wm.setType(deviceType);
        String result = wm.getDeviceType().getDeviceTypeName();
        assertEquals("WashingMachine", result);

    }
    @Test
    void getEnergyConsumptionTest(){
        WashingMachine wm = new WashingMachine();
        double result = wm.getEnergyConsumption();
        assertEquals(0,result);
    }
}