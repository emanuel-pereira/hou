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

    public boolean addDevice(int indexOfRoom, String inputName, DeviceSpecs deviceSpecs, double nominalPower) {
        Device device = mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().newDevice(inputName,deviceSpecs,mHouse.getRoomListFromHouse().get(indexOfRoom-1),nominalPower);
        return mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().addDevice(device);
    }

   /* public boolean addDeviceWithoutSpecsToRoom(int indexOfRoom, String inputName, String deviceType, double nominalPower){
        Device device = mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().newDeviceWithoutSpecs(inputName,mHouse.getRoomListFromHouse().get(indexOfRoom-1),deviceType,nominalPower);
        return mHouse.getRoomListFromHouse().get(indexOfRoom-1).getDeviceList().addDevice(device);
    }
*/
    /**Method to validate Strings only accepting alphanumeric characters as well as spaces and hyphens.
     * @param inputName valid name
     * @return name if if is
     */
    public String alphanumericName(String inputName){
        return mNameValidations.alphanumericName(inputName);
    }
}
