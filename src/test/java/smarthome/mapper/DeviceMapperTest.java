package smarthome.mapper;

import org.junit.jupiter.api.Test;
import smarthome.dto.DeviceDTO;
import smarthome.model.Device;
import smarthome.model.devices.ElectricWaterHeaterType;

import static org.junit.jupiter.api.Assertions.*;

class DeviceMapperTest {

    @Test
    void getType() {
        DeviceMapper mapper= new DeviceMapper();
        ElectricWaterHeaterType type = new ElectricWaterHeaterType();
        Device ehw = type.createDevice("Daikin Heater", 200);
        DeviceDTO deviceDTO=mapper.toDto(ehw);
        String expected= "ElectricWaterHeater";
        String result= deviceDTO.getType();
        assertEquals(expected,result);
    }
    @Test
    void getName() {
        DeviceMapper mapper= new DeviceMapper();
        ElectricWaterHeaterType type = new ElectricWaterHeaterType();
        Device ehw = type.createDevice("Daikin Heater", 200);
        DeviceDTO deviceDTO=mapper.toDto(ehw);
        String expected= "Daikin Heater";
        String result= deviceDTO.getName();
        assertEquals(expected,result);
    }
    @Test
    void getNominalPower() {
        DeviceMapper mapper= new DeviceMapper();
        ElectricWaterHeaterType type = new ElectricWaterHeaterType();
        Device ehw = type.createDevice("Daikin Heater", 200);
        DeviceDTO deviceDTO=mapper.toDto(ehw);
        double expected= 200;
        double result= deviceDTO.getNominalPower();
        assertEquals(expected,result);
    }
}