/*package smarthome.controller;

import smarthome.model.*;

import java.util.List;

public class US160GetDeviceListInGridByTypeCTRL {

    private House mHouse;

    public US160GetDeviceListInGridByTypeCTRL (House house) {
        mHouse = house;
    }

    public List<HouseGrid> getHouseGridListCtrl() {
        return mHouse.getHGListInHouse().getHouseGridList();
    }

    public String showHouseGridListInStringCtrl() {
        return mHouse.getHGListInHouse().showHouseGridListInString();
    }



    public RoomList getListOfRoomsInGrid(int indexOfHouseGrid) {
        RoomList listOfRoomsWithHouseGrid = new RoomList();
        for (Room r : mHouse.getRoomList().getRoomList()) {
            if (r.getmHouseGrid() != null && r.getmHouseGrid().equals(mHouse.getHGListInHouse().get(indexOfHouseGrid - 1))) {
                   listOfRoomsWithHouseGrid.addRoom(r);
            }
        }
        return listOfRoomsWithHouseGrid;
    }

    public List<Device> getDeviceListInGridCtrl(int indexHG) {
        DeviceList deviceListInGrid = new DeviceList();
        for (int i = 0; i < this.getListOfRoomsInGrid(indexHG).getRoomListSize(); i++) {
            for (Device d : this.getListOfRoomsInGrid(indexHG).get(i).getDeviceList().getDeviceList()) {
                deviceListInGrid.addDevice(d);
            }
        }
        return deviceListInGrid.getDeviceList();
    }

    public List<Device> deviceListGroupByTypeCtrl(int indexHG){
        DeviceList deviceListGroupByType = new DeviceList();
        int i;
        for(i=0;i<14;i++){
            for(Device d : this.getDeviceListInGridCtrl(indexHG)){
                if(DeviceType.values()[i].getTypeString().equals(d.getDeviceSpecs().getType().getTypeString())){
                    deviceListGroupByType.addDevice(d);
                }
            }
        }
    return deviceListGroupByType.getDeviceList();
    }


    public String showGroupedDeviceListInGridString (int indexHG) {
        List<Device> list = deviceListGroupByTypeCtrl(indexHG);
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (Device device : list) {
            result.append(number++);
            result.append(element);
            result.append(device.getName());
            result.append(", Nominal Power: ");
            result.append(device.getNominalPower());
            result.append(", Type: ");
            result.append(device.getDeviceSpecs().getType().getTypeString());
            result.append(",Location: ");
            *//*result.append(device.getRoom().getName());*//*
            result.append(".\n");
        }
        return result.toString();
    }

}*/
