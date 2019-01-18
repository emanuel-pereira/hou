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
        String Capacity = "4 - Washing Machine Capacity : " + wm.getCapacity();
        wm.setAttributeValue(Capacity,"20");
        assertEquals(20,wm.getCapacity());

    }

    //@Test
   // void showDeviceSpecsListAttributesInString() {
      //  WashingMachine washingMachine = new WashingMachine(80);
        //List<String> result = washingMachine.getDeviceAttributesInString();
       // assertEquals("4 - Capacity : " +washingMachine.getCapacity(),result);

}