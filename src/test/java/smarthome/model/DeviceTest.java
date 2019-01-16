package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    @DisplayName("Ensure getRoom method from microwave returns kitchen")
    void getRoom() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Device microwave = new Device("Samsung Microwave", kitchen, DeviceType.MICROWAVE_OVEN.getType(), 0.8);

        Room expected = kitchen;
        Room result = microwave.getRoom();

        assertEquals(expected, result);
    }

    @Test
    void getNominalPower() {
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        Device microwave = new Device("Samsung Microwave", kitchen, DeviceType.MICROWAVE_OVEN.getType(), 0.8);

        double expected = 0.8;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure that getType returns enum string correspondent to DeviceType.FRIDGE constant")
    void getType() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device dFridge = new Device("LG Fridge",fridge,kitchen,1.5);

        String expectedResult= dFridge.getType();
        String result= "Fridge";

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure that getTypeFromIndex returns String correspondent to DeviceType.FRIDGE constant")
    void getTypeFromIndex() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device dFridge = new Device("LG Fridge",fridge,kitchen,1.5);

        String result= "Fridge";
        String expectedResult= dFridge.getTypeFromIndex(3);

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure getDeviceSpecs() returns fridge as Device Specs")
    void getDeviceSpecs() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device dFridge = new Device("LG Fridge",fridge,kitchen,1.5);

        DeviceSpecs expectedResult= dFridge.getDeviceSpecs();
        Fridge result= fridge;

        assertEquals(expectedResult,result);
    }
}