package smarthome.controller.rest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.InternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.exceptions.InternalSensorNotFoundException;
import smarthome.exceptions.RoomNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.services.InternalSensorService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping("/internalSensors")
public class InternalSensorCTRL {

    private InternalSensorService service = new InternalSensorService(); //Could also use WebService, etc, etc.

    /**
     * This method retrieves all internal sensors in the database and wraps them as resources so that they may have
     * URI links.
     *
     * @return a response entity containing resources, which contain InternalSensorDTO objects and respective links,
     * as well as the respective HTTP.Status 200 OK.
     */
    @GetMapping
    public HttpEntity<Resources<Resource<InternalSensorDTO>>> findAll() {
        Resources<Resource<InternalSensorDTO>> resources = Resources.wrap(service.findAll());
        resources.forEach(resource ->
                resource.add(linkTo(methodOn(InternalSensorCTRL.class).get(resource.getContent().getId())).withSelfRel()));
        resources.add(linkTo(methodOn(InternalSensorCTRL.class).findAll()).withSelfRel());

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * HTTP GET request mapping to retrieve an internal sensor with a specific id.
     *
     * @param id String value representing the internal sensor's id
     * @return ResponseEntity including a resource with HATEOAS and HTTP status OK. If there is no InternalSensor with the
     * specified id, then it  an InternalSensorNotFoundException is caught returning a resource with a message informing
     * the user that there is no InternalSensor available with that id and returns HTTPStatus.NotFound as well.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource<Object>> get(@PathVariable String id) {
        InternalSensorDTO dto;
        Resource<Object> internalSensorResource = null;
        try {
            dto = service.get(id);
            internalSensorResource = new Resource<>(dto, linkTo(methodOn(InternalSensorCTRL.class).findAll()).withRel("InternalSensors"));
        } catch (InternalSensorNotFoundException internalSensorNotFoundException) {
            return new ResponseEntity<>(new Resource<>("The requested internal sensor with the id "
                    + id +
                    " does not exist in the database."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(internalSensorResource, HttpStatus.OK);
    }

    /**
     * This method retrieves all internal sensors in the database that belong to the room with the roomId given as
     * parameter and wraps them as resources so that they may have URI links.
     * If no room is found with the given id, then a RoomNotFoundException is caught
     * @param roomId String value representing the room id
     * @return a response entity containing resources, which contain InternalSensorDTO objects and respective links,
     * as well as the respective HTTP.Status 200 OK. If a RoomNotFoundException is caught, then it retrieves a response
     * entity informing the user plus the HTTP status code Not Found.
     */
    @GetMapping("/{roomId}/room")
    public HttpEntity<Resources<Resource<InternalSensorDTO>>> fetchSensorsInRoom(@PathVariable String roomId) {
        Resources<Resource<InternalSensorDTO>> resources = null;
        try {
            resources = Resources.wrap(service.fetchSensorsInRoom(roomId));
            resources.forEach(resource ->
                    resource.add(linkTo(methodOn(InternalSensorCTRL.class).get(resource.getContent().getId())).withSelfRel()));
            resources.add(linkTo(methodOn(InternalSensorCTRL.class).findAll()).withSelfRel());

        } catch (RoomNotFoundException roomNotFoundException) {
            return new ResponseEntity(new Resource<>("Room with id " + roomId +
                    " does not exist."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * HTTP POST method containing an InternalSensorDTO, checks if exists a room with the roomId passed as
     * the instance attribute, if not a RoomNotFoundException is caught.
     * Afterwards checks if exists a sensorType with the sensorTypeId passed as parameter, if not a
     * SensorTypeNotFoundException is caught.
     * If both preconditions mentioned previously are met, then it will save the internal sensor
     * in the database.
     *
     * @param internalSensorDTO containing the object passed as parameter
     * @return a response entity with a resource, containing the internalSensorDTO when the respective entity is
     * persisted as well as it URI link, and the respective HTTP status code OK. If a RoomNotFoundException
     * is caught, then it retrieves a response entity informing the user plus the HTTP status code precondition failed.
     * If a SensorTypeNotFoundException is caught then it retrieves a response entity informing the user plus the
     * HTTP status code precondition failed.
     */
    @PostMapping
    ResponseEntity<Resource<Object>> create(@RequestBody InternalSensorDTO internalSensorDTO) {
        Resource<Object> resource = null;
        try {
            service.createInternalSensor(internalSensorDTO);
            resource = new Resource<>(internalSensorDTO);
            resource.add(linkTo(methodOn(InternalSensorCTRL.class).get(internalSensorDTO.getId())).withSelfRel());
        } catch (RoomNotFoundException roomNotFoundException) {
            resource = new Resource<>("Room with id " + internalSensorDTO.getRoomId() + " does not exist.");
            return new ResponseEntity<>(resource, HttpStatus.PRECONDITION_FAILED);
        } catch (SensorTypeNotFoundException sensorTypeNotFoundException) {
            SensorBehaviorDTO sensorBehavior = internalSensorDTO.getSensorBehavior();
            resource = new Resource<>("Sensor type with id " + sensorBehavior.getSensorType().getId() + " does not exist.");
            return new ResponseEntity<>(resource, HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
