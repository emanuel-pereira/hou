package smarthome.mapper;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.SensorList;

import java.util.List;
import java.util.stream.Collectors;

public class GeographicalAreaMapper {
    private final SensorMapper sensorMapper = new SensorMapper();


    /**
     * Converts a geographicalArea into a geographicalAreaDTO to be used as a model view, containing only Id, Designation and SensorList as attributes
     * @param geographicalArea to be converted in geographicalAreaDTO
     * @return a geographicalAreaDTO
     */
    public GeographicalAreaDTO toDto(GeographicalArea geographicalArea) {
        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setIdentification(geographicalArea.getId());
        geographicalAreaDTO.setDesignation(geographicalArea.getGAName());
        SensorList sensorList = geographicalArea.getSensorListInGA();
        geographicalAreaDTO.setSensorListDTO(sensorMapper.toDtoList(sensorList));
        return geographicalAreaDTO;
    }

    /**
     * Converts a list of geographical areas into a list of geographical areas DTOs to be used as a model view, containing only Id, Designation and SensorList as attributes
     * @param gaList to be converted into a geographical area DTO list
     * @return list of geographical area DTOs
     */
    public List<GeographicalAreaDTO> toDtoList(GAList gaList) {
        List<GeographicalArea> listOfGAs=gaList.getGAList();
        return listOfGAs.stream().map(this::toDto).collect(Collectors.toList());
    }

}
