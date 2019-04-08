package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import smarthome.model.Sensor;


@Repository
public interface SensorRepository extends CrudRepository<Sensor, String> {

}
