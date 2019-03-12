package smarthome.io.ui;

import smarthome.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This entire class is only used in application demonstration and test scenarios
 * As a final application this entire class and it's call in SmartHomeUI must be removed.
 */
public final class BootStrap {
    private static int year = 2018;
    private static int month = 11;
    private static int day = 31;
    private static int hour = 0;
    private static int minutes = 0;
    private static int meteringPeriod = 15;
    private static String deviceName = "Device Name";
    private static String nominalPower = "Device Nominal Power";
    private static String waterVolumeEWH = "Volume of water capacity";
    private static String hotWaterTemperatureEWH = "Hot water temperature";
    private static String performanceRatioEWH = "Performance Ratio";
    private static String dishwasherCapacity = "Dishwasher Capacity";
    private static String washingMachineCapacity = "Washing Machine Capacity";
    private static House house;
    private BootStrap() {
    }

    /**
     * The BootStrap class main method that runs all constructive methods sequentially
     * In most cases it is not needed to write or add new calls in this method as the
     * objects such as GA, house grids, sensors, rooms, are all created in secondary
     * private methods which can be extended or duplicated.
     *
     * @param house          bootstrap house object
     * @param gaList         bootstrap List of Geographical Areas object
     * @param sensorTypeList bootstrap List of sensor Unit Types object
     * @throws IllegalAccessException exception
     * @throws InstantiationException exception
     * @throws ClassNotFoundException exception
     */
    public static void run(House house, TypeGAList typeGAList, GAList gaList, SensorTypeList sensorTypeList) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException, org.json.simple.parser.ParseException, IOException {
        BootStrap.house = house;
        createSensorsUnitTypes(sensorTypeList);
        createGeographicalAreas(gaList, typeGAList);
        GeographicalArea isep = gaList.get(0);
        createHouse(isep);

        createHouseGrid();

        createRoom(sensorTypeList);

        createDeviceTypes();
    }

    /**
     * This method is responsible for all geographical areas creation.
     * In order to add a new Geographical area one simply needs to create a new Geographical
     * object and add it to the Geographical Areas list.
     * <p>
     * The house creation method is called here, after all GA's are build.
     *
     * @param gaList bootstrap List of Geographical Areas object
     */
    private static void createGeographicalAreas(GAList gaList, TypeGAList typeGAList) throws ParseException, org.json.simple.parser.ParseException, IOException {
        TypeGA urbanArea = new TypeGA("urban area");
        typeGAList.addTypeGA(urbanArea);
        TypeGA city = new TypeGA("city");
        typeGAList.addTypeGA(city);
        DataImportUI ui = new DataImportUI(gaList);
        ui.loadJSON();
    }

    /**
     * This method is responsible for the creation of the house. This means it's address
     * and location are defined here.
     *
     * @param houseArea Geographical Area where the house is placed
     */
    private static void createHouse(GeographicalArea houseArea) {
        //set house address and it's parent GA as ISEP
        house.setHouseGA(houseArea);
        house.setHouseAddress("Rua Dr António Bernardino de Almeida", "431", "4200-072", 41.177748, -8.607745, 112);
    }

    /**
     * This method is responsible for the creation of the Sensor Unit Types, examples are:
     * - rainfall, humidity, wind speed, temperature
     *
     * @param sensorTypeList SensorTypeList global object which represents the ArrayList
     *                       containing all Sensor Unit Types
     */
    private static void createSensorsUnitTypes(SensorTypeList sensorTypeList) {
        //set Sensor Types
        SensorType sT1 = sensorTypeList.newSensorType("rainfall");
        sensorTypeList.addSensorType(sT1);
        SensorType sT2 = sensorTypeList.newSensorType("humidity");
        sensorTypeList.addSensorType(sT2);
        SensorType sT3 = sensorTypeList.newSensorType("temperature");
        sensorTypeList.addSensorType(sT3);
    }

    /**
     * This method is responsible for the house grids creation, and for adding it to electric
     * house grids list present in the house object
     */
    private static void createHouseGrid() {
        HouseGrid mainGrid = house.getHGListInHouse().newHouseGrid("Main Grid");
        house.getHGListInHouse().addHouseGrid(mainGrid);
    }

