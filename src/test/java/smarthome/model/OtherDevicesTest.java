package smarthome.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherDevicesTest {

    @Test
    void getDeviceAttributesInStringTest() {
        OtherDevices otherDevices = new OtherDevices();
        List<String> result = otherDevices.getAttributesNames();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    void setAttributeValue() {
        OtherDevices otherDevices = new OtherDevices();
        otherDevices.setAttributeValue("A", "2");
        List<String> result = otherDevices.getAttributesNames();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, result);
    }

    @Test
    void setAndGetTypeTest(){
        OtherDevices otherDevices = new OtherDevices();
        DeviceType deviceType = new DeviceType("TV");
        otherDevices.setType(deviceType);
        String result = otherDevices.getDeviceType().getDeviceTypeName();
        assertEquals("TV",result);
    }

    @Test
    void getEnergyConsumptionTest(){
        OtherDevices otherDevices = new OtherDevices();
        double result = otherDevices.getEnergyConsumption();
        assertEquals(0,result);
    }
    @Test
    void showDeviceAttributeNamesAndValuesTest(){
        OtherDevices otherDevices = new OtherDevices();
        String result = otherDevices.showDeviceAttributeNamesAndValues();
        assertEquals("",result);

    }

}
