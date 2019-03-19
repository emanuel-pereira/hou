package smarthome.io.ui;

import smarthome.controller.EditDevicesCTRL;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class EditDevicesUI {
    private House mHouse;
    private Scanner read = new Scanner(System.in);
    private int selectedRoomIndex;
    private int deviceTypeIndex;
    private EditDevicesCTRL ctrl;
    private Room selectedRoom;
    private Device device;
    private Device selectedDeviceToEdit;
    private int deviceIndex;
    private int attributeIndex;


    public EditDevicesUI(House house) {
        mHouse = house;
        ctrl = new EditDevicesCTRL(mHouse);

    }

    public void selectOption() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int option = -1;
        while (option != 0) {

            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Add a device to a room");
            options.add("[2] List all devices in a room");
            options.add("[3] Edit the configuration of a device");
            options.add("[4] Remove a device from a room");
            options.add("[5] Deactivate a device");
            options.add("[0] Return to previous menu");

            UtilsUI.showList("Edit Device", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 8, or 0 to go back.");

            switch (option) {
                case 1:
                    this.roomSelectionToAddDevice();
                    break;
                case 2:
                    this.roomSelectionToListDevice();
                    break;
                case 3:
                    this.editDeviceAttributes();
                    break;
                case 4:
                    this.removeDevice();
                    break;
                case 5:
                    this.deactivateDevice();
                    break;
                default:
                    //no action needed
            }
        }
    }

    private void roomSelection() {
        System.out.println("Select the room where you want to add the device:");
        System.out.println(ctrl.showRoomListInString());
        selectedRoomIndex = UtilsUI.requestIntegerInInterval(1, ctrl.getRoomListSize(), "Error, room not found! Please, select a valid room.");
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);
    }

    private String deviceTypeSelection() {
        System.out.println("Select a device type:");
        System.out.println(ctrl.showDeviceTypesListInString());
        deviceTypeIndex = UtilsUI.requestIntegerInInterval(1, 14, "Error, device type not found! Please, select a device type");
        return ctrl.getDeviceTypeFromIndex(deviceTypeIndex);
    }

    private void deviceSpecsConfiguration() {

        UtilsUI.showInfo("Device configuration", "Please enter the parameters needed to configure this "+device.getDeviceType()+".");

        for (String attribute : ctrl.getDeviceAttributesListInString(device)) {
            System.out.println(attribute);
            Double newValue = UtilsUI.requestDouble("Please enter a numeric value.");
            ctrl.setAttribute(device, attribute, newValue);
        }
    }

    public void roomSelectionToAddDevice() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        roomSelection();
        String deviceType = deviceTypeSelection();
        System.out.println("Please enter the device name");
        String name = UtilsUI.requestText("Input not valid, please try again.");
        System.out.println("What is the device's nominal power (W)?");
        Double nominalPower = UtilsUI.requestDouble("Input not valid, please try again.");
        device = ctrl.createDevice(selectedRoom, deviceType, name, nominalPower);
        deviceSpecsConfiguration();
        UtilsUI.showList("Device created!", ctrl.showDeviceAttributesInString(device));


    }

    public void roomSelectionToListDevice() {
        System.out.println("Select a room from the list below to get the list of all devices in that room:");
        System.out.println(ctrl.showRoomListInString());


        selectedRoomIndex = read.nextInt();
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);

        List<String> ls = ctrl.showDeviceListInString(selectedRoomIndex);
        UtilsUI.showList("devices available in room", ls, true, 10);


    }


    //TODO: This is FUBAR.
    public void editDeviceAttributes() {
        roomSelectionToListDevice();
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);
        System.out.println("Select a device");
        deviceIndex = UtilsUI.requestInteger("Invalid selection. Please try again.");

        selectedDeviceToEdit = ctrl.getDeviceFromIndex(selectedRoomIndex, deviceIndex);


        List <String> ls = ctrl.showDeviceAttributesInString(selectedDeviceToEdit);
        UtilsUI.showList("Select a attribute to reconfigure",ls,true,5);
        UtilsUI.requestIntegerInInterval(1,ls.size(),"Please enter a value between 1 and "+ls.size());
        String deviceAttribute = ctrl.getDeviceAttribute(selectedDeviceToEdit, attributeIndex);
        System.out.println("Set the new value of " + deviceAttribute);
        double newValue = UtilsUI.requestDouble("Please enter a numeric value");
        ctrl.setAttribute(selectedDeviceToEdit, deviceAttribute, newValue);
        UtilsUI.showInfo("Success","The device's specifications were successfully updated.");

    }

    /**
     * Method that removes any device from the Rooms device list
     * First we need to select a Room in order to list all devices contained in it
     * With that input we list all devices in the room and we then select one
     */
    private void removeDevice() {
        roomSelectionToListDevice();
        System.out.println("Select a device from the previous list to remove it:");
        int deviceIndex = read.nextInt() - 1;
        read.nextLine();
        if (ctrl.removeDevice(selectedRoomIndex, deviceIndex) && deviceListInRoomIsNotEmpty()) {
            this.listDevicesInRoom();
            System.out.println("Success, the device was removed");
        } else System.out.println("Not possible to remove device");
    }

    /**
     * Method that changes the Device status flag from true to false when a device is deactivated
     */
    public void deactivateDevice() {
        roomSelectionToListDevice();
        System.out.println("Select a device from the previous list to deactivate it:");
        int deviceIndex = read.nextInt() - 1;
        read.nextLine();
        if (ctrl.deactivateDevice(selectedRoomIndex, deviceIndex) && deviceListInRoomIsNotEmpty()) {
            this.listDevicesInRoom();
            System.out.println("Success, the device was deactivated");
        } else System.out.println("Not possible to deactivate device");
    }

    public void listDevicesInRoom() {
        System.out.println("List of devices in " + selectedRoom.getName() + ":");
        System.out.println(ctrl.showDeviceListInString(selectedRoomIndex));
    }

    private boolean deviceListInRoomIsNotEmpty() {
        if (ctrl.getDeviceList(selectedRoom).getDeviceList().isEmpty()) {
            System.out.println("The device list in " + selectedRoom.getName() + " is empty.\n");
            return false;
        }
        return true;
    }

}