package smarthome.dto;


import java.util.List;

public class GeographicalAreaDTO {

    private String identification;
    private String designation;
    private List<SensorDTO> sensorList;

    public GeographicalAreaDTO(String identification, String designation, List<SensorDTO> sensorList) {
        this.identification = identification;
        this.designation = designation;
        this.sensorList=sensorList;
    }

    public String getIdentification() {
        return identification;
    }

    public String getDesignation() {
        return designation;
    }

    public List<SensorDTO> getSensorListDTO() {
        return sensorList;
    }
}
