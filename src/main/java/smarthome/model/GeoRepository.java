package smarthome.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GeoRepository extends CrudRepository<GeographicalArea, String> {

    List<GeographicalArea> findByDesignation(String designation);
}
