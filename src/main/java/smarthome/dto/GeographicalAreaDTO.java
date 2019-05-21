package smarthome.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import smarthome.model.GeographicalArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonSerialize
public class GeographicalAreaDTO {

    private String identification;
    private String designation;
    private TypeGADTO type;
    private OccupationAreaDTO occupationDto;
    private LocationDTO locationDto;
    private List<SensorDTO> sensorListDTO = new ArrayList<>();
    private GeographicalArea parentGA;

    public GeographicalAreaDTO() {
    }

    public GeographicalAreaDTO(String identification, String designation, List<SensorDTO> sensorListDTO) {
        this.identification = identification;
        this.designation = designation;
        this.sensorListDTO = sensorListDTO;
    }

    public GeographicalAreaDTO(String identification, String designation, TypeGADTO type, OccupationAreaDTO occupationDto, LocationDTO locationDto, List<SensorDTO> sensorListDTO) {
        this.identification = identification;
        this.designation = designation;
        this.type = type;
        this.occupationDto = occupationDto;
        this.locationDto = locationDto;
        this.sensorListDTO = sensorListDTO;
    }

    /**
     * Method to get the parent of an geographical area in DTO.
     * @return the parent of an geographical area.
     */
    public GeographicalArea getParentGaDto(){
        return parentGA;
    }

    /**
     * Method to set the parent of an geographical area in DTO.
     * @param geographicalArea is set as a parentGa.
     */
    public void setParentGADto(GeographicalArea geographicalArea){
        this.parentGA = geographicalArea;
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

    public OccupationAreaDTO getOccupation() {
        return occupationDto;
    }

    public void setOccupation(OccupationAreaDTO occupationDto) {
        this.occupationDto = occupationDto;
    }

    public LocationDTO getLocation() {
        return locationDto;
    }

    public void setLocation(LocationDTO locationDto) {
        this.locationDto = locationDto;
    }

    public List<SensorDTO> getSensorListDTO() {
        return sensorListDTO;
    }

    public void setSensorListDTO(List<SensorDTO> sensorList) {
        this.sensorListDTO = sensorList;
    }


    public GeographicalArea fromDTO() {

        return new GeographicalArea(this.identification, this.designation, this.type.fromDTO(), this.occupationDto.fromDTO(), this.locationDto.fromDTO());
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
