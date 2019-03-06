package smarthome.io.ui;

import smarthome.controller.EditDevicesCTRL;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class EditDevicesUI {
    private House mHouse;
    private RoomList mRoomList;
    private Scanner read = new Scanner(System.in);
    private int selectedRoomIndex;
    private int deviceTypeIndex;
    private String deviceType;
    private EditDevicesCTRL ctrl;
    private Room selectedRoom;
    private Device device;
    private Device selectedDeviceToEdit;
    private int deviceIndex;
    private int attributeIndex;


    public EditDevicesUI(House house) {
        mHouse = house;
        mRoomList = house.getRoomList();
        ctrl = new EditDevicesCTRL(mHouse);

    }

    public void selectOption() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int option = -1;
        while (option != 0) {

            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Add a device to a Room from the list of the available device types.");
            options.add("[2] Get a list of all Devices in a Room");
            options.add("[3] Edit the configuration of an existing device");
            options.add("[4] Remove a device");
            options.add("[5] Deactivate a device");
            options.add("[0] Exit");

            UtilsUI.showList("Device Edition", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 8, or 0 to exit the program");

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
        System.out.println("Select a room from the list below where you want to add the device:");
        System.out.println(ctrl.showRoomListInString());
        selectedRoomIndex = UtilsUI.requestIntegerInInterval(1, ctrl.getRoomListSize(), "Error, room not found! Please, select a valid room.");
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);
    }

    private void deviceTypeSelection() {
        System.out.println("Select a device type:");
        System.out.println(ctrl.showDeviceTypesListInString());
        deviceTypeIndex = UtilsUI.requestIntegerInInterval(1,14,"Error, device type not found! Please, select a device type");
        String devType = ctrl.getDeviceTypeFromIndex(deviceTypeIndex);
    }

    private void deviceSpecsConfiguration() throws IllegalAccessException {
        System.out.println("[------- Configuration of a new " + device.getDeviceType() + " -------]");
        System.out.println("[-------- Please configure the device --------]");
        System.out.println();

        for (String attribute : ctrl.getDeviceAttributesListInString(device)) {
            System.out.println(attribute);
            Double newValue = parseDouble(read.next());
            ctrl.setAttribute(device, attribute, newValue);
        }
    }

    public void roomSelectionToAddDevice() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        roomSelection();
        deviceTypeSelection();
        device = ctrl.createDevice(selectedRoom, deviceType);
        deviceSpecsConfiguration();
        System.out.println("Success! NEW DEVICE CREATED ");
        System.out.println(ctrl.showDeviceAttributesInString(device));

    }

    public void roomSelectionToListDevice() {
        System.out.println("Select a room from the list below to get the list of all devices in that room:");
        System.out.println(ctrl.showRoomListInString());
        selectedRoomIndex = read.nextInt();
        System.out.println(ctrl.showDeviceListInString(selectedRoomIndex));
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);
    }

    public void editDeviceAttributes()throws IllegalAccessException {
        roomSelectionToListDevice();
        selectedRoom = ctrl.getRoomFromListIndex(selectedRoomIndex);
        System.out.println("Select a device");
        deviceIndex = read.nextInt();
        selectedDeviceToEdit = ctrl.getDeviceFromIndex(selectedRoomIndex, deviceIndex);
        System.out.println("Select a attribute to reconfigure");
        System.out.println(ctrl.showDeviceAttributesInString(selectedDeviceToEdit));
        attributeIndex = read.nextInt();
        String deviceAttribute = ctrl.getDeviceAttribute(selectedDeviceToEdit, attributeIndex - 1);
        System.out.println("Set the new value of " + deviceAttribute);
        Double newValue = parseDouble(read.next());
        ctrl.setAttribute(selectedDeviceToEdit, deviceAttribute, newValue);
        System.out.println("Success!");
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