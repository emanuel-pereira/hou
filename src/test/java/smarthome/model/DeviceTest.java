package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    @Test
    void getNominalPower() {
        OtherDevices micro = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", micro, 0.8);

        double expected = 0.8;
        double result = microwave.getNominalPower();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure getDeviceSpecs() returns fridge as Device Specs")
    void getDeviceSpecs() {
        Fridge fridge = new Fridge(50, 350, 50);
        Device dFridge = new Device("LG Fridge", fridge, 1.5);

        DeviceSpecs expectedResult = dFridge.getDeviceSpecs();

        assertEquals(expectedResult, fridge);
    }

   /* @Test
    void setDeviceAttributesTest() {

        OtherDevices stove= new OtherDevices(DeviceType.STOVE);
        Device d1 = new Device("device1",stove, 40);
        d1.setName("TV");
        d1.setNominalPower(47);

        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : TV\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 47.0\n";
        assertEquals(expected, result);
    }*/

   /* @Test
    void showDeviceSpecsListAttributesInString() {
        Lamp lamp = new Lamp(DeviceType.LAMP,6);
        Device d1 = new Device("Lamp1", lamp, 40);
        String result = d1.showDeviceAttributesInString();
        String expected = "1 - Device Name : Lamp1\n" +
                "2 - Device Room : Living Room\n" +
                "3 - Device Nominal Power : 40.0\n" +
                "4 - Luminous Flux : 6\n";
        assertEquals(expected, result);


    }*/

    @Test
    void setAttributeValue() {
        Lamp lamp = new Lamp(6);
        Device d1 = new Device("Lamp1", lamp, 40);
        String name = "2 - Device Name : " + d1.getName();
        d1.setAttributeValue(name, "Lamp 55");
        assertEquals("Lamp 55", d1.getName());
        String nominalPower = "3 - Device Nominal Power : " + d1.getNominalPower();
        d1.setAttributeValue(nominalPower, "50");
        assertEquals(50, d1.getNominalPower());
    }

    @Test
    @DisplayName("Ensure that getEnergyConsumption returns energy consumed by a Electric Water Heater in a given day.")
    void getConsumption() {
        ElectricWaterHeater ewh = new ElectricWaterHeater();
        Device dEWH = new Device(ewh);
        String volumeOfWater = "Volume of water capacity";
        String hotWaterTemperature = "Hot water temperature";
        String coldWaterTemperature = "Cold water temperature";
        String performanceRatio = "Performance Ratio";
        String volumeOfWaterToHeat = "Volume of water to heat";
        dEWH.setAttributeValue(performanceRatio, "1");
        dEWH.setAttributeValue(hotWaterTemperature, "65");
        dEWH.setAttributeValue(volumeOfWater, "75");
        dEWH.setAttributeValue(volumeOfWaterToHeat, "55");
        dEWH.setAttributeValue(coldWaterTemperature, "15");
        double expected = 3.2;
        double result = dEWH.getEnergyConsumption();
        assertEquals(expected, result);
    }

    @Test
    void status() {
        OtherDevices micro = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", micro, 0.8);

        assertTrue(microwave.status());
    }

    @Test
    void deactivateDevice() {
        OtherDevices micro = new OtherDevices();
        Device microwave = new Device("Samsung Microwave", micro, 0.8);

        assertTrue(microwave.deactivateDevice());
        assertFalse(microwave.status());
    }

    @Test
    void getEnergyConsumptionInPeriod() {
        DeviceSpecs ewh = new ElectricWaterHeater(25, 50, 2);
        Device dEWH = new Device("EWH DAIKIN", ewh, 15);
        ReadingList activityLog = dEWH.getActivityLog();
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 10));
        Reading r3 = new Reading(22, new GregorianCalendar(2018, 11, 5, 0, 20));
        Reading r4 = new Reading(37, new GregorianCalendar(2018, 11, 5, 0, 30));
        Reading r5 = new Reading(31, new GregorianCalendar(2018, 11, 5, 0, 40));
        Reading r6 = new Reading(18, new GregorianCalendar(2018, 11, 5, 0, 50));
        Reading r7 = new Reading(22, new GregorianCalendar(2018, 11, 5, 1, 00));
        Reading r8 = new Reading(37, new GregorianCalendar(2018, 11, 5, 1, 10));
        activityLog.addReading(r2);
        activityLog.addReading(r3);
        activityLog.addReading(r4);
        activityLog.addReading(r5);
        activityLog.addReading(r6);
        activityLog.addReading(r7);
        activityLog.addReading(r8);
        GregorianCalendar startDate = new GregorianCalendar(2018, 11, 5, 0, 30);
        GregorianCalendar endDate = new GregorianCalendar(2018, 11, 5, 1, 00);
        dEWH.setIsMetered(true);
        double expected = 71;
        double result = dEWH.getEnergyConsumptionInPeriod(startDate, endDate);

        assertEquals(expected, result);
    }

    @Test
    void getActivityLogSumIfMetered() {
        House house = new House();
        Room B106 = house.getRoomList().createNewRoom("B106", 1, 7, 13, 3.5);
        house.getRoomList().addRoom(B106);
        Room B107 = house.getRoomList().createNewRoom("B107", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B107);
        Room B109 = house.getRoomList().createNewRoom("B109", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B109);
        Dishwasher dish109 = new Dishwasher(100);
        Device dishwasherB109 = house.getRoomList().get(2).getDeviceList().newDevice("Dishwasher B109", dish109, 1.5);
        house.getRoomList().get(2).getDeviceList().addDevice(dishwasherB109);

        int year = 2018, month = 1, day = 1, hour = 0, minutes = 0, meteringPeriod = 15;
        int[] values = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : values) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            dishwasherB109.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }

        assertEquals(1022, house.getRoomList().getRoomList().get(2).getDeviceList().get(0).getActivityLogSum());
    }

    @Test
    void getActivityLogSumIfNotMetered() {
        House house = new House();
        Room B106 = house.getRoomList().createNewRoom("B106", 1, 7, 13, 3.5);
        house.getRoomList().addRoom(B106);
        Room B107 = house.getRoomList().createNewRoom("B107", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B107);
        Room B109 = house.getRoomList().createNewRoom("B109", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B109);
        Dishwasher dish109 = new Dishwasher(100);
        Device dishwasherB109 = house.getRoomList().get(2).getDeviceList().newDevice("Dishwasher B109", dish109, 1.5);
        dishwasherB109.setIsMetered(false);
        house.getRoomList().get(2).getDeviceList().addDevice(dishwasherB109);
        int year = 2018, month = 1, day = 1, hour = 0, minutes = 0, meteringPeriod = 15;
        int[] values = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        boolean thrown = false;
        for (int i : values) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            try {
                dishwasherB109.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
            } catch (NullPointerException e) {
                thrown = true;
            }
            assertTrue(thrown);
        }
    }

    @Test
    void newDeviceV2Test() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        House house = new House();
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        DeviceType fridge = new DeviceType("Fridge");
        Device device = kitchenDevList.newDeviceV2(fridge);
        kitchenDevList.addDevice(device);
        String result = device.getDeviceSpecs().getDeviceType().getDeviceTypeName();
        assertEquals("Fridge", result);
    }
    void newDeviceOtherDevicesTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        House house = new House();
        Room kitchen = new Room("Kitchen", 0, 6, 4, 2.5);
        DeviceList kitchenDevList = kitchen.getDeviceList();
        DeviceType deviceType = new DeviceType("Television");
        Device device = kitchenDevList.newDeviceV2(deviceType);
        kitchenDevList.addDevice(device);
        String result = device.getDeviceSpecs().getDeviceType().getDeviceTypeName();
        assertEquals("Television", result);
    }
}