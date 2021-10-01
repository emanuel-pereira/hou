package smarthome.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthome.dto.*;
import smarthome.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class InternalSensorMapperTest {
    InternalSensorMapper internalSensorMapper = new InternalSensorMapper();

    InternalSensor internalSensorMock(){
        Calendar startDate = new GregorianCalendar(2019, 5, 25);
        Location loc = new Location(22, 32, 55);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "city", new OccupationArea(25, 45), loc);
        SensorType type = new SensorType("temperature");
        type.setId(3L);
        InternalSensor internalSensor = new InternalSensor("T1025", "Kitchen Temperature Sensor", startDate, type, "C", new ReadingList());
        internalSensor.setRoomId("KITCHENISEP");
        return internalSensor;
    }

    InternalSensorDTO internalSensorDTOMock() {
        InternalSensorDTO internalSensorDTO = new InternalSensorDTO();
        internalSensorDTO.setId("TT1250");


        SensorBehaviorDTO sensorBehaviorDTO = new SensorBehaviorDTO();
        sensorBehaviorDTO.setName("Kitchen Humidity Sensor");
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO("humidity");
        sensorBehaviorDTO.setSensorType(sensorTypeDTO);
        sensorBehaviorDTO.setStartDate(new GregorianCalendar(2019, 11, 15));
        sensorBehaviorDTO.setUnit("C");
        sensorBehaviorDTO.setActive(true);
        internalSensorDTO.setSensorBehavior(sensorBehaviorDTO);
        internalSensorDTO.setRoomId("KITCHENISEP");

        return internalSensorDTO;
    }


    @Test
    void toDTOTestReturnsEqualIdForObjectAndDTO() {
        InternalSensor sensor = internalSensorMock();
        InternalSensorDTO dto = internalSensorMapper.toDto(sensor);
        String expected = sensor.getId();
        String result = dto.getId();
        assertEquals(expected, result);
    }

    @Test
    void toDTOTestReturnsEqualNameForObjectAndDTO() {
        InternalSensor sensor = internalSensorMock();
        InternalSensorDTO dto = internalSensorMapper.toDto(sensor);
        String expected = sensor.getSensorBehavior().getDesignation();
        String result = dto.getSensorBehavior().getName();
        assertEquals(expected, result);
    }

    @Test
    void sensorIdIsNotEqualToDTORoomId() {
        InternalSensor sensor = internalSensorMock();
        InternalSensorDTO dto = internalSensorMapper.toDto(sensor);
        String expected = sensor.getId();
        String result = dto.getRoomId();
        assertNotEquals(expected, result);
    }

    @Test
    void toDTOOfNullObjectReturnsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,()->internalSensorMapper.toDto(null));
    }

    @Test
    void toEntityTestReturnsEqualIdForDTOAndObject() {
        InternalSensorDTO dto = internalSensorDTOMock();
        InternalSensor internalSensor=internalSensorMapper.toEntity(dto);

        String expected=dto.getId();
        String result= internalSensor.getId();

        assertEquals(expected,result);
    }

    @Test
    void startDateHasDifferentHashCodeInEntityAndDTO() {
        InternalSensorDTO dto = internalSensorDTOMock();
        InternalSensor internalSensor=internalSensorMapper.toEntity(dto);

        Calendar expected=dto.getSensorBehavior().getStartDate();
        Calendar result= internalSensor.getSensorBehavior().getStartDate();

        assertEquals(expected,result);
    }

    @Test
    void toEntityTestReturnsEqualUnitForDTOAndObject() {
        InternalSensorDTO dto = internalSensorDTOMock();
        InternalSensor internalSensor=internalSensorMapper.toEntity(dto);

        String expected=dto.getSensorBehavior().getUnit();
        String result= internalSensor.getSensorBehavior().getUnit();

        assertEquals(expected,result);
    }

    @Test
    void toEntityTestReturnsEqualSensorTypeIDForDTOAndObject() {
        InternalSensorDTO dto = internalSensorDTOMock();
        InternalSensor internalSensor=internalSensorMapper.toEntity(dto);

        SensorTypeDTO dtoSensorType=dto.getSensorBehavior().getSensorType();
        SensorType entitySensorType=internalSensor.getSensorBehavior().getSensorType();

        String expected=dtoSensorType.getType();
        String result= entitySensorType.getType().getName();

        assertEquals(expected,result);
    }


    @Test
    void toEntityOfNullObjectReturnsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,()->internalSensorMapper.toEntity(null));
    }
}
