package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.ExternalSensor;
import smarthome.model.Sensor;

@Repository
public interface ExternalSensorRepository extends CrudRepository<ExternalSensor, String> {

}
