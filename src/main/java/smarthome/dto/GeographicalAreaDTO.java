package smarthome.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.OccupationArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonSerialize
public class GeographicalAreaDTO {

    private String identification;
    private String designation;
    private TypeGADTO type;
    private OccupationArea occupationArea;
    private Location location;
    private List<SensorDTO> sensorListDTO = new ArrayList<>();
    private GeographicalArea parentGA;

    public GeographicalAreaDTO() {
    }

    public GeographicalAreaDTO(String identification, String designation, List<SensorDTO> sensorListDTO) {
        this.identification = identification;
        this.designation = designation;
        this.sensorListDTO = sensorListDTO;
    }


    public GeographicalAreaDTO(String identification, String designation, TypeGADTO type, OccupationArea occupationArea, Location location) {
        this.identification = identification;
        this.designation = designation;
        this.type = type;
        this.occupationArea = occupationArea;
        this.location = location;
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

    public TypeGADTO getType() {
        return type;
    }

    public void setType(TypeGADTO type) {
        this.type = type;
    }

    public OccupationArea getOccupation() {
        return occupationArea;
    }

    public void setOccupation(OccupationArea occupationDto) {
        this.occupationArea = occupationDto;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SensorDTO> getSensorListDTO() {
        return sensorListDTO;
    }

    public void setSensorListDTO(List<SensorDTO> sensorList) {
        this.sensorListDTO = sensorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeographicalAreaDTO)) {
            return false;
        }
        GeographicalAreaDTO that = (GeographicalAreaDTO) o;
        return Objects.equals(this.identification, that.identification) /*&&
                Objects.equals(this.designation, that.designation) &&
                Objects.equals(this.typeOfGa, that.typeOfGa)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.identification);
    }
}
