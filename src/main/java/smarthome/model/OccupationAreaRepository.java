package smarthome.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OccupationAreaRepository extends CrudRepository<OccupationArea, String> {

}
