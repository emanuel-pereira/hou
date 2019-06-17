package smarthome.services;

import org.jetbrains.annotations.NotNull;
import smarthome.dto.InternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.exceptions.InternalSensorNotFoundException;
import smarthome.exceptions.RoomNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.mapper.InternalSensorMapper;
import smarthome.model.InternalSensor;
import smarthome.model.SensorList;
import smarthome.repository.InternalSensorRepository;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InternalSensorService {

    private InternalSensorRepository repo;
    private InternalSensorMapper internalSensorMapper;
    private RoomService roomService;
    private SensorTypeService sensorTypeService;

    public InternalSensorService() {
        this.internalSensorMapper = new InternalSensorMapper();
        this.roomService = new RoomService();
        this.sensorTypeService = new SensorTypeService();
    }

    void injectRepository() {
        this.repo = Repositories.getInternalSensorRepository();
    }

    /**
     * This method iterates through all instances of internal sensors in the database, converts them into DTOs and
     * returns the list of DTOs, so that model objects are not exposed on methods available in this layer.
     *
     * @return a list of InternalSensorDTOs
     */
    public List<InternalSensorDTO> findAll() {
        this.injectRepository();
        List<InternalSensorDTO> internalSensorDTOs = new ArrayList<>();
        repo.findAll().forEach(internalSensor -> {
            InternalSensorDTO dto = internalSensorMapper.toDto(internalSensor);
            internalSensorDTOs.add(dto);
        });
        return internalSensorDTOs;
    }

    /**
     * This method checks if there is an internal sensor with the id passed as parameter in the database.
     * If so, retrieves the internal sensor with the given Id and maps it into an InternalSensorDTO so that the model layer is
     * not exposed by this method in other classes.
     *
     * @param id String value representing the InternalSensor id
     * @return the InternalSensor with the given id or if none found throws an InternalSensorNotFoundException.
     */
    public InternalSensorDTO get(String id) {
        this.injectRepository();
        InternalSensor internalSensor = getInternalSensor(id);
        return internalSensorMapper.toDto(internalSensor);
    }

    /**
     * This method retrieves an Internal with the id passed as parameter. If not, throws an InternalSensorNotFoundException.
     * This method is private as it is only invoked in this class.
     *
     * @param id String value representing the InternalSensor id
     * @return an InternalSensor instance (as this method is private, and therefore only used in this class, it can
     * return a model object)
     */
    @NotNull
    private InternalSensor getInternalSensor(String id) {
        Optional<InternalSensor> internalSensorOpt = repo.findById(id);
        if (!internalSensorOpt.isPresent()) {
            throw new InternalSensorNotFoundException("There is no internal sensor with id " + id);
        }
        return internalSensorOpt.get();
    }

    /**
     * This method receives an InternalSensorDTO, checks if exists a room with the roomId passed as
     * the instance attribute, if not a RoomNotFoundException is thrown. Afterwards checks if
     * exists a sensorType with the sensorTypeId passed as parameter, if not a SensorTypeNotFoundException is thrown.
     * If both preconditions mentioned previously are met, then it will retrieve an internalSensor instance and persist
     * it in the database. If so, it will retrieve an internalSensorDTO, so that the model layer is not exposed by
     * this method in other classes.
     *
     * @param internalSensorDTO to be mapped to an InternalSensor instance
     * @return an InternalSensorDTO if it is successfully mapped to an InternalSensor and persisted in the database.
     */
    public InternalSensorDTO createInternalSensor(InternalSensorDTO internalSensorDTO) {
        injectRepository();
        //checks if sensor's room exists
        if (!roomService.checkIfIDExists(internalSensorDTO.getRoomId())) {
            throw new RoomNotFoundException("Room with id " + internalSensorDTO.getRoomId() +
                    " does not exist.");
        }
        SensorBehaviorDTO sensorBehaviorDTO = internalSensorDTO.getSensorBehavior();
        //checks if sensor's type exists in the database

        if (!sensorTypeService.existsByID(sensorBehaviorDTO.getSensorType().getId())) {
            throw new SensorTypeNotFoundException("Sensor type with id " + sensorBehaviorDTO.getSensorType().getId() +
                    " does not exist.");
        }
        InternalSensor internalSensor = internalSensorMapper.toEntity(internalSensorDTO);
        repo.save(internalSensor);

        return internalSensorDTO;
    }

    /**
     * This method fetches all internal sensors in the database that belong to the room with the id specified.
     * If there is no room with the given id in the database, then a RoomNotFoundException is thrown.
     * @param idRoom String value representing the Room id
     * @return a list of InternalSensorDTOs that belong to the room with the given id.
     */
    public List<InternalSensorDTO> fetchSensorsInRoom(String idRoom) {
        this.injectRepository();
        List<InternalSensorDTO> sensorsInRoom = new ArrayList<>();
        if (!roomService.checkIfIDExists(idRoom)) {
            throw new RoomNotFoundException("Room with id " + idRoom +
                    " does not exist.");
        }
        repo.findAll().forEach(internalSensor -> {
            if (internalSensor.getRoomId().matches(idRoom)) {
                InternalSensorDTO dto = internalSensorMapper.toDto(internalSensor);
                sensorsInRoom.add(dto);
            }
        });
        return sensorsInRoom;
    }


    //===================//=======================//
    //TODO: replace this method accordingly
    public SensorList findByRoom(String idRoom) {
        Iterable<InternalSensor> internalSensors = Repositories.getInternalSensorRepository().findAll();
        SensorList sensorList = new SensorList();
        for (InternalSensor iSensor : internalSensors) {
            if (iSensor.getRoomId().equals(idRoom))
                sensorList.addSensor(iSensor);
        }

        return sensorList;
    }


}
