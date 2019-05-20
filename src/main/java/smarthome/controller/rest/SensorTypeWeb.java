package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.SensorTypeDTO;
import smarthome.services.SensorTypeService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/sensorTypes")

public class SensorTypeWeb {

    private final SensorTypeService sensorTypeRepoDDD;

    SensorTypeWeb() {
        sensorTypeRepoDDD= new SensorTypeService();

    }

    @PostMapping
    public ResponseEntity<Object> createSensorType(@RequestBody SensorTypeDTO type) throws ParseException {

        if (this.sensorTypeRepoDDD.createSensorType(type)) {
            return new ResponseEntity<>("Type is successfully created", HttpStatus.CREATED);
        } else return new ResponseEntity<>("Type wasn't created", HttpStatus.UNAUTHORIZED);
    }


    public boolean existsByType(String type) {
        return this.sensorTypeRepoDDD.existsByType(type);
    }



    @GetMapping
    public List<SensorTypeDTO> listOfSensorTypesDTOs() {
        return this.sensorTypeRepoDDD.findAll();
    }

    @GetMapping("/{id}")
    public SensorTypeDTO findById(@PathVariable Long id) {
        return this.sensorTypeRepoDDD.findById(id);
    }
}
