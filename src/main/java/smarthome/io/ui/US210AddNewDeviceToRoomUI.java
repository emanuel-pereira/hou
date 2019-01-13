package smarthome.io.ui;

import smarthome.controller.US210AddNewDeviceToRoomCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class US210AddNewDeviceToRoomUI {
    private House mHouse;
    private US210AddNewDeviceToRoomCTRL mCtrl;
    ProgramList mProgramList;
    Scanner read = new Scanner(System.in);
/*
    1 - Listar Rooms ao user para selecionar o room onde o device deve ser adicionado
    2 - User seleciona room
    3 - Questiona atributos genérios de qualquer device (nome e nominal power)
    4 - Lista tipos de devices ao user (user seleciona um tipo de device)
    5 - Questiona device specs associadas a esse device

Dúvidas:
    1) testar throw exceptions (ex: classe GPSValidations)
    2) DeviceSpecs
    4) Verificar otimização de UI de fluxo de UIs (Exceto validações a consolidar nas férias em classes de validações próprias)

*/

    public US210AddNewDeviceToRoomUI(House house) {
        mCtrl = new US210AddNewDeviceToRoomCTRL(house);
        mHouse = house;
        mProgramList = new ProgramList();
    }

    //Generic attributes of any device regardless of its type:
    int mRoomIndex;
    String mName;
    double mNominalPower;
    int mDeviceTypeIndex;
    String insertValidOption = "Please insert a valid option \n.";

    public void roomSelectionToAddDevice() {

        while (true) {
            System.out.println("Select a room from the list below where you want to add the device:");
            System.out.println(mCtrl.showRoomListInString());
            mRoomIndex = read.nextInt();
            read.nextLine();
            if (mRoomIndex > mHouse.getRoomListFromHouse().getRoomList().size())
                System.out.println(insertValidOption);
            this.selectDeviceType();
            break;
        }
        return;
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
        System.out.println("Insert the device name:");
        mName = nameIsValid();
        System.out.println("Insert the nominal power (kW):");
        mNominalPower = read.nextDouble(); //to validate only positive values
        read.nextLine();
        this.insertDeviceSpecs();
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
                mCtrl.addDeviceWithSpecsToRoom(mRoomIndex, mName, electricWaterHeater, mNominalPower);
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
                        mCtrl.addDeviceWithSpecsToRoom(mRoomIndex, mName, washingMachine, mNominalPower);
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
                        mCtrl.addDeviceWithSpecsToRoom(mRoomIndex, mName, dishwasher, mNominalPower);
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
                mCtrl.addDeviceWithSpecsToRoom(mRoomIndex, mName, fridge, mNominalPower);
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
                String deviceType = DeviceType.values()[mDeviceTypeIndex - 1].getType();
                mCtrl.addDeviceWithoutSpecsToRoom(mName, mRoomIndex, deviceType, mNominalPower);
                break;
            case 13:
                System.out.println("Insert the lamp luminous flux(lm):");
                int luminousFlux = read.nextInt();
                read.nextLine();
                Lamp lamp = new Lamp(DeviceType.TV, luminousFlux);
                mCtrl.addDeviceWithSpecsToRoom(mRoomIndex, mName, lamp, mNominalPower);
                break;
            default:
                System.out.println("Please choose a valid option.");
        }
        System.out.println("The following device was successfully created:");
        if (mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceSpecs() != null) {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getTypeFromIndex(mDeviceTypeIndex - 1));
        } else {
            System.out.println("[DEVICE TYPE]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getDeviceType());
        }
        System.out.println("[NAME]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getName());
        System.out.println("[ROOM]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getRoom().getName());
        System.out.println("[NOMINAL POWER]: " + mHouse.getRoomListFromHouse().get(mRoomIndex - 1).getDeviceList().getLastElement().getNominalPower());

    }

    private String nameIsValid() {
        String name = read.nextLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Empty spaces are not accepted.");
            return null;
        }
        if (!name.matches("^[A-Za-z -]+$")) { //accepts alphanumeric characters, spaces
            System.out.println("Please insert only alphabetic characters with spaces or hyphens.");
            return null;
        }
        return name;
    }


}
