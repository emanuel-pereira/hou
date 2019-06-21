package smarthome.controller.rest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.exceptions.ExternalSensorNotFoundException;
import smarthome.exceptions.GeographicalAreaNotFoundException;
import smarthome.exceptions.SensorTypeNotFoundException;
import smarthome.dto.ExternalSensorDTO;
import smarthome.dto.SensorBehaviorDTO;
import smarthome.services.ExternalSensorService;

import java.security.InvalidParameterException;
import java.util.Calendar;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping("/externalSensors")
public class ExternalSensorCTRL {

    private final ExternalSensorService service;

    public ExternalSensorCTRL(ExternalSensorService service) {
        this.service = service;
    }

    /**
     * This method retrieves all external sensors in the database and wraps them as resources so that they may have
     * URI links.
     *
     * @return a response entity containing resources, which contain ExternalSensorDTO objects and respective links,
     * as well as the respective HTTP.Status 200 OK.
     */
    @GetMapping
    public HttpEntity<Resources<Resource<ExternalSensorDTO>>> findAll() {
        Resources<Resource<ExternalSensorDTO>> resources = Resources.wrap(service.findAll());
        resources.forEach(resource ->
                resource.add(linkTo(methodOn(ExternalSensorCTRL.class).get(resource.getContent().getId())).withSelfRel()));
        resources.add(linkTo(methodOn(ExternalSensorCTRL.class).findAll()).withSelfRel());

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * HTTP GET request mapping to retrieve an external sensor with a specific id.
     *
     * @param id String value representing the external sensor's id
     * @return ResponseEntity including a resource with HATEOAS and HTTP status OK. If there is no ExternalSensor with the
     * specified id, then it throws an ExternalSensorNotFoundException returning a resource with a message informing
     * the user that there is no ExternalSensor available with that id and returns HTTPStatus.NotFound as well.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource<Object>> get(@PathVariable String id) {
        ExternalSensorDTO dto;
        Resource<Object> externalSensorResource;
        try {
            dto = service.get(id);
            externalSensorResource = new Resource<>(dto, linkTo(methodOn(ExternalSensorCTRL.class).findAll()).withRel("ExternalSensors"));
        } catch (ExternalSensorNotFoundException externalSensorNotFoundException) {
            return new ResponseEntity<>(new Resource<>("The requested external sensor with the id "
                    + id +
                    " does not exist in the database."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(externalSensorResource, HttpStatus.OK);
    }

    /**
     * HTTP DELETE method request to delete an external sensor with the id given as parameter.
     * This method starts by looking for an external sensor with the given id in the database and when it is found,
     * then this object is deleted from the database.
     * Otherwise, if there is no external sensor with that id in the database, then an ExternalSensorNotFoundException
     * is thrown.
     *
     * @param id String value parameter representing the external sensor's id.
     * @return a response entity containing an empty resource if the sensor is deleted from the database plus the
     * HTTP Status code 204 No Content. If there is none sensor with that id in the database, then it returns a
     * message informing the user plus the HTTP Status code 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Resource<Object>> removeSensor(@PathVariable String id) {
        Resource<Object> externalSensorResource = null;
        try {
            service.removeSensor(id);
        } catch (ExternalSensorNotFoundException externalSensorNotFoundException) {
            return new ResponseEntity<>(new Resource<>("The requested external sensor with the id "
                    + id +
                    " does not exist in the database."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(externalSensorResource, HttpStatus.NO_CONTENT);
    }

    /**
     * HTTP PATCH method request to deactivate an external sensor with the id given as parameter.
     * This method starts by looking for an external sensor with the given id in the database and when it is found,
     * checks if the sensor status active is set as true, as well as it checks if the dateAndTime parameter is after the
     * sensor's startDate. If so, then the external sensor Active state is set as false as well as sets its pauseDate
     * saving this changes in the database.
     *
     * @param id        String value parameter representing the external sensor's id.
     * @param pauseDate Calendar dataType parameter to set the pauseDate of an external sensor
     * @return if the sensor is successfully deactivated then it retrieves a resource, containing the externalSensor
     * instance plus the respective link, and returns the respective HTTP status code OK.
     * If the external sensor with the id is found but if its active state is already set as false, then an
     * InvalidParameterException is caught and returns a resource with a message informing the user plus the respective
     * HTTP status code PRECONDITION_FAILED. Otherwise, if the @param pauseDate is before
     * the external sensor's start date, then a InvalidParameterException is caught and returns a resource with a message
     * informing the user about this plus the respective HTTP status code PRECONDITION_FAILED.
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Resource<Object>> deactivate(@PathVariable String id, @RequestParam(name = "dateAndTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar pauseDate) {
        ExternalSensorDTO dto;
        Resource<Object> externalSensorResource;
        try {
            dto = service.deactivate(id, pauseDate);
            externalSensorResource = new Resource<>(dto, linkTo(methodOn(ExternalSensorCTRL.class).findAll()).withRel("ExternalSensors"));
        } catch (InvalidParameterException invalidParameterException) {
            return new ResponseEntity<>(new Resource<>("The date inputted as pauseDate cannot be before the sensor's start date."), HttpStatus.PRECONDITION_FAILED);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(new Resource<>("The sensor with id " + id + " is already inactive."), HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>(externalSensorResource, HttpStatus.OK);
    }


    /**
     * HTTP POST method containing an ExternalSensorDTO, checks if exists a geographical area with the IdGA passed as
     * the instance attribute, if not a GeographicalAreaNotFoundException is caught.
     * Afterwards checks if exists a sensorType with the sensorTypeId passed as parameter, if not a
     * SensorTypeNotFoundException is caught.
     * If both preconditions mentioned previously are met, then it will save the external sensor
     * in the database.
     *
     * @param externalSensorDTO containing the object passed as parameter
     * @return a response entity with a resource, containing the externalSensorDTO when the respective entity is
     * persisted as well as it URI link, and the respective HTTP status code OK. If a GeographicalAreaNotFoundException
     * is caught, then it retrieves a response entity informing the user plus the HTTP status code precondition failed.
     * If a SensorTypeNotFoundException is caught then it retrieves a response entity informing the user plus the
     * HTTP status code precondition failed.
     */
    @PostMapping
    public ResponseEntity<Resource<Object>> add(@RequestBody ExternalSensorDTO externalSensorDTO) {
        Resource<Object> resource;
        try {
            service.createExternalSensor(externalSensorDTO);
            resource = new Resource<>(externalSensorDTO);
            resource.add(linkTo(methodOn(ExternalSensorCTRL.class).get(externalSensorDTO.getId())).withSelfRel());
        } catch (GeographicalAreaNotFoundException gaNotFoundException) {
            resource = new Resource<>("Geographical area with id " + externalSensorDTO.getIdGA() + " does not exist.");
            return new ResponseEntity<>(resource, HttpStatus.PRECONDITION_FAILED);
        } catch (SensorTypeNotFoundException sensorTypeNotFoundException) {
            SensorBehaviorDTO sensorBehavior = externalSensorDTO.getSensorBehaviorDTO();
            resource = new Resource<>("Sensor type " + sensorBehavior.getSensorType().getType() + " does not exist.");
            return new ResponseEntity<>(resource, HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
