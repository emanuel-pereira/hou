
package smarthome.io.ui;

import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

public class MainUI {
    public static void main(String[] args) {
        SensorTypeList sensorTypeList = new SensorTypeList ();
        GAList gaList = new GAList ();
        House house = new House ();
        HouseGridList hglist = new HouseGridList ();
        PowerSourceList pslist = new PowerSourceList ();
        List<Room> roomList = new ArrayList<> ();


        //Dummy Objects
        SensorType temperature = sensorTypeList.newSensorType ("temperature");
        sensorTypeList.addSensorType (temperature);
        Room B107 = house.getRoomList ().createNewRoom ("B107", 1, 7, 11, 3.5);
        house.getRoomList ().addRoom (B107);
        Room B109 = house.getRoomList ().createNewRoom ("B109", 1, 7, 11, 3.5);
        house.getRoomList ().addRoom (B109);
        Room B106 = house.getRoomList ().createNewRoom ("B106", 1, 7, 13, 3.5);
        house.getRoomList ().addRoom (B106);
        HouseGrid mainGrid = house.getHGListInHouse ().newHouseGrid ("Main Grid");
        house.getHGListInHouse ().addHouseGrid (mainGrid);

        mainGrid.attachRoomToGrid (B107);
        mainGrid.attachRoomToGrid (B109);
        mainGrid.attachRoomToGrid (B106);

        ElectricWaterHeater ewh109 = new ElectricWaterHeater (DeviceType.ELECTRIC_WATER_HEATER, 100, 55, 0.91);
        ElectricWaterHeater ewh106 = new ElectricWaterHeater (DeviceType.ELECTRIC_WATER_HEATER, 150, 55, 0.92);
        Dishwasher dish109 = new Dishwasher (DeviceType.DISHWASHER, 100);

        Dishwasher dish106 = new Dishwasher (DeviceType.DISHWASHER, 100);

        WashingMachine washingMachine = new WashingMachine (DeviceType.WASHING_MACHINE, 100);


        Device eHWB109 = house.getRoomList ().get (1).getDeviceList ().newDevice ("EHW B109", ewh109, 1.5);
        house.getRoomList ().get (1).getDeviceList ().addDevice (eHWB109);
        Device dishwasherB109 = house.getRoomList ().get (1).getDeviceList ().newDevice ("Dishwasher B109", dish109, 1.5);
        house.getRoomList ().get (1).getDeviceList ().addDevice (dishwasherB109);
        Device washingMachineB109 = house.getRoomList ().get (1).getDeviceList ().newDevice ("Washing Machine B109", washingMachine, 2.5);
        house.getRoomList ().get (1).getDeviceList ().addDevice (washingMachineB109);

        Device eHWB106 = house.getRoomList ().get (2).getDeviceList ().newDevice ("EHW B106", ewh106, 2.2);
        house.getRoomList ().get (2).getDeviceList ().addDevice (eHWB106);
        Device dishwasherB106 = house.getRoomList ().get (2).getDeviceList ().newDevice ("Dishwasher B106", dish106, 1.4);
        house.getRoomList ().get (2).getDeviceList ().addDevice (dishwasherB106);


        Scanner keyboard = new Scanner (System.in);
        int option = -1;

        while (option != 0) {
            System.out.println ("Click 1 System Administration");
            System.out.println ("Click 2 House Administration");
            System.out.println ("Click 3 Regular User");
            System.out.println ("Click 4 Power User");
            System.out.println ("Click 5 Room Owner");
            System.out.println ("Click 0 Exit");

            option = Integer.parseInt (keyboard.nextLine ());
            switch (option) {
                case 1:
                    systemAdministration (sensorTypeList, gaList);
                    break;
                case 2:
                    houseAdministration (sensorTypeList, gaList, roomList, house, hglist, pslist);
                    break;
                case 3:
                    regularUsage (house, sensorTypeList, gaList);
                    break;
                case 4:
                    powerUser (house);
                    break;
                case 5:
                    roomOwner (house);
                    break;
            }
        }
    }
}
