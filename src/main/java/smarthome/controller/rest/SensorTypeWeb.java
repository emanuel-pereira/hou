package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.SensorTypeDTO;
import smarthome.services.SensorTypeService;

import java.util.List;

@RestController
@RequestMapping(path = "/sensorTypes")

public class SensorTypeWeb {

    private final SensorTypeService sensorTypeService;

    SensorTypeWeb(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;

    }

    @PostMapping
    public ResponseEntity<Object> createSensorType(@RequestBody SensorTypeDTO type) {

        if (this.sensorTypeService.createSensorType(type)) {
            return new ResponseEntity<>("Type is successfully created with id "+type.getId(), HttpStatus.CREATED);
        } else return new ResponseEntity<>("Type wasn't created", HttpStatus.CONFLICT);
    }


   /* public boolean existsByType(String type) {
        return this.sensorTypeService.existsByType(type);
    }*/

    @GetMapping
    public List<SensorTypeDTO> listOfSensorTypesDTOs() {
      /*  if (this.sensorTypeService.findAll().isEmpty())
            return new ResponseEntity<>("The sensor type is empty",Http)*/
        return this.sensorTypeService.findAll();
    }

    @GetMapping("/{id}")
    public SensorTypeDTO findById(@PathVariable Long id) {
        return this.sensorTypeService.findById(id);
    }
}
