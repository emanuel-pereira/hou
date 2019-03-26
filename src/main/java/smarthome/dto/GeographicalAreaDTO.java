package smarthome.dto;


import java.util.ArrayList;
import java.util.List;

public class GeographicalAreaDTO {

    private String identification;
    private String designation;
    private List<SensorDTO> sensorListDTO= new ArrayList<>();

    public GeographicalAreaDTO(){}
    public GeographicalAreaDTO(String identification, String designation, List<SensorDTO> sensorListDTO) {
        this.identification = identification;
        this.designation = designation;
        this.sensorListDTO = sensorListDTO;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<SensorDTO> getSensorListDTO() {
        return sensorListDTO;
    }

    public void setSensorListDTO(List<SensorDTO> sensorList) {
        this.sensorListDTO = sensorList;
    }
}
