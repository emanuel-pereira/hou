package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FridgeTest {

    @Test
    public void getAndSetAllAttributes() {
        Fridge fr = new Fridge(DeviceType.FRIDGE,2, 3, 6);
        fr.setFreezerCapacity(6);
        int result = fr.getFreezerCapacity();
        assertEquals(6, result);

        fr.setRefrigeratorCapacity(9);
        int result1 = fr.getRefrigeratorCapacity();
        assertEquals(9, result1);
    }
    @Test
    public void showDeviceSpecsListAttributesInString() {
        Fridge f = new Fridge(DeviceType.FRIDGE,2, 3, 6);
        List<String> result = f.getDeviceAttributesInString();
        List<String> expected = Arrays.asList("3 - Device Type : " + f.getType().getTypeString(),"4 - Freezer Capacity : "+f.getFreezerCapacity(),"5 - Refrigerator Capacity : " +f.getRefrigeratorCapacity());
        assertEquals(expected,result);
    }
    @Test
    void setAttributeValueTest() {
        Fridge f = new Fridge(DeviceType.FRIDGE,2, 3, 6);
        String freezerCapacity = "4 - Freezer Capacity : " + f.getFreezerCapacity();
        String refrigeratorCapacity = "5 - Refrigerator Capacity : " + f.getRefrigeratorCapacity();
        f.setAttributeValue(freezerCapacity,"20");
        assertEquals(20,f.getFreezerCapacity());
        f.setAttributeValue(refrigeratorCapacity,"5");
        assertEquals(5,f.getRefrigeratorCapacity());


    }

    @Test
    void getEnergyConsumption() {
        DeviceSpecs fridgeSpecs= new Fridge(DeviceType.FRIDGE,25,50,1500);
        double expected=4.1;
        double result=fridgeSpecs.getEnergyConsumption();
        assertEquals(expected,result,0.1);

    }


}
