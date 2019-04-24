package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import smarthome.model.Sensors;


@Repository
public interface SensorRepository extends CrudRepository<Sensors, String> {

}
