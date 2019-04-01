package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.OccupationArea;

@Component
public interface OccupationAreaRepository extends CrudRepository<OccupationArea, Long> {

}
