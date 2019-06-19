package smarthome.controller.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import smarthome.model.SensorType;
import smarthome.model.SensorTypeList;
import smarthome.model.validations.Name;

import smarthome.repository.SensorTypeRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NewSensorTypeCTRLTest {

    @Mock
    private SensorTypeRepository sensorTypeRepository;

    private NewSensorTypeCTRL newSensorTypeCTRL;

    private SensorType typeTemperature;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.newSensorTypeCTRL = new NewSensorTypeCTRL(sensorTypeRepository);

        typeTemperature = new SensorType("temperature");
    }

    @Test
    @DisplayName("Create new Sensor Type")
    void createSensorType() {

        when(sensorTypeRepository.save(this.typeTemperature)).thenReturn(this.typeTemperature);

        boolean expected = newSensorTypeCTRL.createSensorType("temperature");
        assertTrue(expected);

    }

    @Test
    @DisplayName("Check if a sensor type exists and return true because he exists")
    void checkIfTypeExists() {

        Name temperature = new Name("temperature");

        when(sensorTypeRepository.existsByType(temperature)).thenReturn(true);

        boolean expected = newSensorTypeCTRL.existsByType("temperature");
        assertTrue(expected);
    }

    @Test
    @DisplayName("Check if a sensor type dont exists and return false because he dont exists")
    void checkIfTypeDontExists() {

        Name temperature = new Name("temperature");

        when(sensorTypeRepository.existsByType(temperature)).thenReturn(false);

        boolean expected = newSensorTypeCTRL.existsByType("temperature");
        assertFalse(expected);
    }


    @Test
    @DisplayName("List all sensor types")
    void listOfSensorTypesDTOs() {

        SensorType typeRainfall = new SensorType("rainfall");

        when(sensorTypeRepository.findAll()).thenReturn(Stream.of(this.typeTemperature, typeRainfall).collect(Collectors.toList()));

        int result = newSensorTypeCTRL.listOfSensorTypesDTOs().size();

        assertEquals(2, result);
    }


    //Because we are using a specific constructor for tests this one has no coverage
    @Test
    @DisplayName("Test used constructor in running mode")
    void realConstructor() {

        SensorTypeList sensorTypeList = new SensorTypeList();

        NewSensorTypeCTRL newSensorTypeCTRL = new NewSensorTypeCTRL(sensorTypeList);

        assertThat(newSensorTypeCTRL).isInstanceOf(NewSensorTypeCTRL.class);
    }



}
