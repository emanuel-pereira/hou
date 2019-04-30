package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.InternalSensor;
import smarthome.model.Sensor;

@Repository
public interface InternalSensorRepository extends CrudRepository<InternalSensor, String> {

}
