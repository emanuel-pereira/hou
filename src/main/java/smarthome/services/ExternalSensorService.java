package smarthome.services;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import smarthome.exceptions.ExternalSensorNotFoundException;
import smarthome.exceptions.GeographicalAreaNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.dto.ExternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.mapper.ExternalSensorMapper;
import smarthome.model.ExternalSensor;
import smarthome.model.SensorBehavior;
import smarthome.repository.ExternalSensorRepository;
import smarthome.repository.Repositories;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ExternalSensorService {

    private ExternalSensorRepository repo;
    private ExternalSensorMapper externalSensorMapper;
    private GeoAreaService geoAreaService;
    private SensorTypeService sensorTypeService;

    public ExternalSensorService() {
        this.externalSensorMapper = new ExternalSensorMapper();
        this.geoAreaService = new GeoAreaService();
        this.sensorTypeService = new SensorTypeService();
    }

    void init() {
        repo = Repositories.getExternalSensorRepository();
    }

    /**
     * This method iterates through all instances of external sensors in the database, converts them into DTOs and
     * returns the list of DTOs, so that model objects are not exposed on methods available in this layer.
     * @return a list of ExternalSensorDTOs
     */
    public List<ExternalSensorDTO> findAll() {
        this.init();
        List<ExternalSensorDTO> externalSensorDTOs = new ArrayList<>();
        repo.findAll().forEach(externalSensor -> {
            ExternalSensorDTO dto = externalSensorMapper.toDto(externalSensor);
            externalSensorDTOs.add(dto);
        });
        return externalSensorDTOs;
    }

    /**
     * This method checks if there is an external sensor with the id passed as parameter in the database.
     * If so, retrieves the external sensor with the given Id and maps it into an ExternalDTO so that the model layer is
     * not exposed by this method in other classes.
     * @param id String value representing the ExternalSensor id
     * @return the externalSensor with the given id or if none found throws an ExternalSensorNotFoundException.
     */
    public ExternalSensorDTO get(String id) {
        this.init();
        ExternalSensor externalSensor = getExternalSensor(id);
        return externalSensorMapper.toDto(externalSensor);
    }

    /**
     * This method retrieves an ExternalSensor with the id passed as parameter. If not, throws an ExternalSensorNotFoundException.
     * This method is private as it is only invoked in this class.
     * @param id String value representing the ExternalSensor id
     * @return an ExternalSensor instance (as this method is private, and therefore only used in this class, it can
     * return a model object)
     */
    @NotNull
    private ExternalSensor getExternalSensor(String id) {
        Optional<ExternalSensor> externalSensorOpt = repo.findById(id);
        if (!externalSensorOpt.isPresent()) {
            throw new ExternalSensorNotFoundException("There is no external sensor with id " + id);
        }
        return externalSensorOpt.get();
    }

    /**
     * This method receives an ExternalSensorDTO, checks if exists a geographical area with the IdGA passed as
     * the instance attribute, if not a GeographicalAreaNotFoundException is thrown. Afterwards checks if
     * exists a sensorType with the sensorTypeId passed as parameter, if not a SensorTypeNotFoundException is thrown.
     * If both preconditions mentioned previously are met, then it will retrieve an externalSensor instance and persist
     * it in the database. If so, it will retrieve an externalSensorDTO, so that the model layer is not exposed by
     * this method in other classes.
     * @param externalSensorDTO to be mapped to an ExternalSensor instance
     * @return an ExternalSensorDTO if it is successfully mapped to an ExternalSensor and persisted in the database.
     */
    public ExternalSensorDTO createExternalSensor(ExternalSensorDTO externalSensorDTO) {
        init();
        //checks if sensor's geographical area exists
        if (!geoAreaService.checkIfIdExists(externalSensorDTO.getIdGA())) {
            throw new GeographicalAreaNotFoundException("Geographical area with id " + externalSensorDTO.getIdGA() +
                    " does not exist.");
        }
        SensorBehaviorDTO sensorBehaviorDTO = externalSensorDTO.getSensorBehaviorDTO();
        //checks if sensor's type exists in the database

        if (!sensorTypeService.existsByID(sensorBehaviorDTO.getSensorType().getId())) {
            throw new SensorTypeNotFoundException("Sensor type with id " + sensorBehaviorDTO.getSensorType().getId() +
                    " does not exist.");
        }
        ExternalSensor externalSensor = externalSensorMapper.toEntity(externalSensorDTO);
        repo.save(externalSensor);

        return externalSensorDTO;
    }
    /**
     * This method checks if there is an external sensor with the id passed as parameter in the database.
     * If so, maps the externalSensorDTO to retrieve the external sensor with the given Id and deletes it from the
     * database. If the externalSensor is successfully deleted then it returns the DTO of the deleted object.
     * @param id String value representing the ExternalSensor id
     * @return ExternalSensorDTO of the deleted object.
     */
    public ExternalSensorDTO removeSensor(String id){
        this.init();
        ExternalSensor externalSensor = getExternalSensor(id);
        repo.delete(externalSensor);
        return externalSensorMapper.toDto(externalSensor);
    }
    /**
     * This method checks if there is an external sensor with the id passed as parameter in the database.
     * If so, maps the externalSensorDTO into an ExternalSensor object and if the pause date given as parameter is
     * after the sensor's start date, then it sets its Active state as false as well as sets its pauseDate saving this
     * changes in the database.
     * @param id String value representing the ExternalSensor id
     * @param pauseDate Calendar value to set the sensor's pauseDate which should be in yyyy-MM-dd format, otherwise
     * throws an InvalidParameterException.
     * @return an externalSensorDTO object passed as parameter
     */
    public ExternalSensorDTO deactivate(String id, Calendar pauseDate) {
        init();
        ExternalSensor externalSensor = getExternalSensor(id);
        SensorBehavior sensorBehavior = externalSensor.getSensorBehavior();
        checkIfActive(id, pauseDate, sensorBehavior);
        sensorBehavior.deactivate(pauseDate);
        repo.save(externalSensor);
        return externalSensorMapper.toDto(externalSensor);
    }


    /**
     * Private method auxiliary to the deactivate method to check if the parameters inputted meet the defined criteria,
     * i.e., if the pause date given as parameter is after the sensor's startDate and if the sensor's active status is
     * set to true.
     * @param id String value representing the ExternalSensor id
     * @param pauseDate Calendar value to set the sensor's pauseDate which should be in yyyy-MM-dd format, otherwise
     * throws an InvalidParameterException.
     * @param sensorBehavior which is used to check if the sensor's active status is set to true, if not an
     * IllegalArgumentException is thrown
     * @return true if the preconditions mentioned above are met.
     */
    private boolean checkIfActive(String id, Calendar pauseDate, SensorBehavior sensorBehavior) {
        if (!sensorBehavior.isActive())
            throw new IllegalArgumentException("The sensor with id " + id + " is already inactive.");
        if (pauseDate.before(sensorBehavior.getStartDate())) {
            throw new InvalidParameterException("The date inputted as pauseDate " + pauseDate +
                    "cannot be before the sensor's start date " + sensorBehavior.getStartDate());
        }
        return true;
    }
}
