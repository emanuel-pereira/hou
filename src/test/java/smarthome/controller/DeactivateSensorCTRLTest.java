package smarthome.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.mapper.SensorMapper;
import smarthome.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DeactivateSensorCTRLTest {

    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
        Field instance2 = TypeGAList.class.getDeclaredField("typeGaList");
        instance2.setAccessible(true);
        instance2.set(null, null);
    }

    @Test
    @DisplayName("Check the correct size of the gaList")
    void getGAList() {
        GAList gaList = new GAList();
        //ga Porto created and added to gaList
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        //ga Lisbon created and added to gaList
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        GeographicalAreaMapper areaMapper = new GeographicalAreaMapper();
        areaMapper.toDtoList(gaList);

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);

        int result = ctrl.getGAList().size();

        assertEquals(2, result);
    }

    @Test
    @DisplayName("Check the correct size of the gaList")
    void getGAListSize() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);

        int expected = 2;
        int result = ctrl.getGAList().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deactivate a active sensor with success")
    void deactivateSensorCorrectly() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);

        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensorDTO.getId();
        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.MARCH, 2);

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        boolean result = ctrl.deactivateSensor(gaDTOId, sensorDTOId, pDate);
        assertTrue(result);
    }

    @Test
    @DisplayName("Try to deactivate a sensor with the wrong name and return false")
    void deactivateSensorIfWrongName() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = "invalidID";
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.SEPTEMBER, 2);
        boolean result = ctrl.deactivateSensor(gaDTOId, sensorDTOId, pDate);
        assertFalse(result);
    }

    @Test
    void getSensorIfActiveToDto() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();

        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);
        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.SEPTEMBER, 2);
        sensor.deactivate(pDate);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);

        int result = ctrl.getSensorIfActiveDto(gaDTOId).size();

        assertEquals(0, result);
    }


    @Test
    @DisplayName("Returns geographical area Lisbon when searching for a geographical area with Id=LIS")
    void getGAById() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        GeographicalArea result = ctrl.getGAById("LIS");
        assertEquals(lisbon, result);
    }

    @Test
    @DisplayName("Returns Null Pointer Exception when searching for an nonexistent geographical area Id")
    void getGAByIdReturnsNullPointerException() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        boolean thrown = false;
        try {
            GeographicalArea ga = ctrl.getGAById("coim");
        } catch (NullPointerException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    @DisplayName("Don´t return geographical area Lisbon when searching for a geographical area with Id=POR")
    void getGAByIdNotEquals() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        GeographicalArea result = ctrl.getGAById("POR");
        assertNotEquals(lisbon, result);
    }

    @Test
    @DisplayName("Display true because sensor is active")
    void sensorStatusTrue() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();

        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");

        Sensor sensor1 = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor1);
        Sensor sensor2 = new ExternalSensor("TL1024", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor2);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensor1DTO = sMapper.toDto(sensor1);
        SensorDTO sensor2DTO = sMapper.toDto(sensor2);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensor1DTO);
        sensorListDTO.add(sensor2DTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensor1DTO.getId();

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);

        boolean result = ctrl.sensorStatus(gaDTOId, sensorDTOId);

        assertTrue(result);
    }

    @Test
    @DisplayName("Display false because sensor is deactivated")
    void sensorStatusFalse() {
        GAList gaList = new GAList();
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();

        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");

        ExternalSensor sensor1 = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor1);
        ExternalSensor sensor2 = new ExternalSensor("TL1024", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor2);

        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        sensor2.deactivate(pDate);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensor1DTO = sMapper.toDto(sensor1);
        SensorDTO sensor2DTO = sMapper.toDto(sensor2);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensor1DTO);
        sensorListDTO.add(sensor2DTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = sensor2DTO.getId();

        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);

        boolean result = ctrl.sensorStatus(gaDTOId, sensorDTOId);

        assertFalse(result);
    }

    @Test
    @DisplayName("Check if the is not possible to deactivate a sensor with a invalid sensor id")
    void deactivateSensorForInvalidSensorId() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        TypeGAList.addTypeGA(new TypeGA("city"));
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);
        OccupationArea lisOA = new OccupationArea(35, 20);
        Location lisLoc = new Location(55, 22, 29);
        GeographicalArea lisbon = new GeographicalArea("LIS", "Lisbon", "City", lisOA, lisLoc);
        gaList.addGA(lisbon);

        SensorList lisbonSensorList = lisbon.getSensorListInGA();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        GeographicalAreaDTO lisbonDTO = new GeographicalAreaDTO("LIS", "Lisbon", sensorListDTO);
        String gaDTOId = lisbonDTO.getIdentification();
        String sensorDTOId = "invalidID";
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.MARCH, 2);

        boolean result = ctrl.deactivateSensor(gaDTOId, sensorDTOId, pDate);

        assertFalse(result);
    }

    @Test
    @DisplayName("Try to deactivate sensor for invalid geographical area id")
    void removeSensorForInvalidGAIdReturnsFalse() {
        GAList gaList = new GAList();
        OccupationArea portoOA = new OccupationArea(25, 20);
        Location portoLoc = new Location(25, 12, 29);
        GeographicalArea porto = new GeographicalArea("POR", "Porto", "City", portoOA, portoLoc);
        gaList.addGA(porto);

        SensorList lisbonSensorList = porto.getSensorListInGA();
        Location sLoc = new Location(55, 21, 26);
        GregorianCalendar sDate = new GregorianCalendar(2019, Calendar.MARCH, 2);
        SensorType sensorType = new SensorType("Temperature");
        Sensor sensor = new ExternalSensor("TL1023", "TemperatureSensor", sDate, sLoc, sensorType, "Celsius", new ReadingList());
        lisbonSensorList.addSensor(sensor);

        SensorMapper sMapper = new SensorMapper();
        SensorDTO sensorDTO = sMapper.toDto(sensor);
        List<SensorDTO> sensorListDTO = new ArrayList<>();
        sensorListDTO.add(sensorDTO);

        String gaDTOId = "Ga";
        String sensorDTOId = sensorDTO.getId();
        DeactivateSensorCTRL ctrl = new DeactivateSensorCTRL(gaList);
        GregorianCalendar pDate = new GregorianCalendar(2019, Calendar.MARCH, 2);

        boolean thrown = false;
        try {
            ctrl.deactivateSensor(gaDTOId, sensorDTOId, pDate);
        } catch (NullPointerException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}