package smarthome.controller.rest;

import org.springframework.web.bind.annotation.*;
import smarthome.dto.SensorDTO;
import smarthome.services.ExternalSensorService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/externalsensors")
public class ExternalSensorCTRL {

    private ExternalSensorService service= new ExternalSensorService();


    @GetMapping
    List<SensorDTO> all() {
        return service.findAll();
    }

/*    @GetMapping("/")
    List<SensorDTO> allIn(@PathParam ("geoArea") String geoArea) {
        return service.findAllBy(geoArea);
    }*/

    @GetMapping("/{id}")
    SensorDTO one(@PathVariable String id) {
        return service.findById(id);
    }


    @PostMapping
    SensorDTO add(@RequestBody SensorDTO newSensor) {
        return service.create(newSensor);
    }

    @PutMapping("/{id}")
    SensorDTO replace(@RequestBody SensorDTO newSensor, @PathVariable String id) {
        return service.replace(newSensor, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id, @PathParam("delete") boolean delete) {
        if (delete) {
            service.deleteById(id);
        }
    }


}
