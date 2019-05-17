package smarthome.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smarthome.dto.SensorTypeDTO;
import smarthome.services.SensorTypeService;

import java.text.ParseException;
import java.util.List;

@RestController
public class SensorTypeWeb {

    private final SensorTypeService sensorTypeRepoDDD;

    SensorTypeWeb() {
        sensorTypeRepoDDD= new SensorTypeService();

    }
    
    @PostMapping("/sensorTypes")
    public ResponseEntity<Object> createSensorType(@RequestBody SensorTypeDTO type) throws ParseException {

        if (this.sensorTypeRepoDDD.createSensorType(type)) {
            return new ResponseEntity<>("Type is successfully created", HttpStatus.CREATED);
        } else return new ResponseEntity<>("Type wasn't created", HttpStatus.UNAUTHORIZED);
    }


    public boolean existsByType(String type) {
        return this.sensorTypeRepoDDD.existsByType(type);
    }



    @GetMapping("/sensorTypes")
    public List<SensorTypeDTO> listOfSensorTypesDTOs() {
        return this.sensorTypeRepoDDD.findAll();
    }
}
