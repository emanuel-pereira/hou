package smarthome.services;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoAreaService {


    GeographicalAreaMapper geoMapper;


    /**
     * Constructor initialize GeoAreaService
     */
    public GeoAreaService() {
        this.geoMapper = new GeographicalAreaMapper();
    }

    /**
     * Method to set the parent of one geographical area on an web controller.
     * @param GA1 is used to match with G2.
     * @param GA2 is used to match with G1.
     */

    public void setParentOfGaWebCTRL(GeographicalArea GA1, GeographicalArea GA2) {

        GeographicalAreaDTO ga1 = geoMapper.toDtoParent(GA1);
        GeographicalAreaDTO ga2 = geoMapper.toDtoParent(GA2);

        if (!ga1.equals(ga2))
            ga1.setParentGADto(GA1);
    }

    /**
     * Method to check if the id of the GA to set in another exists.
     * @param id of requested GA
     * @param designation of requested GA
     * @return true or false.
     */

    public boolean setParentGaDTO(String id, String designation) {

        if (checkIfIdExists(id)) {
            return false;
        }
        GeographicalArea GA1 = new GeographicalArea(id, designation).getParentGa();
        GeographicalArea GA2 = new GeographicalArea(id, designation).getParentGa();

        if (!GA1.equals(GA2))
            GA1.setParentGa(GA2);

        Repositories.getGeoRepository().save(GA1);
        Repositories.getGeoRepository().save(GA2);
        return true;
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
        return Collections.unmodifiableList(geoMapper.toDtoList((GAList) GAList));
        //return geoMapper.toDtoList((GAList) GAList);
    }


}
