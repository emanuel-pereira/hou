package smarthome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import smarthome.model.GeographicalArea;

import java.util.List;

@Component
public interface GeoRepository extends CrudRepository<GeographicalArea, String> {

    List<GeographicalArea> findByDesignation(String designation);
}
