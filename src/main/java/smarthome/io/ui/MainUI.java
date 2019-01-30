
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
    public static void main(String[] args) {
        SensorTypeList sensorTypeList = new SensorTypeList();
        GAList gaList = new GAList();
        House house = new House();
        HouseGridList hglist = new HouseGridList();
        PowerSourceList pslist = new PowerSourceList();
        List<Room> roomList = new ArrayList<>();

        bootstrap(house, hglist, pslist, roomList, gaList, sensorTypeList);

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
                case 1:
                    systemAdministration(sensorTypeList, gaList);
                    break;
                case 2:
                    houseAdministration(sensorTypeList, gaList, roomList, house, hglist, pslist);
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


    private static void bootstrap(House house, HouseGridList hglist, PowerSourceList pslist, List<Room> roomList, GAList gaList, SensorTypeList sensorTypeList) {


        GeographicalArea porto = new GeographicalArea("001", "Porto", "city", 2, 4, 5, 5, 6);
        gaList.addGA(porto);
        SensorList list = porto.getSensorListInGA();

        Reading r1 = new Reading(15, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r2 = new Reading(18, new GregorianCalendar(2018, 12, 26, 13, 00));
        Reading r3 = new Reading(20, new GregorianCalendar(2018, 12, 26, 14, 00));
        Reading r4 = new Reading(22, new GregorianCalendar(2018, 12, 26, 15, 00));
        ReadingList rl = new ReadingList();
        rl.addReading(r1);
        rl.addReading(r2);
        rl.addReading(r3);
        rl.addReading(r4);
        SensorType sT1 = sensorTypeList.newSensorType("rainfall");
        sensorTypeList.addSensorType(sT1);
        list.addSensor(list.newSensor("RainSensor", new GregorianCalendar(2018, 12, 15), 24, 34, 25, sT1, "C", rl));

        Reading r5 = new Reading(80, new GregorianCalendar(2018, 12, 26, 12, 00));
        Reading r6 = new Reading(81, new GregorianCalendar(2018, 12, 26, 13, 00));
        ReadingList rl2 = new ReadingList();
        rl2.addReading(r5);
        rl2.addReading(r6);
        SensorType sH1 = sensorTypeList.newSensorType("humidity");
        sensorTypeList.addSensorType(sH1);
        list.addSensor(list.newSensor("HumiditySensor", new GregorianCalendar(2018, 12, 15), 25, 32, 25, sH1, "Percentage", rl2));


        SensorType temperature = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(temperature);
        Room B106 = house.getRoomList().createNewRoom("B106", 1, 7, 13, 3.5);
        house.getRoomList().addRoom(B106);
        Room B107 = house.getRoomList().createNewRoom("B107", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B107);
        Room B109 = house.getRoomList().createNewRoom("B109", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(B109);
        HouseGrid mainGrid = house.getHGListInHouse().newHouseGrid("Main Grid");
        house.getHGListInHouse().addHouseGrid(mainGrid);

        mainGrid.attachRoomToGrid(B106);
        mainGrid.attachRoomToGrid(B107);
        mainGrid.attachRoomToGrid(B109);

        int year = 2018, month = 1, day = 1, hour = 0, minutes = 0, meteringPeriod = 15;

        ElectricWaterHeater ewh106 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 150, 55, 0.92);
        Device eHWB106 = house.getRoomList().get(0).getDeviceList().newDevice("EHW B106", ewh106, 2.2);
        house.getRoomList().get(0).getDeviceList().addDevice(eHWB106);
        int[] valueseHWB106 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
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
        }

        Dishwasher dish106 = new Dishwasher(DeviceType.DISHWASHER, 100);
        Device dishwasherB106 = house.getRoomList().get(0).getDeviceList().newDevice("Dishwasher B106", dish106, 1.4);
        house.getRoomList().get(0).getDeviceList().addDevice(dishwasherB106);
        int[] valuesdishwasherB106 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
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
        }

        ElectricWaterHeater ewh107 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 150, 55, 0.92);
        Device eHWB107 = house.getRoomList().get(1).getDeviceList().newDevice("EHW B107", ewh107, 2.2);
        eHWB107.setIsMetered(false);
        house.getRoomList().get(1).getDeviceList().addDevice(eHWB107);

        ElectricWaterHeater ewh109 = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, 100, 55, 0.91);
        Device eHWB109 = house.getRoomList().get(2).getDeviceList().newDevice("EHW B109", ewh109, 1.5);
        house.getRoomList().get(2).getDeviceList().addDevice(eHWB109);
        int[] valueseHWB109 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : valueseHWB109) {
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

        Dishwasher dish109 = new Dishwasher(DeviceType.DISHWASHER, 100);
        Device dishwasherB109 = house.getRoomList().get(2).getDeviceList().newDevice("Dishwasher B109", dish109, 1.5);
        house.getRoomList().get(2).getDeviceList().addDevice(dishwasherB109);
        int[] valuesdishwasherB109 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : valuesdishwasherB109) {
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

        WashingMachine washing109 = new WashingMachine(DeviceType.WASHING_MACHINE, 100);
        Device washingMachineB109 = house.getRoomList().get(2).getDeviceList().newDevice("Washing Machine B109", washing109, 2.5);
        house.getRoomList().get(2).getDeviceList().addDevice(washingMachineB109);
        int[] valueswashingMachineB109 = new int[]{23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        for (int i : valueswashingMachineB109) {
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
    /*
        Program p1dish106 = dish106.getmProgramListInDW().newProgram("Glasses", 0.8);
        Program p2dish106 = dish106.getmProgramListInDW().newProgram("Light", 1.3);
        Program p3dish106 = dish106.getmProgramListInDW().newProgram("Light Turbo", 1.9);
        Program p4dish106 = dish106.getmProgramListInDW().newProgram("Dishes", 2.3);
        dish106.getmProgramListInDW().addProgram(p1dish106);
        dish106.getmProgramListInDW().addProgram(p2dish106);
        dish106.getmProgramListInDW().addProgram(p3dish106);
        dish106.getmProgramListInDW().addProgram(p4dish106);
    */
    /*  Program p1Wm109 = dish106.getmProgramListInDW ().newProgram ("Wool",0.9);
        Program p2Wm109 = dish106.getmProgramListInDW ().newProgram ("Fast",1.3);
        Program p3Wm109 = dish106.getmProgramListInDW ().newProgram ("Fast Plus",1.7);
        Program p4Wm109 = dish106.getmProgramListInDW ().newProgram ("Synthetic 30",2.1);
        washing109.getmProgramListInDW ().addProgram (p1Wm109);
        washing109.getmProgramListInDW ().addProgram (p2Wm109);
        washing109.getmProgramListInDW ().addProgram (p3Wm109);
        washing109.getmProgramListInDW ().addProgram (p4Wm109);
    */
    }
}