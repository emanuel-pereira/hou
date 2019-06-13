package smarthome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.GeographicalArea;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class GeoAreaService {


    private GeographicalAreaMapper geoMapper;


    /**
     * Constructor initialize GeoAreaService
     */

    @Autowired
    public GeoAreaService() {
        this.geoMapper = new GeographicalAreaMapper();
    }


    /**
     * Method to check if the id of the GA to set in another exists.
     *
     * @param id       of requested GA1
     * @param idParent of requested GA2
     * @return true or false.
     */

    public boolean setParentGaWebCTRL(String id, String idParent) {

        if (!checkIfIdExists(id)) {
            return false;
        }

        GeographicalArea GA1 = findByIdGa(id);
        GeographicalArea GA2 = findByIdGa(idParent);

        if (!GA1.equals(GA2))
            GA1.setParentGa(GA2);

        Repositories.getGeoRepository().save(GA1);
        return true;
    }

    public void saveGA(GeographicalArea ga){

        Repositories.getGeoRepository().save(ga);
    }

    public GeographicalArea findByIdGa(String id) {
        return Repositories.getGeoRepository().findById(id).get();
    }

    public boolean checkIfIdExists(String id) {

        return Repositories.getGeoRepository().existsById(id);
    }

    public long size() {
        return Repositories.getGeoRepository().count();
    }

    public List<GeographicalAreaDTO> findAll() {

        Iterable<GeographicalArea> GAs = Repositories.getGeoRepository().findAll();
        List<GeographicalArea> GAList = new ArrayList<>();
        for (GeographicalArea geographicalArea : GAs) {
            GAList.add(geographicalArea);
        }
        return Collections.unmodifiableList(geoMapper.toDtoListParent(GAList));
    }


}
