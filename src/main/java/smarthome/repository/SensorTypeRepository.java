package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import smarthome.model.SensorType;
import smarthome.model.validations.Name;
@Repository
public interface SensorTypeRepository extends CrudRepository<SensorType, Long> {
    boolean existsByType(Name type);
    SensorType findByType(Name type);



}
