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
                System.out.println("Insert the volume of water (l):");
                double volumeOfWater = read.nextDouble();
                System.out.println("Insert the hot water temperature (ºC):");
                double hotWaterTemperature = read.nextDouble();
                System.out.println("Insert the cold water temperature (ºC):");
                double coldWaterTemperature = read.nextDouble();
                System.out.println("Insert the performance ratio for the Electric Water Heater:");
                double performanceRatio = read.nextDouble();
                ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(DeviceType.ELECTRIC_WATER_HEATER, volumeOfWater, hotWaterTemperature, coldWaterTemperature, performanceRatio);
                mCtrl.addDevice(mRoomIndex, mName, electricWaterHeater, mNominalPower);
                break;
            case 2:
                System.out.println("Insert the capacity(kg):");
                int capacity = read.nextInt();
                read.nextLine();
                while (true) {
                    System.out.println("Do you want to insert a program for the washing machine(y/n)?");
                    String option = read.nextLine();
                    if (option.matches("y")) {
                        System.out.println("Insert the duration for the program:");
                        int duration = read.nextInt();
                        read.nextLine();
                        System.out.println("Insert the washing machine consumption in this program:");
                        double consumption = read.nextDouble();
                        read.nextLine();
                        Program program = new Program(duration, consumption);
                        mProgramList.addProgram(program);
                    }
                    if (option.matches("n")) {
                        WashingMachine washingMachine = new WashingMachine(DeviceType.WASHING_MACHINE, capacity);
                        mCtrl.addDevice(mRoomIndex, mName, washingMachine, mNominalPower);
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
                        System.out.println("Insert the duration for the program:");
                        int duration = read.nextInt();
                        read.nextLine();
                        System.out.println("Insert the washing machine consumption in this program:");
                        double consumption = read.nextInt();
                        read.nextLine();
                        Program program = new Program(duration, consumption);
                        mProgramList.addProgram(program);
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
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
                OtherDevices others = new OtherDevices(DeviceType.values()[mDeviceTypeIndex - 1]);
                mCtrl.addDevice(mRoomIndex, mName, others, mNominalPower);
                break;
            case 13:
                System.out.println("Insert the lamp luminous flux(lm):");
                int luminousFlux = read.nextInt();
                read.nextLine();
                Lamp lamp = new Lamp(DeviceType.TV, luminousFlux);
                mCtrl.addDevice(mRoomIndex, mName, lamp, mNominalPower);
                break;
            default:
                System.out.println("Please choose a valid option.");
        }
        System.out.println("The following device was successfully created:");
        if (mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceSpecs() != null) {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getTypeFromIndex(mDeviceTypeIndex - 1));
        } else {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getType());
        }
        System.out.println("[NAME]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getName());
        System.out.println("[ROOM]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getRoom().getName());
        System.out.println("[NOMINAL POWER]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getNominalPower());

    }

    public void roomSelectionToListDevice() {

        while (true) {
            if (roomListIsEmpty()) break;
            if (mHouse.getRoomListFromHouse().getRoomList().size() != 0) {
                System.out.println("Select a room from the list below where to get the list of all devices in that room:");
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
        System.out.println("List of devices in " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getName() + ":");
        System.out.println(mCtrl.showDeviceListInString(mRoomIndex));
        return;
    }

    private boolean deviceListInRoomIsEmpty() {
        if (mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getDeviceList().isEmpty()) {
            System.out.println("The device list in " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getName() + " is empty.\n");
            return true;
        }
        return false;
    }

}
