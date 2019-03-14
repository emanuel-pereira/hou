package smarthome.controller;

import smarthome.model.*;
import smarthome.model.validations.GPSValidations;
import smarthome.model.validations.NameValidations;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class NewSensorCTRL {

    private SensorTypeList sensorTypeList;
    private GAList gaList;
    private GPSValidations gpsValidations;
    private NameValidations nameValidations;
    private House house;


    public NewSensorCTRL(House house, SensorTypeList sensorTypeList, GAList listOfGA) {
        this.house = house;
        this.sensorTypeList = sensorTypeList;
        this.gaList = listOfGA;
        this.gpsValidations = new GPSValidations();
        this.nameValidations = new NameValidations();
    }

    /**
     * Method to check if altitude inputted by the user is valid
     *
     * @param altitude Double inputted by the user to represent altitude
     * @return altitude if it is within [-12.500,8848] range, otherwise returns  to ask again for valid input
     */
    public boolean altitudeIsValid(double altitude) {
        return this.gpsValidations.altitudeIsValid(altitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param longitude Double inputted by the user to represent longitude
     * @return longitude if it is within [-180,180] range, otherwise returns  to ask again for valid input
     */
    public boolean longitudeIsValid(double longitude) {
        return this.gpsValidations.longitudeIsValid(longitude);
    }

    /**
     * Method to check if longitude inputted by the user is valid
     *
     * @param latitude Double inputted by the user to represent longitude
     * @return latitude if it is within [-90,90] range, otherwise returns  to ask again for valid input
     */
    public boolean latitudeIsValid(double latitude) {
        return this.gpsValidations.latitudeIsValid(latitude);
    }


    public boolean nameIsValid(String inputName) {
        return this.nameValidations.nameIsValid(inputName);
    }

    /**
     * @return Method that shows the list of dataTypes in a unique string
     */
    public String showSensorTypeListInString() {

        return this.sensorTypeList.showSensorTypeListInString();
    }

    /**
     * @return Method that shows the list of rooms in a unique string
     */
    public String showRoomListInStr() {
        RoomList roomList = this.house.getRoomList();
        return roomList.showRoomListInString();
    }

    public List<GeographicalArea> getGAList() {
        return this.gaList.getGAList();
    }

    /**
     * @return Method that shows the list of Geographical Areas in a unique string
     */
    public String showGAListInString() {
        return this.gaList.showGAListInString();
    }

    /**
     * index position of the List of Geographical Areas chosen by the user
     *
     * @param inputName       String variable to set the name of the sensor
     * @param startDate       Calendar parameter to set start date of the sensor
     * @param sensorTypeIndex integer declaring the index position of the sensor type in the sensor type list
     * @param indexOfGA       integer declaring the index position of the geographical area in the geographical areas list to which the sensor will be added
     * @param location        GPS coordinates inputted by the user to set the sensor location
     * @param inputUnit       String variable to set the sensor's unit of measure
     * @param readings        list of readings stored by the sensor
     * @return adds the sensor created to the selected Geographical Area
     */

    public boolean addNewSensorToGA(String id, String inputName, GregorianCalendar startDate, int sensorTypeIndex, String inputUnit, Location location, int indexOfGA, ReadingList readings) {
        GeographicalArea geographicalArea = this.gaList.get(indexOfGA);
        SensorType sensorType = this.sensorTypeList.getSensorTypeList().get(sensorTypeIndex);
        Sensor sensor = geographicalArea.getSensorListInGA().newSensor(id,inputName, startDate, location, sensorType, inputUnit, readings);
        return geographicalArea.getSensorListInGA().addSensor(sensor);
    }

    /**
     * Method that creates and adds a new sensor to a room
     *
     * @param inputName       String variable to set the name of the sensor
     * @param startDate       Calendar parameter to set start date of the sensor
     * @param sensorTypeIndex integer declaring the index position of the sensor type in the sensor type list
     * @param indexOfRoom     integer declaring the index position of the room in the room list to which the sensor will be added
     * @param unit            String variable to set the sensor's unit of measure
     * @param readingList     list of readings stored by the sensor
     * @return adds the sensor created to the selected room

     */
    public boolean addNewSensorToRoom(String inputName, GregorianCalendar startDate, int sensorTypeIndex, int indexOfRoom, String unit, ReadingList readingList) {
        RoomList roomList = this.house.getRoomList();
        SensorType sensorType = this.sensorTypeList.getSensorTypeList().get(sensorTypeIndex);
        Room room = roomList.get(indexOfRoom);
        SensorList rSensorList = room.getSensorListInRoom();
        Sensor sensor = rSensorList.createNewInternalSensor(inputName, startDate, sensorType, unit, readingList);
        return rSensorList.addSensor(sensor);
    }


    /**
     * @return the number of elements in the geographical areas list as an integer value
     */
    public int getGAListSize() {
        return this.gaList.size();
    }

    /**
     * @return the number of elements in the sensor type list as an integer value
     */
    public int getSensorTypeListSize() {
        return this.sensorTypeList.size();
    }

    /**
     * @return the number of elements in the room list as an integer value
     */
    public int getRoomListSize() {
        RoomList roomList = this.house.getRoomList();
        return roomList.getRoomListSize();
    }

    /**
     * Method to get the name of the last element in the list of sensors of the geographical area selected as parameter
     *
     * @param indexOfGA geographical area in the index position of the list of geographical areas
     * @return the name of sensor in the last position of the list of sensors of the geographical area selected as parameter
     */
    public String getGASensorType(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        SensorType type = createdSensor.getSensorType();
        return type.getType();
    }

    public String getRoomSensorType(int indexOfRoom) {
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        SensorType type = createdSensor.getSensorType();
        return type.getType();
    }


    /**
     * Method to get the name of the geographical area in the index position of the list of geographical areas
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the geographical area in the index position of the list of geographical areas
     */
    public String getGAName(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        return ga.getGAName();
    }

    /**
     * Method to get the name of the roomin the index position of the list of rooms
     *
     * @param indexOfRoom correspondent to the room in the index position of the list of rooms
     * @return the name of the room in the index position of the list of rooms
     */
    public String getRoomName(int indexOfRoom) {
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        return room.getName();
    }

    /**
     * Method to get the name of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the name of the sensor created in the selected geographical area
     */
    public String getGASensorName(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        return createdSensor.getDesignation();
    }
    /**
     * Method to get the id of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the id of the sensor created in the selected geographical area
     */
    public String getGASensorId(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        return createdSensor.getId();
    }

    /**
     * Method to get the name of the internal sensor created
     *
     * @param indexOfRoom correspondent to the room in the index position of the list of rooms
     * @return the name of the sensor created in the selected room
     */
    public String getInternalSensorName(int indexOfRoom) {
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getDesignation();
    }


    /**
     * Method to get the start date of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the start date of the sensor created in the selected geographical area
     */
    public Calendar getGASensorSDate(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        return createdSensor.getStartDate();
    }

    /**
     * Method to get the start date of the internal sensor created
     *
     * @param indexOfRoom correspondent to the room in the index position of the room list where the sensor is located
     * @return the start date of the sensor created in the selected room
     */
    public Calendar getRoomSensorSDate(int indexOfRoom) {
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getStartDate();
    }

    /**
     * Method to get the unit of the sensor created
     *
     * @param indexOfRoom correspondent to the roomin the index position of the room list
     * @return the unit of the sensor created in the selected room
     */
    public String getRoomSensorUnit(int indexOfRoom) {
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        Sensor createdSensor = room.getSensorListInRoom().getLastSensor();
        return createdSensor.getUnit();
    }

    /**
     * Method to get the unit of the sensor created
     *
     * @param indexOfGA correspondent to the geographical area in the index position of the list of geographical areas
     * @return the unit of the sensor created in the selected geographical area
     */
    public String getGASensorUnit(int indexOfGA) {
        GeographicalArea ga = this.gaList.get(indexOfGA);
        Sensor createdSensor = ga.getSensorListInGA().getLastSensor();
        return createdSensor.getUnit();
    }

    public String showSensorListInRoom (int indexOfRoom){
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        SensorList sensorList= room.getSensorListInRoom();
        return sensorList.showSensorListInString();
    }

    public int sensorListInRoomSize (int indexOfRoom){
        RoomList roomList = this.house.getRoomList();
        Room room = roomList.get(indexOfRoom);
        SensorList sensorList= room.getSensorListInRoom();
        return sensorList.size();
    }



}