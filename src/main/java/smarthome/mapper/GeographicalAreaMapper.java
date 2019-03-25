package smarthome.mapper;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.SensorList;
import java.util.List;

public class GeographicalAreaMapper {
    private SensorTypeMapper sensorTypeMapper;
    private SensorMapper sensorMapper=new SensorMapper();

    /**
     * Converts a geographicalArea into a geographicalAreaDTO to be used as a model view, containing only Id, Designation and SensorList as attributes
     * @param geographicalArea to be converted in geographicalAreaDTO
     * @return a geographicalAreaDTO
     */
    public GeographicalAreaDTO toDto(GeographicalArea geographicalArea) {
        GeographicalAreaDTO geographicalAreaDTO = new GeographicalAreaDTO();
        geographicalAreaDTO.setIdentification(geographicalArea.getIdentification());
        geographicalAreaDTO.setDesignation(geographicalArea.getGAName());
        SensorList sensorList = geographicalArea.getSensorListInGA();
        List<SensorDTO> sensorListDTO=geographicalAreaDTO.getSensorListDTO();
        for (Sensor sensor : sensorList.getSensorList()) {
            SensorDTO  sensorDTO=sensorMapper.toDto(sensor);
            sensorListDTO.add(sensorDTO);
        }
        return geographicalAreaDTO;
    }

}
