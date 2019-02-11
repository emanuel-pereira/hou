
package smarthome.io.ui;

import smarthome.model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

public class MainUI {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        HouseGridList hgList = new HouseGridList();
        PowerSourceList psList = new PowerSourceList();
        List<Room> roomList = new ArrayList<>();

        bootstrap(house, gaList, sensorTypeList);

        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Click 1 System Administration");
            System.out.println("Click 2 House Administration");
            System.out.println("Click 3 Regular User");
            System.out.println("Click 4 Power User");
            System.out.println("Click 5 Room Owner");
            System.out.println("Click 0 Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 0:
                    break;
                case 1:
                    systemAdministration(sensorTypeList, gaList);
                    break;
                case 2:
                    houseAdministration(sensorTypeList, gaList, roomList, house, hgList, psList);
                    break;
                case 3:
                    regularUsage(house, sensorTypeList, gaList);
                    break;
                case 4:
                    powerUser(house);
                    break;
                case 5:
                    roomOwner(house);
                    break;
                default:
                    System.out.println("Please chose an action between 1 and 5");
                    option = -1;
                    break;
            }
        }
    }


    private static void bootstrap(House house, GAList gaList, SensorTypeList sensorTypeList) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        GeographicalArea porto = new GeographicalArea("001", "Porto", "city", 2, 4, 5, 5, 6);
        house.setHouseGA(porto);
        house.setHouseAddress("Rua Dr António Bernardino de Almeida", "431", "4200-072", 41.177748, -8.607745, 112);
        gaList.addGA(porto);
        SensorList list = porto.getSensorListInGA();

        Reading r1 = new Reading(0.5, new GregorianCalendar(2018, 11, 29, 12, 0));
        Reading r2 = new Reading(1.2, new GregorianCalendar(2018, 11, 30, 13, 0));
        Reading r3 = new Reading(1.5, new GregorianCalendar(2018, 11, 31, 14, 0));
        Reading r4 = new Reading(0.3, new GregorianCalendar(2019, 0, 1, 15, 0));
        Reading r5 = new Reading(0, new GregorianCalendar(2019, 0, 2, 13, 0));
        Reading r6 = new Reading(0, new GregorianCalendar(2019, 0, 2, 14, 0));
        Reading r7 = new Reading(0, new GregorianCalendar(2019, 0, 3, 15, 0));


        ReadingList rl = new ReadingList();
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        rl.addReading(r5);
        rl.addReading(r6);
        rl.addReading(r7);
        SensorType sT1 = sensorTypeList.newSensorType("rainfall");
        sensorTypeList.addSensorType(sT1);
        list.addSensor(list.newSensor("RainSensor1", new GregorianCalendar(2018, 11, 15), 24, 34, 25, sT1, "C", rl));

        Reading r8 = new Reading(2, new GregorianCalendar(2019, 0, 3, 16, 0));
        ReadingList rl2 = new ReadingList();
        rl2.addReading(r1);
        rl2.addReading(r2);
        rl2.addReading(r3);
        rl2.addReading(r4);
        rl2.addReading(r5);
        rl2.addReading(r6);
        rl2.addReading(r8);
        list.addSensor(list.newSensor("RainSensor2", new GregorianCalendar(2018, 1, 15), 24, 34, 25, sT1, "C", rl2));


        Reading r9 = new Reading(80, new GregorianCalendar(2018, 11, 26, 12, 0));
        Reading r10 = new Reading(81, new GregorianCalendar(2018, 11, 26, 13, 0));
        ReadingList rl3 = new ReadingList();
        rl3.addReading(r9);
        rl3.addReading(r10);
        SensorType sH1 = sensorTypeList.newSensorType("humidity");
        sensorTypeList.addSensorType(sH1);
        list.addSensor(list.newSensor("HumiditySensor", new GregorianCalendar(2018, 11, 15), 25, 32, 25, sH1, "Percentage", rl3));


        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        Room b106 = house.getRoomList().createNewRoom("B106", 1, 7, 13, 3.5);
        house.getRoomList().addRoom(b106);
        Room b107 = house.getRoomList().createNewRoom("B107", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(b107);
        Room b109 = house.getRoomList().createNewRoom("B109", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(b109);
        HouseGrid mainGrid = house.getHGListInHouse().newHouseGrid("Main Grid");
        house.getHGListInHouse().addHouseGrid(mainGrid);

        mainGrid.attachRoomToGrid(b106);
        mainGrid.attachRoomToGrid(b107);
        mainGrid.attachRoomToGrid(b109);

        int year = 2018;
        int month = 11;
        int day = 31;
        int hour = 0;
        int minutes = 0;
        int meteringPeriod = 15;

        DeviceType electricWaterHeaterType = new DeviceType("ElectricWaterHeater");
        Device eHWB106 = house.getRoomList().get(0).getDeviceList().newDeviceV2(electricWaterHeaterType);
        house.getRoomList().get(0).getDeviceList().addDevice(eHWB106);
        eHWB106.setAttributeValue("Device Name", "EHW B106");
        eHWB106.setAttributeValue("Device Nominal Power", "2.2");
        eHWB106.setAttributeValue("Volume of water capacity", "150");
        eHWB106.setAttributeValue("Hot water temperature", "55");
        eHWB106.setAttributeValue("Performance Ratio", "0.92");
        /*int[] valueseHWB106 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : valueseHWB106) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            eHWB106.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }*/
        eHWB106.setIsMetered (true);

        DeviceType dishwasherType = new DeviceType("Dishwasher");
        Device dishwasherB106 = house.getRoomList().get(0).getDeviceList().newDeviceV2(dishwasherType);
        house.getRoomList().get(0).getDeviceList().addDevice(dishwasherB106);
        dishwasherB106.setAttributeValue("Device Name", "Dishwasher B106");
        dishwasherB106.setAttributeValue("Device Nominal Power", "1.4");
        dishwasherB106.setAttributeValue("Dishwasher Capacity", "100");
        /*int[] valuesdishwasherB106 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : valuesdishwasherB106) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            dishwasherB106.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }*/
        dishwasherB106.setIsMetered (true);

        Device eHWB107 = house.getRoomList().get(1).getDeviceList().newDeviceV2(electricWaterHeaterType);
        house.getRoomList().get(1).getDeviceList().addDevice(eHWB107);
        eHWB107.setAttributeValue("Device Name", "EHW B107");
        eHWB107.setAttributeValue("Device Nominal Power", "2.2");
        eHWB107.setAttributeValue("Volume of water capacity", "150");
        eHWB107.setAttributeValue("Hot water temperature", "55");
        eHWB107.setAttributeValue("Performance Ratio", "0.92");

        double[] valueseHWB107 = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (double i : valueseHWB107) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            eHWB107.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;
        eHWB107.setIsMetered (true);

        Device dishwasherB107 = house.getRoomList().get(1).getDeviceList().newDeviceV2(dishwasherType);
        house.getRoomList().get(1).getDeviceList().addDevice(dishwasherB107);
        dishwasherB107.setAttributeValue("Device Name", "Dishwasher B107");
        dishwasherB107.setAttributeValue("Device Nominal Power", "1.5");
        dishwasherB107.setAttributeValue("Dishwasher Capacity", "100");
        double[] valuesdishwasherB107 = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.500, 0.500, 0.500, 0.200, 0.300, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.000, 0.000, 0.000, 0.100, 0.100, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (double i : valuesdishwasherB107) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            dishwasherB107.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }
        hour = 0;
        minutes = 0;
        dishwasherB107.setIsMetered (true);

        DeviceType whashingMachineType = new DeviceType("WashingMachine");
        Device washingMachineB107 = house.getRoomList().get(1).getDeviceList().newDeviceV2(whashingMachineType);
        house.getRoomList().get(1).getDeviceList().addDevice(washingMachineB107);
        washingMachineB107.setAttributeValue("Device Name", "Washing Machine B107");
        washingMachineB107.setAttributeValue("Device Nominal Power", "2.5");
        washingMachineB107.setAttributeValue("Washing Machine Capacity","100");
        double[] valueswashingMachineB107 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.40, 0.20, 0.25, 0.25, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,};
        for (double i : valueswashingMachineB107) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            washingMachineB107.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;
        washingMachineB107.setIsMetered (true);

        Device eHWB109 = house.getRoomList().get(2).getDeviceList().newDeviceV2(electricWaterHeaterType);
        house.getRoomList().get(2).getDeviceList().addDevice(eHWB109);
        eHWB109.setAttributeValue("Device Name", "EHW B109");
        eHWB109.setAttributeValue("Device Nominal Power", "1.5");
        eHWB109.setAttributeValue("Volume of water capacity", "100");
        eHWB109.setAttributeValue("Hot water temperature", "55");
        eHWB109.setAttributeValue("Performance Ratio", "0.91");
        double[] valueseHWB109 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.20, 0.50, 0.50, 0.50, 0.50, 0.25, 0.00, 0.00, 0.00, 0.00, 0.20, 0.20, 0.00, 0.00, 0.00, 0.00, 0.20, 0.50, 0.50, 0.50, 0.50, 0.20, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.10, 0.50, 0.50, 0.50, 0.15, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00};
        for (double i : valueseHWB109) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            eHWB109.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;
        eHWB109.setIsMetered (true);

        Device dishwasherB109 = house.getRoomList().get(2).getDeviceList().newDeviceV2(dishwasherType);
        house.getRoomList().get(2).getDeviceList().addDevice(dishwasherB109);
        dishwasherB109.setAttributeValue("Device Name", "Dishwasher B109");
        dishwasherB109.setAttributeValue("Device Nominal Power", "1.5");
        dishwasherB109.setAttributeValue("Dishwasher Capacity", "100");
        //No energy consumption values were measured
        dishwasherB109.setIsMetered (false);

        Device washingMachineB109 = house.getRoomList().get(2).getDeviceList().newDeviceV2(whashingMachineType);
        house.getRoomList().get(2).getDeviceList().addDevice(washingMachineB109);
        washingMachineB109.setAttributeValue("Device Name", "Washing Machine B109");
        washingMachineB109.setAttributeValue("Device Nominal Power", "2.5");
        washingMachineB109.setAttributeValue("Washing Machine Capacity","100");
        double[] valueswashingMachineB109 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.40, 0.20, 0.20, 0.25, 0.25, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,};
        for (double i : valueswashingMachineB109) {
            minutes += meteringPeriod;
            if (minutes == 60) {
                minutes = 0;
                hour++;
            }
            if (hour == 24) {
                hour = 0;
                day++;
            }
            washingMachineB109.getActivityLog().addReading(new Reading(i, new GregorianCalendar(year, month, day, hour, minutes)));
        }
        washingMachineB109.setIsMetered (true);
    }
}