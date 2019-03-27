package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;

import java.util.List;

@Component
public interface SensorRepository extends CrudRepository<Sensor, String> {

}
