package smarthome.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.*;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GeographicalAreaMapperTest {

    @Test
    @DisplayName("Ensure that geographical area Aveiro is converted into a geographical area DTO and that the designation of both objects matches")
    void toDto() {
        OccupationArea occupationArea = new OccupationArea(25, 25);
        Location location = new Location(18, 2, 13);
        GeographicalArea aveiro = new GeographicalArea("AVR", "Aveiro", "City", occupationArea, location);
        SensorList sensorList = aveiro.getSensorListInGA();
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("S01", "Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        sensorList.addSensor(sensor);
        GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
        GeographicalAreaDTO gaDTO = gaMapper.toDto(aveiro);

        String expected = aveiro.getGAName();
        String result = gaDTO.getDesignation();

        assertEquals(expected, result);

        String expected1 = aveiro.getId();
        String result1 = gaDTO.getIdentification();

        assertEquals(expected1, result1);
    }

    @Test
    @DisplayName("Ensure that geographical area Aveiro is converted into a geographical area DTO and that the size of the sensor list in both objects matches")
    void toDtoReturnsSensorListWithTheSameSize() {
        OccupationArea occupationArea = new OccupationArea(25, 25);
        Location location = new Location(18, 2, 13);
        GeographicalArea aveiro = new GeographicalArea("AVR", "Aveiro", "City", occupationArea, location);
        SensorList sensorList = aveiro.getSensorListInGA();
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("S01", "Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        sensorList.addSensor(sensor);
        GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
        GeographicalAreaDTO gaDTO = gaMapper.toDto(aveiro);

        int expected = aveiro.getSensorListInGA().size();
        int result = gaDTO.getSensorListDTO().size();

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that geographical area Aveiro is converted into a geographical area DTO and that the size of the sensor list DTO isn't zero")
    void toDtoDoesNotReturnEmptySensorListDTO() {
        OccupationArea occupationArea = new OccupationArea(25, 25);
        Location location = new Location(18, 2, 13);
        GeographicalArea aveiro = new GeographicalArea("AVR", "Aveiro", "City", occupationArea, location);
        SensorList sensorList = aveiro.getSensorListInGA();
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("S01", "Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        sensorList.addSensor(sensor);
        GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
        GeographicalAreaDTO gaDTO = gaMapper.toDto(aveiro);

        int expected = 0;
        int result = gaDTO.getSensorListDTO().size();

        assertNotEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that all geographical areas in GAList are converted to DTOs")
    void toDtoList() {
        GAList gaList = new GAList();

        OccupationArea area1 = new OccupationArea(25, 25);
        Location loc1 = new Location(18, 2, 13);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", area1, loc1);
        SensorList sLPorto = porto.getSensorListInGA();
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("S01", "Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        sLPorto.addSensor(sensor);
        gaList.addGA(porto);

        OccupationArea area2 = new OccupationArea(25, 25);
        Location loc2 = new Location(18, 2, 13);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("AVR", "Aveiro", "City", area2, loc2);
        gaList.addGA(lisbon);

        GeographicalAreaMapper geographicalAreaMapper = new GeographicalAreaMapper();
        List<GeographicalAreaDTO> gasDTOs = geographicalAreaMapper.toDtoList(gaList);
        int expected = 2;
        int result = gasDTOs.size();
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Ensure that after executing the toDtoList method the size of the list of geographical area DTOs doesn't remain zero")
    void toDtoListDoesNotReturnEmptyGADTOList() {
        GAList gaList = new GAList();

        OccupationArea area1 = new OccupationArea(25, 25);
        Location loc1 = new Location(18, 2, 13);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", area1, loc1);
        SensorList sLPorto = porto.getSensorListInGA();
        SensorType temperature = new SensorType("Temperature");
        ReadingList readingList = new ReadingList();
        Sensor sensor = new Sensor("S01", "Temperature Sensor", new GregorianCalendar(2019, 1, 5), temperature, "Celsius", readingList);
        sLPorto.addSensor(sensor);
        gaList.addGA(porto);

        OccupationArea area2 = new OccupationArea(25, 25);
        Location loc2 = new Location(18, 2, 13);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("AVR", "Aveiro", "City", area2, loc2);
        gaList.addGA(lisbon);

        GeographicalAreaMapper geographicalAreaMapper = new GeographicalAreaMapper();
        List<GeographicalAreaDTO> gasDTOs = geographicalAreaMapper.toDtoList(gaList);
        int expected = 0;
        int result = gasDTOs.size();
        assertNotEquals(expected, result);
    }


}