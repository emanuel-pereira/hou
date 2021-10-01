package smarthome.mapper;

import smarthome.dto.DeviceDTO;
import smarthome.model.Device;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceMapper {


        /**
         * Converts a device into a deviceDTO to be used as a model view, containing only name, type and nominal power as attributes.
         * @param device to be converted in deviceDTO
         * @return a deviceDTO
         */
        public DeviceDTO toDto(Device device) {
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setName(device.getDeviceName());
            deviceDTO.setType(device.getDeviceType());
            deviceDTO.setNominalPower(device.getNominalPower());
            return deviceDTO;
        }

        /**
         * Converts a list of devices into a list of devices DTOs to be used as a model view, containing only only name, type and nominal power as attributes.
         * @param deviceList to be converted into a list of devices DTOs
         * @return a list of devices DTOs
         */
        public List<DeviceDTO> toDtoList(List<Device> deviceList) {
            List<Device> listOfDevices=deviceList;
            return listOfDevices.stream().map(this::toDto).collect(Collectors.toList());
        }

    }
