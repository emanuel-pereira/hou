/*package smarthome.controller.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorTypeList;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
class NewSensorTypeCTRLTest {


    @Test
    @DisplayName("Tests if a new sensor type is created and persisted")
    void createAndPersistNewSensorType() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        boolean result = ctrl.createSensorType("temperature");
        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if a sensor type already persisted is not created in duplicate")
    void alreadyPersistedSensorTypeIsNotCreatedAndPersistedAgain() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        ctrl.createSensorType("temperature");
        boolean result = ctrl.createSensorType("Temperature");
        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if existsByType returns true to a sensor type already persisted")
    void existsByTypeReturnsTrueToAlreadyPersistedSensorType() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        ctrl.createSensorType("rainfall");
        boolean result = ctrl.existsByType("RAINFALL");
        assertTrue(result);
    }

    @Test
    @DisplayName("Tests if existsByType returns false to a non-persisted sensor type")
    void existsByTypeReturnsFalseToNonPersistedSensorType() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        ctrl.createSensorType("rainfall");
        boolean result = ctrl.existsByType("wind");
        assertFalse(result);
    }

    @Test
    @DisplayName("Tests if listOfSensorTypesDTOs returns a list containing 4 DTOs of sensor types persisted in which the" +
            "third element is of type: temperature")
    void listOfSensorTypesDTOsHas3ElementsAndThirdElementIsTemperature() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        ctrl.createSensorType("raINFAll");
        ctrl.createSensorType("WIND");
        ctrl.createSensorType("tempERaTURE");
        List<SensorTypeDTO> sensorTypeDTOs = ctrl.listOfSensorTypesDTOs();

        int expectedSize=3;
        int resultingSize=sensorTypeDTOs.size();
        assertEquals(expectedSize,resultingSize);

        String expectedType="temperature";
        String resultingType=sensorTypeDTOs.get(2).getSensorType();
        assertEquals(expectedType,resultingType);
    }

    @Test
    @DisplayName("Tests if listOfSensorTypesDTOs returns a list containing 0 sensor types for an empty repository")
    void listOfSensorTypesDTOsIsEmptyForEmptyRepository() {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);

        List<SensorTypeDTO> sensorTypeDTOs = ctrl.listOfSensorTypesDTOs();

        int expectedSize=0;
        int resultingSize=sensorTypeDTOs.size();
        assertEquals(expectedSize,resultingSize);
    }


    @Test
    @DisplayName("Tests if listOfSensorTypesDTOs is not empty when repository has 2 sensor types persisted and that " +
            "the first element in the list name is not of type Humidity")
    void listOfSensorTypesDTOsIsNotEmptyAndFirstElementIsNotOfTypeHumidity() throws ParseException {
        SensorTypeList sensorTypeList= new SensorTypeList();
        NewSensorTypeCTRL ctrl = new NewSensorTypeCTRL(sensorTypeList);
        ctrl.createSensorType("raINFAll");
        ctrl.createSensorType("Humidity");
        List<SensorTypeDTO> sensorTypeDTOs = ctrl.listOfSensorTypesDTOs();

        int expectedSize=0;
        int resultingSize=sensorTypeDTOs.size();
        assertNotEquals(expectedSize,resultingSize);

        String expectedType="humidity";
        String resultingType=sensorTypeDTOs.get(0).getSensorType();
        assertNotEquals(expectedType,resultingType);
    }
}*/
