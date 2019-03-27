package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.Sensor;


@Component
public interface SensorRepository extends CrudRepository<Sensor, String> {

}
