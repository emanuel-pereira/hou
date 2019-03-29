package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import smarthome.model.SensorType;

public interface SensorTypeRepository extends CrudRepository<SensorType, Long> {
}
