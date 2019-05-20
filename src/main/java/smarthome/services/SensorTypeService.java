package smarthome.services;

import smarthome.dto.SensorTypeDTO;
import smarthome.mapper.SensorTypeMapper;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeService {

    SensorTypeMapper mapper;

    //TODO: encapsulate sensorTypeRepository as attribute of this class through autowired or by dependency injection in constructor

    /**
     * Constructor method that creates an instance of the SensorTypeRepoDDD
     */
    public SensorTypeService() {
        this.mapper = new SensorTypeMapper();
    }


    /**
     * Method to creates and adds a sensor type to the database if the sensor type doesn't already exist.
     *
     * @param type - String that names the type of data
     * @return new data type object with designation
     */
    public boolean createSensorType(SensorTypeDTO type) {
        SensorType sensorType = convertToEntity(type);
        if (Repositories.getSensorTypeRepository().existsByType(sensorType.getType())) {
            return false;
        }
        Repositories.getSensorTypeRepository().save(sensorType);
        return true;
    }

    public SensorType convertToEntity(SensorTypeDTO dto) {
        SensorType type = mapper.toEntity(dto);
        return type;
    }

    /**
     * @return the number of sensor types persisted in the database
     */
    public long size() {
        return Repositories.getSensorTypeRepository().count();

    }


    /**
     * Method to return all sensor types included in the repository as dto objects
     *
     * @return list of sensor types as DTO
     */
    public List<SensorTypeDTO> findAll() {
        Iterable<SensorType> sensorTypes = Repositories.getSensorTypeRepository().findAll();
        //For each to convert an iterator to a list of elements
        List<SensorType> sensorTypeList = new ArrayList<>();
        for (SensorType sensorType : sensorTypes) {
            sensorTypeList.add(sensorType);
        }
        return mapper.toDtoList(sensorTypeList);
    }


    /**
     * Some SensorTypes are required in some User Stories, so this method checks if a mandatory sensor type exists
     *
     * @param type sensor type designation
     * @return true if exists and false if not
     */
    public boolean existsByType(String type) {
        Name repoType = new Name(type);
        return Repositories.getSensorTypeRepository().existsByType(repoType);
    }

    //TODO: whenever needed to show
    public SensorType findByType(String type) {
        Name repoType = new Name(type);
        if (Repositories.getSensorTypeRepository().findByType(repoType) == null) {
            throw new NullPointerException(type + " sensor type does not exist.");
        }
        return Repositories.getSensorTypeRepository().findByType(repoType);
    }

    public SensorTypeDTO findByTypeDTO(String type) {
        SensorType sensorType = findByType(type);
        return mapper.toDto(sensorType);

    }

    public SensorTypeDTO findById(Long id) {
        SensorType sensorType = Repositories.getSensorTypeRepository().findById(id).get();
        return mapper.toDto(sensorType);
    }
}
