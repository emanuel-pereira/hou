package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    @DisplayName("Ensure same device instance is only added once to device list")
    void addDevice() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Device microwave = new Device("Samsung Microwave",DeviceType.MICROWAVE_OVEN,kitchen,0.8);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(microwave);
        int expectedResult1= 1;
        int result1= deviceList.getDeviceList().size();
        assertEquals(expectedResult1,result1);

        deviceList.addDevice(microwave);
        int expectedResult2= 1;
        int result2= deviceList.getDeviceList().size();
        assertEquals(expectedResult2,result2);
    }
    @Test
    @DisplayName("Ensure newDevice method creates local instance of device with specs and that addDevice method adds it deviceList")
    void newDeviceWithSpecs() {
        DeviceList deviceList= new DeviceList();
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device device = deviceList.newDevice("LG Fridge",fridge,kitchen,1.2);
        deviceList.addDevice(device);

        Device expected= device;
        Device result= deviceList.getDeviceList().get(0);

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure newDevice method creates local instance of device without specs and that addDevice method adds it deviceList")
    void newDeviceWithoutSpecs() {
        DeviceList deviceList= new DeviceList();
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Device device = deviceList.newDevice("Samsung Microwave",DeviceType.MICROWAVE_OVEN,kitchen,0.8);
        deviceList.addDevice(device);

        Device expected= device;
        Device result= deviceList.getDeviceList().get(0);

        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Ensure getLastElement().getName() returns last element from device list and respective device name")
    void getLastElement() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Device microwave = new Device("Samsung Microwave",DeviceType.MICROWAVE_OVEN,kitchen,0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device dFridge = new Device("LG Fridge",fridge,kitchen,1.5);

        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(microwave);
        deviceList.addDevice(dFridge);
        String expectedResult= deviceList.getLastElement().getName();
        String result= "LG Fridge";

        assertEquals(expectedResult,result);
    }

    @Test
    @DisplayName("Ensure get(0).getDeviceType methods returns first element of deviceList and respective deviceType")
    void get() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        Device microwave = new Device("Samsung Microwave",DeviceType.MICROWAVE_OVEN,kitchen,0.8);
        DeviceList deviceList = new DeviceList();
        deviceList.addDevice(microwave);

        String expectedResult= deviceList.get(0).getType();
        String result= "Microwave Oven";

        assertEquals(expectedResult,result);
    }


    @Test
    void showDeviceListInString() {
        Room kitchen= new Room("Kitchen",0,6,4,2.5);
        DeviceList deviceList = new DeviceList();
        Device microwave = new Device("Samsung Microwave",DeviceType.MICROWAVE_OVEN,kitchen,0.8);
        Fridge fridge = new Fridge(DeviceType.FRIDGE,50,350,50);
        Device dFridge = new Device("LG Fridge",fridge,kitchen,1.5);
        deviceList.addDevice(microwave);
        deviceList.addDevice(dFridge);
        String expected="1 - Device: Samsung Microwave | Type: Microwave Oven\n2 - Device: LG Fridge | Type: Fridge\n";
        String result= deviceList.showDeviceListInString();
        assertEquals(expected,result);
    }

}