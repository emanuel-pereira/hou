package smarthome.io.ui;

import smarthome.controller.EditDevicesCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class EditDevicesUI {
    private House mHouse;
    private RoomList mRoomList;
    private int mRoomIndex;
    private Room mRoom;
    private EditDevicesCTRL mCtrl;
    private String mName;
    private double mNominalPower;
    private int mDeviceTypeIndex;
    private DeviceList mDeviceList;
    private Device mDevice;
    private ProgramList mProgramList;
    private Scanner read = new Scanner(System.in);
    private int mDeviceIndex;
    int mAttributeIndex;
    String selectedAttribute;


    public EditDevicesUI(House house) {
        mCtrl = new EditDevicesCTRL(house);
        mHouse = house;
        mRoomList = house.getRoomList();
        mProgramList = new ProgramList();

    }

    public void selectOption() {
        int option = -1;
        while (option != 0) {
            System.out.println("Choose an option from the list below:");
            System.out.println("1 - Add a device to a Room from the list of the available device types.");
            System.out.println("2 - Get a list of all Devices in a Room");
            System.out.println("3 - Edit the configuration of an existing device");
            System.out.println("4 - Remove a device");
            System.out.println("5 - Deactivate a device");
            System.out.println("0 - Exit");
            option = read.nextInt();
            read.nextLine();
            switch (option) {
                case 0:
                    break;
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
                    System.out.println("Please choose a valid option.");
            }
        }
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
        if (mCtrl.removeDevice(mRoomIndex, deviceIndex) && deviceListInRoomIsNotEmpty()) {
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
        if (mCtrl.deactivateDevice(mRoomIndex, deviceIndex) && deviceListInRoomIsNotEmpty()) {
            this.listDevicesInRoom();
            System.out.println("Success, the device was deactivated");
        } else System.out.println("Not possible to deactivate device");
    }


    public void roomSelectionToAddDevice() {
        if (!roomListIsEmpty()) {
            System.out.println("Select a room from the list below where you want to add the device:");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndex = read.nextInt();
            read.nextLine();
            roomIndexIsOutOfBounds();
            this.selectDeviceType();
        }
    }

    private boolean roomListIsEmpty() {
        if (mRoomList.getRoomList().isEmpty()) {
            System.out.println("The room list is empty. Please add new rooms in US105.\n");
            return true;
        }
        return false;
    }

    private void roomIndexIsOutOfBounds() {
        if ((mRoomIndex > mRoomList.getRoomList().size()) || (mRoomIndex <= 0))
            UtilsUI.printLnInsertValidOption();
        roomSelectionToAddDevice();
    }

    public void selectDeviceType() {
        System.out.println("Choose the type of the device you want to create from the list below:");
        System.out.println(DeviceType.displayDeviceTypes());
        mDeviceTypeIndex = read.nextInt();
        read.nextLine();
        if (mDeviceTypeIndex > DeviceType.values().length) {
            UtilsUI.printLnInsertValidOption();
        } else this.insertDeviceStdInputs();
    }

    public void insertDeviceStdInputs() {
        while (true) {
            System.out.println("Insert the device name:");
            mName = read.nextLine();
            if (mCtrl.alphanumericName(mName)) {
                this.insertNominalPower();
                break;
            } else
                UtilsUI.printLnInsertValidParameter("name");
        }
    }

    public void insertNominalPower() {
        while (true) {
            System.out.println("Insert the nominal power (kW):");
            mNominalPower = read.nextDouble();
            read.nextLine();
            if (mNominalPower > 0) {
                this.insertDeviceSpecs();
                break;
            } else System.out.println("Please insert only positive values.");
        }
    }


    public void insertDeviceSpecs() {
        DeviceSpecs mDeviceSpecs;

        switch (mDeviceTypeIndex) {
            case 1:
                System.out.println("Insert the volume of water capacity (l) of the Electric Water Heater");
                int volumeOfWater = read.nextInt();
                read.nextLine();
                System.out.println("Insert the hot water temperature (ºC):");
                double hotWaterTemperature = read.nextDouble();
                System.out.println("Insert the performance ratio for the Electric Water Heater:");
                double performanceRatio = read.nextDouble();
                ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, volumeOfWater, hotWaterTemperature, performanceRatio);
                mCtrl.addDevice(mRoomIndex, mName, electricWaterHeater, mNominalPower);
                break;
            case 2:
                int capacity = insertTheCapacity("(kg)");
                while (true) {
                    System.out.println("Do you want to insert a program for the washing machine(y/n)?");
                    String option = read.nextLine();
                    if (option.matches("y")) {
                        insertNewProgram("dishwasher");
                    }
                    if (option.matches("n")) {
                        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, capacity);
                        mCtrl.addDevice(mRoomIndex, mName, washingMachine, mNominalPower);
                        break;
                    }
                }
                break;
            case 3:
                capacity = insertTheCapacity("(in dish sets)");
                while (true) {
                    System.out.println("Do you want to insert a program for the washing machine(y/n)?");
                    String option = read.nextLine();
                    if (option.matches("y")) {
                        insertNewProgram("dishwasher");
                    }
                    if (option.matches("n")) {

                        Dishwasher dishwasher = new Dishwasher(DeviceType.DISHWASHER, capacity);
                        mCtrl.addDevice(mRoomIndex, mName, dishwasher, mNominalPower);
                        break;
                    }
                }
                break;
            case 4:
                System.out.println("Insert the freezer capacity(l):");
                int freezerCapacity = read.nextInt();
                read.nextLine();
                System.out.println("Insert the refrigerator capacity(l):");
                int refrigeratorCapacity = read.nextInt();
                read.nextLine();
                System.out.println("Insert the annual Energy Consumption:");
                int annualEnergyConsumption = read.nextInt();
                read.nextLine();
                Fridge fridge = new Fridge(DeviceType.FRIDGE, freezerCapacity, refrigeratorCapacity, annualEnergyConsumption);
                mCtrl.addDevice(mRoomIndex, mName, fridge, mNominalPower);
                break;
            case 5:
                OtherDevices kettle = new OtherDevices(DeviceType.KETTLE);
                mCtrl.addDevice(mRoomIndex, mName, kettle, mNominalPower);
                break;
            case 6:
                OtherDevices oven = new OtherDevices(DeviceType.OVEN);
                mCtrl.addDevice(mRoomIndex, mName, oven, mNominalPower);
                break;
            case 7:
                OtherDevices stove = new OtherDevices(DeviceType.STOVE);
                mCtrl.addDevice(mRoomIndex, mName, stove, mNominalPower);
                break;
            case 8:
                OtherDevices microwave = new OtherDevices(DeviceType.MICROWAVE_OVEN);
                mCtrl.addDevice(mRoomIndex, mName, microwave, mNominalPower);
                break;
            case 9:
                OtherDevices wallElectricHeater = new OtherDevices(DeviceType.WALL_ELECTRIC_HEATER);
                mCtrl.addDevice(mRoomIndex, mName, wallElectricHeater, mNominalPower);
                break;
            case 10:
                OtherDevices portableElectricHeater = new OtherDevices(DeviceType.PORTABLE_ELECTRIC_OIL_HEATER);
                mCtrl.addDevice(mRoomIndex, mName, portableElectricHeater, mNominalPower);
                break;
            case 11:
                OtherDevices portableConvectionHeater = new OtherDevices(DeviceType.PORTABLE_ELECTRIC_CONVENCTION_HEATER);
                mCtrl.addDevice(mRoomIndex, mName, portableConvectionHeater, mNominalPower);
                break;
            case 12:
                OtherDevices wallTowelHeater = new OtherDevices(DeviceType.WALL_TOWEL_HEATER);
                mCtrl.addDevice(mRoomIndex, mName, wallTowelHeater, mNominalPower);
                break;
            case 13:
                System.out.println("Insert the lamp luminous flux(lm):");
                int luminousFlux = read.nextInt();
                read.nextLine();
                Lamp lamp = new Lamp(DeviceType.LAMP, luminousFlux);
                mCtrl.addDevice(mRoomIndex, mName, lamp, mNominalPower);
                break;
            case 14:
                OtherDevices otherDevices = new OtherDevices(DeviceType.TV);
                mCtrl.addDevice(mRoomIndex, mName, otherDevices, mNominalPower);
                break;
            default:
                System.out.println("Please choose a valid option.");
        }
        mRoom = mRoomList.get(mRoomIndex - 1);
        mDeviceList = mRoom.getDeviceList();
        mDevice = mDeviceList.getLastElement();
        mDeviceSpecs = mDevice.getDeviceSpecs();

        System.out.println("The following device was successfully created:");
        System.out.println("[DEVICE TYPE]: " + mDeviceSpecs.getType());
        System.out.println("[NAME]: " + mDevice.getName());
        System.out.println("[ROOM]: " + mRoom.getName());
        System.out.println("[NOMINAL POWER]: " + mDevice.getNominalPower());
    }

    private int insertTheCapacity(final String capacityType) {
        System.out.println("Insert the capacity" + capacityType + ":");
        int capacity = read.nextInt();
        read.nextLine();
        return capacity;
    }


    private void insertNewProgram(final String deviceType) {
        System.out.println("Insert a name for the program:");
        String programName = read.nextLine();
        System.out.println("Insert the " + deviceType + " consumption in this program:");
        double consumption = read.nextDouble();
        read.nextLine();
        Program program = new Program(programName, consumption);
        mProgramList.addProgram(program);
    }

    public void roomSelectionToListDevice() {
        if (!roomListIsEmpty()) {
            System.out.println("Select a room from the list below to get the list of all devices in that room:");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndex = read.nextInt();
            read.nextLine();
        }
        roomIndexIsOutOfBounds();
        mRoom = mRoomList.get(mRoomIndex - 1);
        if (deviceListInRoomIsNotEmpty())
            this.listDevicesInRoom();
    }


    public void listDevicesInRoom() {
        System.out.println("List of devices in " + mRoom.getName() + ":");
        System.out.println(mCtrl.showDeviceListInString(mRoomIndex));
    }

    private boolean deviceListInRoomIsNotEmpty() {
        mDeviceList = mRoom.getDeviceList();
        if (mDeviceList.getDeviceList().isEmpty()) {
            System.out.println("The device list in " + mRoom.getName() + " is empty.\n");
            return false;
        }
        return true;
    }

    public void deviceSelectionToEdit() {
        mDeviceIndex = read.nextInt();
        read.nextLine();
        if (mDeviceIndex > mRoom.getDeviceList().size())
            UtilsUI.printLnInsertValidOption();
    }

    public void attributeSelectionToEdit() {
        System.out.println("Select a attribute of the device to edit: ");
        System.out.println("1 - Device Room : " + this.mRoom.getName());
        System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
        mAttributeIndex = read.nextInt();
        read.nextLine();
        if (mAttributeIndex == 1)
            mCtrl.removeDeviceFromRoom(mDevice, mRoomIndex);
        selectedAttribute = mCtrl.getDeviceAttribute(mDevice, mAttributeIndex - 1);
    }

    public void editDeviceAttributes() {
        roomSelectionToListDevice();
        mRoom = mCtrl.getRoomList().get(mRoomIndex - 1);
        deviceSelectionToEdit();
        mDevice = mCtrl.getDeviceList(mRoom).get(mDeviceIndex - 1);
        attributeSelectionToEdit();
        setDeviceAttributes();
    }

    public void setDeviceAttributes() {
        String successMessage = "Success";
        String mRoomIndexToChange;
        if (mAttributeIndex == 1) {
            System.out.println("Set the new room:");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndex = read.nextInt();
            read.nextLine();
            mCtrl.addDeviceToRoom(mDevice, mRoomIndex);
            mRoom = mRoomList.get(mRoomIndex - 1);
            System.out.println(successMessage);
            System.out.println("1 - Device Room : " + this.mRoom.getName());
            System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
            return;
        }
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(0))) {
            System.out.println("Set the new name:");
            mName = read.nextLine();
            mCtrl.setAttribute(mDevice, selectedAttribute, mName);
            System.out.println(successMessage);
            System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
        }
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(1))) {
            System.out.println("Set the new room from the list of rooms: ");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndexToChange = read.nextLine();
            roomIndexIsOutOfBounds();
            mCtrl.setAttribute(mDevice, selectedAttribute, mRoomIndexToChange);
            System.out.println(successMessage);
            System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
        }
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(2))) {
            System.out.println("Set the nominal power (kW):");
            String mNominalPower = read.nextLine();//TODO same variable is started as double and then re-written as a string, we need a new method
            mCtrl.setAttribute(mDevice, selectedAttribute, mNominalPower);
            System.out.println(successMessage);
            System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
        }
        if (mDevice.getDeviceSpecs().getType().equals(DeviceType.FRIDGE)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(3))) {
                System.out.println("Set the freezer capacity:");
                String mFreezerCapacity = read.nextLine();
                mCtrl.setAttribute(mDevice, selectedAttribute, mFreezerCapacity);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(4))) {
                System.out.println("Set the new refrigerator capacity:");
                String mRefCapacity = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(mDevice, selectedAttribute, mRefCapacity);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
            }
        }
        if (mDevice.getDeviceSpecs().getType().equals(DeviceType.DISHWASHER) && selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(3))) {
            System.out.println("Set the dishwasher capacity:");
            String dwCapacity = read.nextLine(); //to validate only positive values
            mCtrl.setAttribute(mDevice, selectedAttribute, dwCapacity);
            System.out.println(successMessage);
            System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
        }
        if (mDevice.getDeviceSpecs().getType().equals(DeviceType.ELECTRIC_WATER_HEATER)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(3))) {
                System.out.println("Set the Electric Water Heater volume of water:");
                String volumeOfWater = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(mDevice, selectedAttribute, volumeOfWater);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(4))) {
                System.out.println("Set the new Electric Water Heater hot water temperature:");
                String newHotWaterTemp = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(mDevice, selectedAttribute, newHotWaterTemp);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(5))) {
                System.out.println("Set the new Electric Water Heater cold water temperature:");
                String newColdWaterTemp = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(mDevice, selectedAttribute, newColdWaterTemp);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(6))) {
                System.out.println("Set the new Electric Water Heater performance ratio:");
                String newPerformanceRatio = read.nextLine(); //to validate only positive values
                mCtrl.setAttribute(mDevice, selectedAttribute, newPerformanceRatio);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(7))) {
                System.out.println("Set the new Electric Water Heater Volume of water to heat:");
                String newPerformanceRatio = read.nextLine(); //to validate only positive values
                mCtrl.setAttribute(mDevice, selectedAttribute, newPerformanceRatio);
                System.out.println(successMessage);
                System.out.println(mCtrl.showDeviceAttributesInString(mDevice));
            }

        }
        if (mDevice.getDeviceSpecs().getType().equals(DeviceType.LAMP) && selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(mDevice).get(3))) {
            System.out.println("Set the new Lamp Luminous Flux:");
            String newLuminousFlux = read.nextLine();
            mCtrl.setAttribute(mDevice, selectedAttribute, newLuminousFlux);
            System.out.println(successMessage);
            System.out.println(mCtrl.getDeviceAttributesListInString(mDevice));
        }
    }
}
