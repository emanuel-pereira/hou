package smarthome.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LocationRepository extends CrudRepository<Location, String> {

}
