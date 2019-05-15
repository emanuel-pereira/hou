package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;

public interface SensorTypeRepository extends CrudRepository<SensorType, Long> {
    boolean existsByType(Name type);
    SensorType findByType(Name type);

}