    /**
     * This method is responsible for the house rooms creation
     *
     * @param sensorTypeList is required to allow the devices creation as this attribute is passed on
     *                       to the consequent methods, createDevicesInRoomXXX
     * @throws IllegalAccessException exception
     * @throws ClassNotFoundException exception
     * @throws InstantiationException exception
     */
    private static void createRoom(SensorTypeList sensorTypeList) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        HouseGrid mainGrid = house.getHGListInHouse().get(0);
        List<DeviceType> deviceTypeList = house.getListOfDeviceTypes();

        Room b106 = house.getRoomList().createNewRoom("B106", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(b106);
        mainGrid.attachRoomToGrid(b106);
        createDevicesInRoomB106(deviceTypeList);

        Room b107 = house.getRoomList().createNewRoom("B107", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(b107);
        mainGrid.attachRoomToGrid(b107);
        createDevicesInRoomB107(deviceTypeList);

        Room b109 = house.getRoomList().createNewRoom("B109", 1, 7, 11, 3.5);
        house.getRoomList().addRoom(b109);
        mainGrid.attachRoomToGrid(b109);
        createDevicesInRoomB109(deviceTypeList);
        addReadingsRoomB109SensorTemp(sensorTypeList);
        addReadingsRoomB109SensorHum(sensorTypeList);
    }

    /**
     * This method is responsible for the cration of Device Types
     * This is a temporary method as in future these types will come from a dynamic configuration file.
     */
    private static void createDeviceTypes() {
        String ehw = "ElectricWaterHeater";
        String dishwasher = "Dishwasher";
        String washingMachine = "WashingMachine";
        String[] deviceTypesList = new String[]{ehw, dishwasher, washingMachine};

        for (String newTypeString : deviceTypesList
        ) {
            DeviceType newType = new DeviceType(newTypeString);
            house.getListOfDeviceTypes().add(newType);
        }
    }

    /**
     * This method is responsible for the device creation and consequent addition to a Room
     * All measured values of energy consumption, in case of existing, are also inserted here.
     *
     * @param deviceTypeList List object containing all device types
     * @throws InstantiationException exception
     * @throws IllegalAccessException exception
     * @throws ClassNotFoundException exception
     */
    private static void createDevicesInRoomB106(List<DeviceType> deviceTypeList) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Room b106 = house.getRoomList().get(0);
        DeviceList deviceListB106 = b106.getDeviceList();

        Device eHWB106 = deviceListB106.newDeviceV2(deviceTypeList.get(0));
        deviceListB106.addDevice(eHWB106);
        eHWB106.setAttributeValue(deviceName, "EHW B106");
        eHWB106.setAttributeValue(nominalPower, "2.2");
        eHWB106.setAttributeValue(waterVolumeEWH, "150");
        eHWB106.setAttributeValue(hotWaterTemperatureEWH, "55");
        eHWB106.setAttributeValue(performanceRatioEWH, "0.92");
        eHWB106.setIsMetered(true);

        Device dishwasherB106 = deviceListB106.newDeviceV2(deviceTypeList.get(1));
        deviceListB106.addDevice(dishwasherB106);
        dishwasherB106.setAttributeValue(deviceName, "Dishwasher B106");
        dishwasherB106.setAttributeValue(nominalPower, "1.4");
        dishwasherB106.setAttributeValue(dishwasherCapacity, "100");
        dishwasherB106.setIsMetered(true);
    }

    /**
     * This method is responsible for the device creation and consequent addition to a Room
     * All measured values of energy consumption, in case of existing, are also inserted here.
     *
     * @param deviceTypeList List object containing all device types
     * @throws InstantiationException exception
     * @throws IllegalAccessException exception
     * @throws ClassNotFoundException exception
     */
    private static void createDevicesInRoomB107(List<DeviceType> deviceTypeList) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Room b107 = house.getRoomList().get(1);
        DeviceList deviceListB107 = b107.getDeviceList();

        Device eHWB107 = deviceListB107.newDeviceV2(deviceTypeList.get(0));
        deviceListB107.addDevice(eHWB107);
        eHWB107.setAttributeValue(deviceName, "EHW B107");
        eHWB107.setAttributeValue(nominalPower, "2.2");
        eHWB107.setAttributeValue(waterVolumeEWH, "150");
        eHWB107.setAttributeValue(hotWaterTemperatureEWH, "55");
        eHWB107.setAttributeValue(performanceRatioEWH, "0.92");

