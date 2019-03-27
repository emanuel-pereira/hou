package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.Location;

@Component
public interface LocationRepository extends CrudRepository<Location, Long> {

    boolean existsByAltitudeEquals(double altitude);
}
