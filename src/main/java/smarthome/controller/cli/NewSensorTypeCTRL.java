package smarthome.controller.cli;

import smarthome.dto.SensorTypeDTO;
import smarthome.model.SensorTypeList;
import smarthome.repository.SensorTypeRepository;
import smarthome.services.SensorTypeService;

import java.util.List;


public class NewSensorTypeCTRL {


    private final SensorTypeService sensorTypeRepoDDD;

    public NewSensorTypeCTRL(SensorTypeList sensorTypeList) {
        this.sensorTypeRepoDDD=new SensorTypeService(sensorTypeList);
    }

    /**
     * Test controller constructor
     * @param sensorTypeRepository for repository dependency injection
     */
    public NewSensorTypeCTRL(SensorTypeRepository sensorTypeRepository){
        this.sensorTypeRepoDDD= new SensorTypeService(sensorTypeRepository);
    }

    /**
     * Method to create an object of the type GA with the a user inputted String
     *
     * @param newSensorType user inputted String to a type of GA
     * @return true if it was possible to add the user's chosen new type of GA
     * false if it was not possible to add the new type of GA, eg. if the type already exists
     */
    public boolean createSensorType(String newSensorType) {
        SensorTypeDTO sensorTypeDTO= new SensorTypeDTO();
        sensorTypeDTO.setType(newSensorType);
        return this.sensorTypeRepoDDD.createSensorType(sensorTypeDTO);
    }
    /**
     *Method checks if the  type passed as parameter already is persisted in the database
     * @param type string parameter that specifies a sensor type
     * @return true if the type is already persisted, otherwise, returns false
     */
    public boolean existsByType(String type){
        return this.sensorTypeRepoDDD.existsByType(type);
    }


    /**
     * Method that finds all sensor types in the database and returns them as a list of DTOs
     * @return a list of sensor types DTOs
     */
    public List<SensorTypeDTO> listOfSensorTypesDTOs() {
        return this.sensorTypeRepoDDD.findAll();
    }
}
