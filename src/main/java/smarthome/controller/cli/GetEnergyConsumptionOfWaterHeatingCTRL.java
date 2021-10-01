package smarthome.controller.cli;

import smarthome.dto.DeviceDTO;
import smarthome.mapper.DeviceMapper;
import smarthome.model.Device;
import smarthome.model.RoomList;

import java.util.ArrayList;
import java.util.List;

import static smarthome.model.House.getHouseRoomList;

public class GetEnergyConsumptionOfWaterHeatingCTRL {

    private final RoomList roomList;
    private final DeviceMapper deviceMapper = new DeviceMapper ();
    private List<Device> listOfDevices= new ArrayList<>();


    public GetEnergyConsumptionOfWaterHeatingCTRL() {
        this.roomList = getHouseRoomList();
    }

    public List<DeviceDTO> getDevicesInAllRoomsByType(String deviceType) {
        listOfDevices=roomList.getDevicesInAllRoomsByType(deviceType);
        return this.deviceMapper.toDtoList(listOfDevices);
    }

    public double getEstimatedEnergyConsumptionByDeviceType(String deviceType) {
        return this.roomList.getEstimatedEnergyConsumptionByDeviceType(deviceType);
    }

    public void setAttribute(String deviceName, String attribute, double newValue) {
        for(Device device:listOfDevices){
            if(device.getDeviceName().equals(deviceName))
                device.getDeviceSpecs().setAttributeValue(attribute, newValue);
        }
    }


}
