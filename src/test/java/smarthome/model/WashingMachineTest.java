package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashingMachineTest {

    @Test
    void setAndGetCapacityValueTest() {
        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE,80);
        washingMachine.setCapacity(90);
        int result = washingMachine.getCapacity();
        assertEquals(90,result);
    }

    @Test
    void setAttributeValueTest() {
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE,4);
        String Capacity = "4 - Washing Machine Capacity : " + wm.getCapacity();
        wm.setAttributeValue(Capacity,"20");
        assertEquals(20,wm.getCapacity());
    }

    @Test
    void getType() {
        WashingMachine wm = new WashingMachine(DeviceType.WASHING_MACHINE,4);
        DeviceType result=wm.getType();
        assertEquals(DeviceType.WASHING_MACHINE,result);}

}