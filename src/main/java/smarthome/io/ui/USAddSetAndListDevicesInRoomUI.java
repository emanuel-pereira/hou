package smarthome.io.ui;

import smarthome.controller.USAddSetAndListDevicesInRoomCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class USAddSetAndListDevicesInRoomUI {
    private House mHouse;
    private USAddSetAndListDevicesInRoomCTRL mCtrl;
    ProgramList mProgramList;
    Scanner read = new Scanner(System.in);

    public USAddSetAndListDevicesInRoomUI(House house) {
        mCtrl = new USAddSetAndListDevicesInRoomCTRL(house);
        mHouse = house;
        mProgramList = new ProgramList();
    }

    //Generic attributes of any device regardless of its type:
    private int mRoomIndex;
    private String mName;
    private double mNominalPower;
    private int mDeviceTypeIndex;
    private String insertValidOption = "Please insert a valid option \n.";
    private int mDeviceIndex;
    private Room selectedRoom;
    Device selectedDevice;
    int mAttributeIndex;
    private String mRoomIndexToChange;
    String selectedAttribute;


    public void selectOption() {
        int option = -1;
        while (option != 0) {
            System.out.println("Choose an option from the list below:");
            System.out.println("1 - Add a device to a Room from the list of the available device types.");
            System.out.println("2 - Get a list of all Devices in a Room");
            System.out.println("3 - Edit the configuration of an existing device");
            System.out.println("0 - Exit");
            option = read.nextInt();
            read.nextLine();
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
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

    public void roomSelectionToAddDevice() {

        while (true) {
            if (roomListIsEmpty()) break;
            System.out.println("Select a room from the list below where you want to add the device:");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndex = read.nextInt();
            read.nextLine();
            roomIndexIsOutOfBounds();
            this.selectDeviceType();
            break;
        }
        return;
    }

    private boolean roomListIsEmpty() {
        if (mHouse.getRoomListFromHouse().getRoomList().isEmpty()) {
            System.out.println("The room list is empty. Please add new rooms in US105.\n");
            return true;
        }
        return false;
    }

    private void roomIndexIsOutOfBounds() {
        if (mRoomIndex > mHouse.getRoomListFromHouse().getRoomList().size())
            System.out.println(insertValidOption);
    }

    public void selectDeviceType() {
        while (true) {
            System.out.println("Choose the type of the device you want to create from the list below:");
            System.out.println(DeviceType.displayDeviceTypes());
            mDeviceTypeIndex = read.nextInt();
            read.nextLine();
            if (mDeviceTypeIndex > DeviceType.values().length) {
                System.out.println(insertValidOption);
            } else this.insertDeviceStdInputs();
            break;
        }
        return;
    }

    public void insertDeviceStdInputs() {
        while (true) {
            System.out.println("Insert the device name:");
            mName = read.nextLine();
            mName = mCtrl.alphanumericName(mName);
            if (mName != null) {
                this.insertNominalPower();
                break;
            } else
                System.out.println("Please insert a valid name");
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
        return;
    }


    public void insertDeviceSpecs() {

        switch (mDeviceTypeIndex) {
            case 1:
                System.out.println("Insert the volume of water capacity (l) of the Electric Water Heater");
                int volumeOfWater = read.nextInt();
                read.nextLine();
                System.out.println("Insert the hot water temperature (ºC):");
                double hotWaterTemperature = read.nextDouble();
                System.out.println("Insert the performance ratio for the Electric Water Heater:");
                double performanceRatio = read.nextDouble();
                ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(volumeOfWater, hotWaterTemperature, performanceRatio);
                mCtrl.addDevice(mRoomIndex, mName, electricWaterHeater, mNominalPower, DeviceType.ELECTRIC_WATER_HEATER);
                break;
            case 2:
                System.out.println("Insert the capacity(kg):");
                int capacity = read.nextInt();
                read.nextLine();
                while (true) {
                    System.out.println("Do you want to insert a program for the washing machine(y/n)?");
                    String option = read.nextLine();
                    if (option.matches("y")) {
                        System.out.println("Insert a name for the program:");
                        String programName = read.nextLine();
                        System.out.println("Insert the washing machine consumption in this program:");
                        double consumption = read.nextDouble();
                        read.nextLine();
                        Program program = new Program(programName, consumption);
                        mProgramList.addProgram(program);
                    }
                    if (option.matches("n")) {
                        WashingMachine washingMachine = new WashingMachine(capacity);
                        mCtrl.addDevice(mRoomIndex, mName, washingMachine, mNominalPower, DeviceType.WASHING_MACHINE);
                        break;
                    }
                }
                break;
            case 3:
                System.out.println("Insert the capacity(in dish sets):");
                capacity = read.nextInt();
                read.nextLine();
                while (true) {
                    System.out.println("Do you want to insert a program for the washing machine(y/n)?");
                    String option = read.nextLine();

                    if (option.matches("y")) {
                        System.out.println("Insert a name for the program:");
                        String programName = read.nextLine();
                        System.out.println("Insert the washing machine consumption in this program:");
                        double consumption = read.nextInt();
                        read.nextLine();
                        Program program = new Program(programName, consumption);
                        mProgramList.addProgram(program);
                    }
                    if (option.matches("n")) {

                        Dishwasher dishwasher = new Dishwasher(capacity);
                        mCtrl.addDevice(mRoomIndex, mName, dishwasher, mNominalPower, DeviceType.DISHWASHER);
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
                Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption);
                mCtrl.addDevice(mRoomIndex, mName, fridge, mNominalPower, DeviceType.FRIDGE);
                break;
            case 5:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.KETTLER, mNominalPower);
                break;
            case 6:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.OVEN, mNominalPower);
                break;
            case 7:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.STOVE, mNominalPower);
                break;
            case 8:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.MICROWAVE_OVEN, mNominalPower);
                break;
            case 9:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.WALL_ELECTRIC_HEATER, mNominalPower);
                break;
            case 10:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.PORTABLE_ELECTRIC_OIL_HEATER, mNominalPower);
                break;
            case 11:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.PORTABLE_ELECTRIC_CONVENCTION_HEATER, mNominalPower);
                break;
            case 12:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.WALL_TOWEL_HEATER, mNominalPower);
                break;
            case 13:
                System.out.println("Insert the lamp luminous flux(lm):");
                int luminousFlux = read.nextInt();
                read.nextLine();
                Lamp lamp = new Lamp(luminousFlux);
                mCtrl.addDevice(mRoomIndex, mName, lamp, mNominalPower, DeviceType.LAMP);
                break;
            case 14:
                mCtrl.addDeviceWithoutSpecsToRoom(mRoomIndex, mName, DeviceType.TV, mNominalPower);
                break;
            default:
                System.out.println("Please choose a valid option.");
        }
        System.out.println("The following device was successfully created:");
        if (mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceSpecs() != null) {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceType().getTypeString());
        } else {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceType());
        }
        System.out.println("[NAME]: " + mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getName());
        System.out.println("[ROOM]: " + mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getRoom().getName());
        System.out.println("[NOMINAL POWER]: " + mHouse.getRoomListFromHouse().getRoomList().get(mRoomIndex - 1).getDeviceList().getLastElement().getNominalPower());

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

        while (true) {
            if (roomListIsEmpty()) break;
            if (mHouse.getRoomListFromHouse().getRoomList().size() != 0) {
                System.out.println("Select a room from the list below where to getRoomWithIndex the list of all devices in that room:");
                System.out.println(mCtrl.showRoomListInString());
                mRoomIndex = read.nextInt();
                read.nextLine();
                if (deviceListInRoomIsEmpty()) break;
                roomIndexIsOutOfBounds();
                this.listDevicesInRoom();
                break;
            }
            break;
        }
        return;
    }

    public void listDevicesInRoom() {
        System.out.println("List of devices in " + mHouse.getRoomListFromHouse().getRoomWithIndex(mRoomIndex - 1).getName() + ":");
        System.out.println(mCtrl.showDeviceListInString(mRoomIndex));
        return;
    }

    private boolean deviceListInRoomIsEmpty() {
        if (mHouse.getRoomListFromHouse().getRoomWithIndex(mRoomIndex - 1).getDeviceList().getDeviceList().isEmpty()) {
            System.out.println("The device list in " + mHouse.getRoomListFromHouse().getRoomWithIndex(mRoomIndex - 1).getName() + " is empty.\n");
            return true;
        }
        return false;
    }
