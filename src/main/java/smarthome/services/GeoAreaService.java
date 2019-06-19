package smarthome.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.TypeGADTO;
import smarthome.model.GeographicalArea;
import smarthome.repository.GeoRepository;
import smarthome.repository.Repositories;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GeoAreaService {

    private ModelMapper mapper;
    private GeoRepository geoRepository;
    private GaTypesService gaTypesService;

    /**
     * Constructor initialize GeoAreaService
     */

    public GeoAreaService() {
        this.mapper = new ModelMapper();
    }

    public GeoAreaService(GeoRepository geoRepository, GaTypesService gaTypesService) {
        this.geoRepository = geoRepository;
        this.gaTypesService = gaTypesService;
        mapper = new ModelMapper();
    }

    private void injectRepository() {
        if (geoRepository == null)
            this.geoRepository = Repositories.getGeoRepository();
    }

    /**
     * Method to check if the id of the GA to set in another exists.
     *
     * @param id       of requested GA1
     * @param idParent of requested GA2
     * @return true or false.
     */


    public boolean setParentGaWebCTRL(String id, String idParent) {
        injectRepository();
        if (!checkIfIdExists(id)) {
            return false;
        }

        GeographicalArea ga1 = geoRepository.findById(id).get();
        GeographicalArea ga2 = geoRepository.findById(idParent).get();

        if (!ga1.equals(ga2))
            ga1.setParentGa(ga2);

        geoRepository.save(ga1);
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
        injectRepository();
        TypeGADTO typeDto = geoAreaDTO.getType();

        if (!gaTypesService.existsByType(typeDto.getType())) {
            throw new InvalidParameterException();
        }

        GeographicalArea newGeoArea = mapper.map(geoAreaDTO, GeographicalArea.class);
        newGeoArea.setType(gaTypesService.findByType(typeDto.getType()));

        if (!geoAreaIsValid(newGeoArea)) {
            throw new InvalidParameterException();
        }
        geoRepository.save(newGeoArea);
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
        injectRepository();
        Optional<GeographicalArea> optional = geoRepository.findById(id);
        if (!optional.isPresent())
            throw new NoSuchFieldException();
        GeographicalArea temp = optional.get();
        return this.mapper.map(temp, GeographicalAreaDTO.class);
    }


    public boolean checkIfIdExists(String id) {
        injectRepository();
        return geoRepository.existsById(id);
    }

    public long size() {
        injectRepository();
        return geoRepository.count();
    }

    public List<GeographicalAreaDTO> findAll() {
        injectRepository();
        List<GeographicalAreaDTO> geoAreas = new ArrayList<>();
        geoRepository.findAll().forEach(geographicalArea -> {
            GeographicalAreaDTO temp = this.mapper.map(geographicalArea, GeographicalAreaDTO.class);
            geoAreas.add(temp);
        });
        return geoAreas;
    }


}
