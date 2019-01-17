package smarthome.controller;

import smarthome.model.*;

public class USAddSetAndListDevicesInRoomCTRL {

    private House mHouse;
    private NameValidations mNameValidations;


    public USAddSetAndListDevicesInRoomCTRL(House house) {
        mHouse=house;
        mNameValidations = new NameValidations();
    }

    public String showRoomListInString() {
        return mHouse.getRoomListFromHouse().showRoomListInString();
    }

    public String showDeviceListInString(int indexOfRoom){
        return mHouse.getRoomListFromHouse().getRoomList().get(indexOfRoom-1).getDeviceList().showDeviceListInString();
    }

    public boolean addDevice(int indexOfRoom, String inputName, DeviceSpecs deviceSpecs, double nominalPower, DeviceType deviceType) {
        Device device = mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().newDevice(inputName,deviceType,deviceSpecs,mHouse.getRoomListFromHouse().get(indexOfRoom-1),nominalPower);
        return mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().addDevice(device);
    }

    /**Method to validate Strings only accepting alphanumeric characters as well as spaces and hyphens.
     * @param inputName valid name
     * @return name if if is
     */
    public String alphanumericName(String inputName){
        return mNameValidations.alphanumericName(inputName);
    }
    public DeviceList getDeviceList(Room room) {
        return room.getDeviceList();
    }

    public RoomList getRoomList() {
        return mHouse.getRoomListFromHouse();
    }

    public String getDeviceAttributesListInString(Device device) {
        return device.showDeviceListAttributesInString();
    }

    public void setDeviceName(Device device, String newName) {
        device.setDeviceName(newName);
    }

    public void setDeviceRoom(Device device, Room newRoom) {
        device.setRoom(newRoom);
    }

    public void setNominalPower(Device device, double newNominalPower) {
        device.setNominalPower(newNominalPower);
    }
    //FridgeSets
    public void setFridgeFreezerCapacity(Device device, int newFreezerCap) {
        ((Fridge)device.getDeviceSpecs()).setFreezerCapacity(newFreezerCap);
    }
    public void setFridgeRefrigeratorCapacity(Device device,int newRefrigeratorCap) {
        ((Fridge)device.getDeviceSpecs()).setRefrigeratorCapacity(newRefrigeratorCap);
    }
    //DWSets
    public void setDWCapacity(Device device,int newCapacity){
        ((Dishwasher)device.getDeviceSpecs()).setCapacity(newCapacity);
    }
    //EletricWaterHeaterSets
    public void setEWHVolumeOfWater(Device device,double newVolumeOfWater) {
        ((ElectricWaterHeater)device.getDeviceSpecs()).setVolumeOfWater(newVolumeOfWater);
    }
    public void setEWHHotWaterTemperature(Device device,double newHotWaterTemp){
        ((ElectricWaterHeater)device.getDeviceSpecs()).setHotWaterTemperature(newHotWaterTemp);
    }
    public void setEWHColdWaterTemperature(Device device,double newColdWaterTemp){
        ((ElectricWaterHeater)device.getDeviceSpecs()).setColdWaterTemperature(newColdWaterTemp);
    }
    public void setEWHPerformanceRatio(Device device,double newPerformanceRatio) {
        ((ElectricWaterHeater)device.getDeviceSpecs()).setPerformanceRatio(newPerformanceRatio);
    }
    //Lamp
    public void setLampLuminousFlux(Device device,int newLuminousFlux){
        ((Lamp)device.getDeviceSpecs()).setLuminousFlux(newLuminousFlux);
    }
    public void setWashingMachineCapacity(Device device, int newCapacity){
        ((WashingMachine)device.getDeviceSpecs()).setCapacity(newCapacity);
    }

}