//

    public void deviceSelectionToEdit() {
        while (true) {
            mDeviceIndex = read.nextInt();
            read.nextLine();
            if (mDeviceIndex > mCtrl.getDeviceList(selectedRoom).getDeviceList().size()) {
                System.out.println(insertValidOption);
                break;
            }
            return;
        }
    }

    public void attributeSelectionToEdit() {
        while (true) {
            System.out.println("Select a attribute of the device to edit: ");
            System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
            mAttributeIndex = read.nextInt();
            read.nextLine();
            selectedAttribute = mCtrl.getDeviceAttribute(selectedDevice, mAttributeIndex - 1);
            //if (mAttributeIndex > mCtrl.)
            //  System.out.println(insertValidOption);
            break;
        }
        return;
    }


    public void editDeviceAttributes() {
        roomSelectionToListDevice();
        selectedRoom = mCtrl.getRoomList().getRoomWithIndex(mRoomIndex - 1);
        deviceSelectionToEdit();
        selectedDevice = mCtrl.getDeviceList(selectedRoom).get(mDeviceIndex - 1);
        attributeSelectionToEdit();
        setDeviceAttributes();
    }

    public void setDeviceAttributes() {
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(0))) {
            System.out.println("Set the new name:");
            mName = read.nextLine();
            mCtrl.setAttribute(selectedDevice, selectedAttribute, mName);
            System.out.println("Success");
            System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
        }
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(1))) {
            System.out.println("Set the new room from the list of rooms: ");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndexToChange = read.nextLine();
            roomIndexIsOutOfBounds();
            mCtrl.setAttribute(selectedDevice, selectedAttribute, mRoomIndexToChange);
            System.out.println("Success");
            System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
        }
        if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(2))) {
            System.out.println("Set the nominal power (kW):");
            String mNominalPower = read.nextLine();
            mCtrl.setAttribute(selectedDevice, selectedAttribute, mNominalPower);
            System.out.println("Success");
            System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
        }
        if (selectedDevice.getDeviceType().equals(DeviceType.FRIDGE)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(3))) {
                System.out.println("Set the freezer capacity:");
                String mFreezerCapacity = read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, mFreezerCapacity);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(4))) {
                System.out.println("Set the new refrigerator capacity:");
                String mRefCapacity = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, mRefCapacity);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
            }
        }
        if (selectedDevice.getDeviceType().equals(DeviceType.DISHWASHER)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(3))) {
                System.out.println("Set the dishwasher capacity:");
                String dwCapacity = read.nextLine(); //to validate only positive values
                mCtrl.setAttribute(selectedDevice, selectedAttribute, dwCapacity);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
            }
        }
        if (selectedDevice.getDeviceType().equals(DeviceType.ELECTRIC_WATER_HEATER)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(3))) {
                System.out.println("Set the Electric Water Heater volume of water:");
                String volumeOfWater = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, volumeOfWater);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(4))) {
                System.out.println("Set the new Electric Water Heater hot water temperature:");
                String newHotWaterTemp = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, newHotWaterTemp);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(5))) {
                System.out.println("Set the new Electric Water Heater cold water temperature:");
                String newColdWaterTemp = read.nextLine(); //to validate only positive values
                read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, newColdWaterTemp);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(6))) {
                System.out.println("Set the new Electric Water Heater performance ratio:");
                String newPerformanceRatio = read.nextLine(); //to validate only positive values
                mCtrl.setAttribute(selectedDevice, selectedAttribute, newPerformanceRatio);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));

            }
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(7))) {
                System.out.println("Set the new Electric Water Heater Volume of water to heat:");
                String newPerformanceRatio = read.nextLine(); //to validate only positive values
                mCtrl.setAttribute(selectedDevice, selectedAttribute, newPerformanceRatio);
                System.out.println("Success");
                System.out.println(mCtrl.showDeviceAttributesInString(selectedDevice));
            }

        }
        if (selectedDevice.getDeviceType().equals(DeviceType.LAMP)) {
            if (selectedAttribute.equals(mCtrl.getDeviceAttributesListInString(selectedDevice).get(3))) {
                System.out.println("Set the new Lamp Luminous Flux:");
                String newLuminousFlux = read.nextLine();
                mCtrl.setAttribute(selectedDevice, selectedAttribute, newLuminousFlux);
                System.out.println("Success");
                System.out.println(mCtrl.getDeviceAttributesListInString(selectedDevice));
            }
        }
        return;
    }

}