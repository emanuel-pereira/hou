package smarthome.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthome.dto.ExternalSensorDTO;
import smarthome.dto.LocationDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ExternalSensorMapperTest {

    ExternalSensorMapper externalSensorMapper = new ExternalSensorMapper();

    ExternalSensor externalSensorMock(){
        Calendar startDate = new GregorianCalendar(2019, 5, 25);
        Location loc = new Location(22, 32, 55);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "city", new OccupationArea(25, 45), loc);
        SensorType type = new SensorType("temperature");
        type.setId(3L);
        ExternalSensor externalSensor = new ExternalSensor("T1025", "Temperature Sensor", startDate, loc, type, "C", new ReadingList());
        externalSensor.setIdGA("POR");
        return externalSensor;
    }

    ExternalSensorDTO externalSensorDTOMock() {
        ExternalSensorDTO externalSensorDTO = new ExternalSensorDTO();
        externalSensorDTO.setId("TT1250");

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(45);
        locationDTO.setLongitude(22);
        locationDTO.setAltitude(31);
        externalSensorDTO.setLocationDTO(locationDTO);

        SensorBehaviorDTO sensorBehaviorDTO = new SensorBehaviorDTO();
        sensorBehaviorDTO.setName("Rainfall Sensor Zone1");
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO("rainfall");
        sensorTypeDTO.setId(3L);
        sensorBehaviorDTO.setSensorType(sensorTypeDTO);
        sensorBehaviorDTO.setStartDate(new GregorianCalendar(2019, 11, 15));
        sensorBehaviorDTO.setUnit("C");
        sensorBehaviorDTO.setActive(true);
        externalSensorDTO.setSensorBehaviorDTO(sensorBehaviorDTO);
        externalSensorDTO.setIdGA("POR");

        return externalSensorDTO;
    }


    @Test
    void toDTOTestReturnsEqualIdForObjectAndDTO() {
        ExternalSensor sensor = externalSensorMock();
        ExternalSensorDTO dto = externalSensorMapper.toDto(sensor);
        String expected = sensor.getId();
        String result = dto.getId();
        assertEquals(expected, result);
    }

    @Test
    void toDTOTestReturnsEqualNameForObjectAndDTO() {
        ExternalSensor sensor = externalSensorMock();
        ExternalSensorDTO dto = externalSensorMapper.toDto(sensor);
        String expected = sensor.getSensorBehavior().getDesignation();
        String result = dto.getSensorBehaviorDTO().getName();
        assertEquals(expected, result);
    }

    @Test
    void sensorAltitudeIsNotEqualToDTOLongitude() {
        ExternalSensor sensor = externalSensorMock();
        ExternalSensorDTO dto = externalSensorMapper.toDto(sensor);
        double expected = sensor.getLocation().getAltitude();
        double result = dto.getLocationDTO().getLongitude();
        assertNotEquals(expected, result);
    }

    @Test
    void toDTOOfNullObjectReturnsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,()->externalSensorMapper.toDto(null));
    }

    @Test
    void toEntityTestReturnsEqualIdForDTOAndObject() {
        ExternalSensorDTO dto = externalSensorDTOMock();
        ExternalSensor externalSensor=externalSensorMapper.toEntity(dto);

        String expected=dto.getId();
        String result= externalSensor.getId();

        assertEquals(expected,result);
    }

    @Test
    void startDateHasDifferentHashCodeInEntityAndDTO() {
        ExternalSensorDTO dto = externalSensorDTOMock();
        ExternalSensor externalSensor=externalSensorMapper.toEntity(dto);

        Calendar expected=dto.getSensorBehaviorDTO().getStartDate();
        Calendar result= externalSensor.getSensorBehavior().getStartDate();

        assertEquals(expected,result);
    }

    @Test
    void toEntityTestReturnsEqualUnitForDTOAndObject() {
        ExternalSensorDTO dto = externalSensorDTOMock();
        ExternalSensor externalSensor=externalSensorMapper.toEntity(dto);

        String expected=dto.getSensorBehaviorDTO().getUnit();
        String result= externalSensor.getSensorBehavior().getUnit();

        assertEquals(expected,result);
    }

    @Test
    void toEntityTestReturnsEqualSensorTypeIDForDTOAndObject() {
        ExternalSensorDTO dto = externalSensorDTOMock();
        ExternalSensor externalSensor=externalSensorMapper.toEntity(dto);

        SensorTypeDTO dtoSensorType=dto.getSensorBehaviorDTO().getSensorType();
        SensorType entitySensorType=externalSensor.getSensorBehavior().getSensorType();

        Long expected=dtoSensorType.getId();
        Long result= entitySensorType.getId();

        assertEquals(expected,result);
    }


    @Test
    void toEntityOfNullObjectReturnsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,()->externalSensorMapper.toEntity(null));
    }

}