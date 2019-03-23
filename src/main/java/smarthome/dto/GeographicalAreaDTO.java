package smarthome.dto;


import smarthome.model.*;

import java.util.List;

public class GeographicalAreaDTO {

    private String identification;
    private String designation;
    private String typeOfGa;
    private LocationDTO locationDTO;
    private List<SensorDTO> sensorListDTO;
    private OccupationAreaDTO occupationAreaDTO;


    public GeographicalAreaDTO(String identification, String designation, List<SensorDTO> sensorList) {
        this.identification = identification;
        this.designation = designation;
        this.sensorListDTO = sensorList;
    }

    public GeographicalAreaDTO(String identification, String designation, String typeOfGa, LocationDTO location, List<SensorDTO> sensorList, OccupationAreaDTO occupationArea) {
        this.identification = identification;
        this.designation = designation;
        this.typeOfGa = typeOfGa;
        this.locationDTO = location;
        this.sensorListDTO = sensorList;
        this.occupationAreaDTO = occupationArea;

    }


    public String getIdentification() {
        return identification;
    }

    public String getDesignation() {
        return designation;
    }

    public List<SensorDTO> getSensorListDTO() {
        return sensorListDTO;
    }

    public GeographicalArea fromDTO() {

        SensorList sensorList = new SensorList();
        OccupationArea occupation = this.occupationAreaDTO.fromDTO();
        Location location = this.locationDTO.fromDTO();

        for (SensorDTO sensorDTO : this.sensorListDTO) {
            Sensor sensor = sensorDTO.fromDTO();
            sensorList.addSensor(sensor);
        }
        return new GeographicalArea(this.identification, this.designation, this.typeOfGa, occupation, location);
    }

}