        Device dishwasherB107 = deviceListB107.newDeviceV2(deviceTypeList.get(1));
        deviceListB107.addDevice(dishwasherB107);
        dishwasherB107.setAttributeValue(deviceName, "Dishwasher B107");
        dishwasherB107.setAttributeValue(nominalPower, "1.5");
        dishwasherB107.setAttributeValue(dishwasherCapacity, "100");

        Device washingMachineB107 = deviceListB107.newDeviceV2(deviceTypeList.get(2));
        deviceListB107.addDevice(washingMachineB107);
        washingMachineB107.setAttributeValue(deviceName, "Washing Machine B107");
        washingMachineB107.setAttributeValue(nominalPower, "2.5");
        washingMachineB107.setAttributeValue(washingMachineCapacity, "100");

        //define EHW B107 metered consumption
        double[] valuesEHWB107 = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.250, 0.000, 0.000, 0.000, 0.000, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.200, 0.375, 0.375, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.100, 0.375, 0.375, 0.375, 0.150, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesEHWB107) {
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
        eHWB107.setIsMetered(true);
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;

        //define dishwasher B107 metered consumption
        double[] valuesDishwasherB107 = new double[]{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.500, 0.500, 0.500, 0.200, 0.300, 0.200, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.200, 0.000, 0.000, 0.000, 0.100, 0.100, 0.375, 0.375, 0.200, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000,};
        for (
                double i : valuesDishwasherB107) {
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
        dishwasherB107.setIsMetered(true);
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;

        //define ashingMachine B107 metered consumption
        double[] valuesWashingMachineB107 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.40, 0.20, 0.25, 0.25, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,};
        for (
                double i : valuesWashingMachineB107) {
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
        washingMachineB107.setIsMetered(true);
        year = 2018;
        month = 11;
        day = 31;
        hour = 0;
        minutes = 0;
    }

    /**
     * This method is responsible for the device creation and consequent addition to a Room
     * All measured values of energy consumption, in case of existing, are also inserted here.
     *
     * @param deviceTypeList List object containing all device types
     * @throws InstantiationException exception
     * @throws IllegalAccessException exception
     * @throws ClassNotFoundException exception
     */
    private static void createDevicesInRoomB109(List<DeviceType> deviceTypeList) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Room b109 = house.getRoomList().get(2);
        DeviceList deviceListB109 = b109.getDeviceList();

        Device eHWB109 = deviceListB109.newDeviceV2(deviceTypeList.get(0));
        deviceListB109.addDevice(eHWB109);
        eHWB109.setAttributeValue(deviceName, "EHW B109");
        eHWB109.setAttributeValue(nominalPower, "1.5");
        eHWB109.setAttributeValue(waterVolumeEWH, "100");
        eHWB109.setAttributeValue(hotWaterTemperatureEWH, "55");
        eHWB109.setAttributeValue(performanceRatioEWH, "0.91");

        Device dishwasherB109 = deviceListB109.newDeviceV2(deviceTypeList.get(1));
        deviceListB109.addDevice(dishwasherB109);
        dishwasherB109.setAttributeValue(deviceName, "Dishwasher B109");
        dishwasherB109.setAttributeValue(nominalPower, "1.5");
        dishwasherB109.setAttributeValue(dishwasherCapacity, "100");
        //No energy consumption values were measured
        dishwasherB109.setIsMetered(false);

        Device washingMachineB109 = deviceListB109.newDeviceV2(deviceTypeList.get(2));
        deviceListB109.addDevice(washingMachineB109);
        washingMachineB109.setAttributeValue(deviceName, "Washing Machine B109");
        washingMachineB109.setAttributeValue(nominalPower, "2.5");
        washingMachineB109.setAttributeValue(washingMachineCapacity, "100");


        double[] valuesEHWB109 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.20, 0.50, 0.50, 0.50, 0.50, 0.25, 0.00, 0.00, 0.00, 0.00, 0.20, 0.20, 0.00, 0.00, 0.00, 0.00, 0.20, 0.50, 0.50, 0.50, 0.50, 0.20, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.10, 0.50, 0.50, 0.50, 0.15, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00};
        for (
                double i : valuesEHWB109) {
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
        eHWB109.setIsMetered(true);

        double[] valueswashingMachineB109 = new double[]{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.40, 0.20, 0.20, 0.25, 0.25, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00,};
        for (
                double i : valueswashingMachineB109) {
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
        washingMachineB109.setIsMetered(true);
    }

    /**
     * This method is responsible for the indoors Temperature Sensor creation and consequent addition
     * to a Room X.
     *
     * @param sensorTypeList List of sensor available unit/data types
     */
    private static void addReadingsRoomB109SensorTemp(SensorTypeList sensorTypeList) {
        Room b109 = house.getRoomList().get(2);
        SensorList b109SensorList = b109.getSensorListInRoom();

        ReadingList rlTempB109 = addReadingsTempRoomB109();

        GregorianCalendar b109TempSensorStartDate = new GregorianCalendar(2018, 9, 15);
        SensorType sT3 = sensorTypeList.getSensorTypeList().get(2);
        Sensor b109TempSensor = b109SensorList.createNewInternalSensor("Temperature B109", b109TempSensorStartDate, sT3, "ºC", rlTempB109);

        b109SensorList.addSensor(b109TempSensor);
    }

    /**
     * This method is responsible readings creation for a specific Room Sensor
     *
     * @return The Reading List containing all Reading entries, paring a value with it's
     * timestamp (in for of a Gregorian Calendar object)
     */
    private static ReadingList addReadingsTempRoomB109() {
        ReadingList rlTempB109 = new ReadingList();

        Reading r1TempB109 = new Reading(14.0, new GregorianCalendar(2018, 11, 30, 2, 0));
        Reading r2TempB109 = new Reading(13.7, new GregorianCalendar(2018, 11, 30, 8, 0));
        Reading r3TempB109 = new Reading(16.5, new GregorianCalendar(2018, 11, 30, 14, 0));
        Reading r4TempB109 = new Reading(15.1, new GregorianCalendar(2018, 11, 30, 20, 0));
        Reading r5TempB109 = new Reading(13.8, new GregorianCalendar(2018, 11, 31, 2, 0));
        Reading r6TempB109 = new Reading(13.3, new GregorianCalendar(2018, 11, 31, 8, 0));
        Reading r7TempB109 = new Reading(15.5, new GregorianCalendar(2018, 11, 31, 14, 0));
        Reading r8TempB109 = new Reading(14.2, new GregorianCalendar(2018, 11, 31, 20, 0));
        Reading r9TempB109 = new Reading(12.5, new GregorianCalendar(2019, 1, 1, 2, 0));
        Reading r10TempB109 = new Reading(12.4, new GregorianCalendar(2019, 1, 1, 8, 0));
        Reading r11TempB109 = new Reading(13.8, new GregorianCalendar(2019, 1, 1, 14, 0));
        Reading r12TempB109 = new Reading(12.9, new GregorianCalendar(2019, 1, 1, 20, 0));
        Reading r13TempB109 = new Reading(11.5, new GregorianCalendar(2019, 1, 2, 2, 0));
        Reading r14TempB109 = new Reading(11.2, new GregorianCalendar(2019, 1, 2, 8, 0));
        Reading r15TempB109 = new Reading(13.5, new GregorianCalendar(2019, 1, 2, 14, 0));
        Reading r16TempB109 = new Reading(12.8, new GregorianCalendar(2019, 1, 2, 20, 0));


        rlTempB109.addReading(r1TempB109);
        rlTempB109.addReading(r2TempB109);
        rlTempB109.addReading(r3TempB109);
        rlTempB109.addReading(r4TempB109);
        rlTempB109.addReading(r5TempB109);
        rlTempB109.addReading(r6TempB109);
        rlTempB109.addReading(r7TempB109);
        rlTempB109.addReading(r8TempB109);
        rlTempB109.addReading(r9TempB109);
        rlTempB109.addReading(r10TempB109);
        rlTempB109.addReading(r11TempB109);
        rlTempB109.addReading(r12TempB109);
        rlTempB109.addReading(r13TempB109);
        rlTempB109.addReading(r14TempB109);
        rlTempB109.addReading(r15TempB109);
        rlTempB109.addReading(r16TempB109);

        return rlTempB109;
    }

    /**
     * This method is responsible for the indoors Humidity sensor creation and consequent addition
     * to a Room X.
     *
     * @param sensorTypeList List of sensor available unit/data types
     */
    private static void addReadingsRoomB109SensorHum(SensorTypeList sensorTypeList) {
        Room b109 = house.getRoomList().get(2);
        SensorList b109SensorList = b109.getSensorListInRoom();

        ReadingList rlHumB109 = addReadingsHumRoomB109();

        GregorianCalendar b109HumSensorStartDate = new GregorianCalendar(2018, 10, 22);
        SensorType sT2 = sensorTypeList.getSensorTypeList().get(1);
        Sensor b109HumSensor = b109SensorList.createNewInternalSensor("Humidity B109", b109HumSensorStartDate, sT2, "%", rlHumB109);

        b109SensorList.addSensor(b109HumSensor);
    }

    /**
     * This method is responsible readings creation for a specific Room Sensor
     *
     * @return The Reading List containing all Reading entries, paring a value with it's
     * timestamp (in for of a Gregorian Calendar object)
     */
    private static ReadingList addReadingsHumRoomB109() {
        ReadingList rlHumB109 = new ReadingList();

        Reading r1HumB109 = new Reading(84.0, new GregorianCalendar(2018, 11, 30, 2, 0));
        Reading r2HumB109 = new Reading(85.7, new GregorianCalendar(2018, 11, 30, 8, 0));
        Reading r3HumB109 = new Reading(76.5, new GregorianCalendar(2018, 11, 30, 14, 0));
        Reading r4HumB109 = new Reading(78.1, new GregorianCalendar(2018, 11, 30, 20, 0));
        Reading r5HumB109 = new Reading(83.8, new GregorianCalendar(2018, 11, 31, 2, 0));
        Reading r6HumB109 = new Reading(83.3, new GregorianCalendar(2018, 11, 31, 8, 0));
        Reading r7HumB109 = new Reading(75.5, new GregorianCalendar(2018, 11, 31, 14, 0));
        Reading r8HumB109 = new Reading(77.2, new GregorianCalendar(2018, 11, 31, 20, 0));
        Reading r9HumB109 = new Reading(82.5, new GregorianCalendar(2019, 1, 1, 2, 0));
        Reading r10HumB109 = new Reading(82.4, new GregorianCalendar(2019, 1, 1, 8, 0));
        Reading r11HumB109 = new Reading(73.8, new GregorianCalendar(2019, 1, 1, 14, 0));
        Reading r12HumB109 = new Reading(72.9, new GregorianCalendar(2019, 1, 1, 20, 0));
        Reading r13HumB109 = new Reading(80.5, new GregorianCalendar(2019, 1, 2, 2, 0));
        Reading r14HumB109 = new Reading(79.2, new GregorianCalendar(2019, 1, 2, 8, 0));
        Reading r15HumB109 = new Reading(71.5, new GregorianCalendar(2019, 1, 2, 14, 0));
        Reading r16HumB109 = new Reading(72.8, new GregorianCalendar(2019, 1, 2, 20, 0));

        rlHumB109.addReading(r1HumB109);
        rlHumB109.addReading(r2HumB109);
        rlHumB109.addReading(r3HumB109);
        rlHumB109.addReading(r4HumB109);
        rlHumB109.addReading(r5HumB109);
        rlHumB109.addReading(r6HumB109);
        rlHumB109.addReading(r7HumB109);
        rlHumB109.addReading(r8HumB109);
        rlHumB109.addReading(r9HumB109);
        rlHumB109.addReading(r10HumB109);
        rlHumB109.addReading(r11HumB109);
        rlHumB109.addReading(r12HumB109);
        rlHumB109.addReading(r13HumB109);
        rlHumB109.addReading(r14HumB109);
        rlHumB109.addReading(r15HumB109);
        rlHumB109.addReading(r16HumB109);

        return rlHumB109;
    }

}
