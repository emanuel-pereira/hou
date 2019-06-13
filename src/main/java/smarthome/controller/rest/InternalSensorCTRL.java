package smarthome.controller.rest;

import org.springframework.web.bind.annotation.*;

import smarthome.dto.SensorDTO;
import smarthome.services.InternalSensorService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping("rooms/{room_id}/sensors")
public class InternalSensorCTRL {

    private InternalSensorService service = new InternalSensorService(); //Could also use WebService, etc, etc.

    @GetMapping
    List<SensorDTO> all(@PathVariable String room_id) {
        return service.findAllBy(room_id);
    }

    @PostMapping
    SensorDTO add(@RequestBody SensorDTO newSensor) {
        return service.create(newSensor);
    }

    @GetMapping("/{id}")
    SensorDTO one(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    SensorDTO replace(@RequestBody SensorDTO newSensor, @PathVariable String id) {
        return service.replace(newSensor, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        service.deleteById(id);
    }

}
