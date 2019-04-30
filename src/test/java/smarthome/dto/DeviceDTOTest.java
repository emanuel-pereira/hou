package smarthome.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDTOTest {

    @Test
    void getType() {
        DeviceDTO d = new DeviceDTO();
        String type = "I am a type";
        d.setType(type);
        String result = d.getType();
        assertEquals(type,result);
    }


    @Test
    void getName() {
        DeviceDTO d = new DeviceDTO();
        String name = "I am a name";
        d.setName(name);
        String result = d.getName();
        assertEquals(name,result);
    }


    @Test
    void getNominalPower() {
        DeviceDTO d = new DeviceDTO();
        double nomPower = 123;
        d.setNominalPower(nomPower);
        double result = d.getNominalPower();
        assertEquals(nomPower,result);
    }

}