package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.TypeGADTO;
import smarthome.model.GeographicalArea;
import smarthome.repository.Repositories;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GeoAreaService {


    private ModelMapper mapper;

    /**
     * Constructor initialize GeoAreaService
     */

    public GeoAreaService() {

        this.mapper = new ModelMapper();


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

        GeographicalArea GA1 = Repositories.getGeoRepository().findById(id).get();
        GeographicalArea GA2 = Repositories.getGeoRepository().findById(idParent).get();

        if (!GA1.equals(GA2))
            GA1.setParentGa(GA2);

        Repositories.getGeoRepository().save(GA1);
        return true;
    }

    /*   public boolean setParentGaWebCTRL(String id, String idParent) {

        if (!checkIfIdExists(id)) {
            return false
        }

        GeographicalArea GA1 = findByIdGa(id)
        GeographicalArea GA2 = findByIdGa(idParent)

        if (!GA1.equals(GA2))
            GA1.setParentGa(GA2)

        Repositories.getGeoRepository().save(GA1)
        return true
    }*/


    public GeographicalAreaDTO addNewGeoArea(GeographicalAreaDTO geoAreaDTO) throws InvalidParameterException {


        TypeGADTO typeDto = geoAreaDTO.getType();

        if (!Repositories.getTypeGARepository().existsByType(typeDto.getType())) {
            throw new InvalidParameterException();
        }

        GeographicalArea newGeoArea = mapper.map(geoAreaDTO, GeographicalArea.class);
        newGeoArea.setType(Repositories.getTypeGARepository().findByType(typeDto.getType()));

        if (!geoAreaIsValid(newGeoArea)) {
            throw new InvalidParameterException();
        }
        Repositories.getGeoRepository().save(newGeoArea);
        return geoAreaDTO;

    }


    private boolean geoAreaIsValid(GeographicalArea geoArea) {

        return (geoArea.getIdentification() != null &&
                geoArea.getDesignation() != null &&
                geoArea.getType() != null &&
                geoArea.getLocation() != null &&
                geoArea.getOccupation() != null);

    }


    public GeographicalAreaDTO findById(String id) throws NoSuchFieldException {
        Optional<GeographicalArea> optional = Repositories.getGeoRepository().findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        GeographicalArea temp = optional.get();
        return this.mapper.map(temp, GeographicalAreaDTO.class);
    }


    public boolean checkIfIdExists(String id) {

        return Repositories.getGeoRepository().existsById(id);
    }

    public long size() {
        return Repositories.getGeoRepository().count();
    }

    public List<GeographicalAreaDTO> findAll() {
        List<GeographicalAreaDTO> geoAreas = new ArrayList<>();
        Repositories.getGeoRepository().findAll().forEach(geographicalArea -> {
            GeographicalAreaDTO temp = this.mapper.map(geographicalArea, GeographicalAreaDTO.class);
            geoAreas.add(temp);
        });
        return geoAreas;
    }


}
