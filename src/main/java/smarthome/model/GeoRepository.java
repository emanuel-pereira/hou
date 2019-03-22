package smarthome.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GeoRepository extends CrudRepository<GeographicalArea, String> {

    List<GeographicalArea> findByDesignation(String designation);
}
