package smarthome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.*;

import static org.junit.jupiter.api.Assertions.*;

class USEnergyConsumptionOfEWHCTRLTest {

    @Test
    @DisplayName("Ensure that method sets volume of water for all electric water heater devices installed in all rooms of the house")
    void setVolumeOfWaterInGlobalEWHList() {
        House house = new House();
        USEnergyConsumptionOfEWHCTRL ctrl= new USEnergyConsumptionOfEWHCTRL(house);
        Room kitchen = new Room("Kitchen",0,6,3.5,3);
        Room garage = new Room("Garage",0,6,4,3);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(garage);
        ElectricWaterHeater ewh1=new ElectricWaterHeater(65,1);
        ElectricWaterHeater ewh2=new ElectricWaterHeater(60,0.9);
        Device dEWH1= new Device("Daikin - Electric Water Heater1",ewh1,kitchen,3,DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2= new Device("Daikin - Electric Water Heater2",ewh1,garage,2.5,DeviceType.ELECTRIC_WATER_HEATER);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setVolumeOfWaterInGlobalEWHList(70);

        double expected=70;
        double resultEWH1=((ElectricWaterHeater)dEWH1.getDeviceSpecs()).getVolumeOfWater();
        assertEquals(expected,resultEWH1);

        double resultEWH2=((ElectricWaterHeater)dEWH2.getDeviceSpecs()).getVolumeOfWater();
        assertEquals(expected,resultEWH2);
    }

    @Test
    @DisplayName("Ensure that method sets cold water temperature for all electric water heater devices installed in all rooms of the house")
    void setColdWaterTemperatureInGlobalEWHList() {
        House house = new House();
        USEnergyConsumptionOfEWHCTRL ctrl= new USEnergyConsumptionOfEWHCTRL(house);
        Room kitchen = new Room("Kitchen",0,6,3.5,3);
        Room garage = new Room("Garage",0,6,4,3);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(garage);
        ElectricWaterHeater ewh1=new ElectricWaterHeater(65,1);
        ElectricWaterHeater ewh2=new ElectricWaterHeater(60,0.9);
        Device dEWH1= new Device("Daikin - Electric Water Heater1",ewh1,kitchen,3,DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2= new Device("Daikin - Electric Water Heater2",ewh2,garage,2.5,DeviceType.ELECTRIC_WATER_HEATER);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setColdWaterTemperatureInGlobalEWHList(15);

        double expected=15;
        double resultEWH1=((ElectricWaterHeater)dEWH1.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected,resultEWH1);

        double resultEWH2=((ElectricWaterHeater)dEWH2.getDeviceSpecs()).getColdWaterTemperature();
        assertEquals(expected,resultEWH2);
    }

    @Test
    void getEnergyConsumptionOfEWHGlobalList() {
        House house = new House();
        USEnergyConsumptionOfEWHCTRL ctrl= new USEnergyConsumptionOfEWHCTRL(house);
        Room kitchen = new Room("Kitchen",0,6,3.5,3);
        Room garage = new Room("Garage",0,6,4,3);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(garage);
        ElectricWaterHeater ewh1=new ElectricWaterHeater(65,1);
        ElectricWaterHeater ewh2=new ElectricWaterHeater(60,0.9);
        Device dEWH1= new Device("Daikin - Electric Water Heater1",ewh1,kitchen,3,DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2= new Device("Daikin - Electric Water Heater2",ewh2,garage,2.5,DeviceType.ELECTRIC_WATER_HEATER);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        ctrl.setColdWaterTemperatureInGlobalEWHList(15);
        ctrl.setVolumeOfWaterInGlobalEWHList(70);

        double expected=7367.605;
        double result=ctrl.getEnergyConsumptionOfEWHGlobalList();
        assertEquals(expected,result);

    }

    @Test
    void showElectricWaterHeaterList() {
        House house = new House();
        USEnergyConsumptionOfEWHCTRL ctrl = new USEnergyConsumptionOfEWHCTRL(house);
        Room kitchen = new Room("Kitchen",0,6,3.5,3);
        Room garage = new Room("Garage",0,6,4,3);
        house.getRoomListFromHouse().addRoom(kitchen);
        house.getRoomListFromHouse().addRoom(garage);
        ElectricWaterHeater ewh1=new ElectricWaterHeater(65,1);
        ElectricWaterHeater ewh2=new ElectricWaterHeater(60,0.9);
        Device dEWH1= new Device("Daikin - Electric Water Heater1",ewh1,kitchen,3,DeviceType.ELECTRIC_WATER_HEATER);
        Device dEWH2= new Device("Daikin - Electric Water Heater2",ewh2,garage,2.5,DeviceType.ELECTRIC_WATER_HEATER);
        kitchen.getDeviceList().addDevice(dEWH1);
        garage.getDeviceList().addDevice(dEWH2);
        house.setVolumeOfWaterInGlobalEWHList(65);
        house.setColdWaterTemperatureInGlobalEWHList(20);
        String expected="ELECTRIC WATER HEATER \n" +
                "Room: Kitchen\n" +
                "1 - Device name : Daikin - Electric Water Heater1\n" +
                "2 - Device room : Kitchen\n" +
                "3 - Nominal Power : 3.0 kW\n" +
                "4 - Volume of water : 65.0\n" +
                "5 - Hot water temperature : 65.0\n" +
                "6 - Cold water temperature : 20.0\n" +
                "7 - Performance Ratio : 1.0\n" +
                "8 - Energy Consumption: 3401.775 KWh\n" +
                "\n" +
                "ELECTRIC WATER HEATER \n" +
                "Room: Garage\n" +
                "1 - Device name : Daikin - Electric Water Heater2\n" +
                "2 - Device room : Garage\n" +
                "3 - Nominal Power : 2.5 kW\n" +
                "4 - Volume of water : 65.0\n" +
                "5 - Hot water temperature : 60.0\n" +
                "6 - Cold water temperature : 20.0\n" +
                "7 - Performance Ratio : 0.9\n" +
                "8 - Energy Consumption: 2721.42 KWh\n\n";
        String result=ctrl.showElectricWaterHeaterList();
        assertEquals(expected,result);
    }
}