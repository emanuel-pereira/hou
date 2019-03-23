package smarthome.dto;

import smarthome.model.*;


import java.util.Calendar;

import java.util.List;

public class SensorDTO {

    private String id;
    private String designation;
    private Calendar startDate;
    private LocationDTO locationDTO;
    private SensorTypeDTO sensorTypeDTO;
    private String unit;
    private List<ReadingDTO> readingListDTO;


    public SensorDTO(String id, String designation, List<ReadingDTO> readingListDTO) {
        this.id = id;
        this.designation = designation;
        this.readingListDTO = readingListDTO;
    }

    public SensorDTO(String id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public SensorDTO(String id, String designation, Calendar startDate, LocationDTO location, SensorTypeDTO sensorType, String unit, List<ReadingDTO> readingList) {
        this.id = id;
        this.designation = designation;
        this.startDate = startDate;
        this.locationDTO = location;
        this.sensorTypeDTO = sensorType;
        this.unit = unit;
        this.readingListDTO = readingList;

    }


    public String getId() {
        return this.id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public List<ReadingDTO> getReadingList() {
        return this.readingListDTO;

    }

    public Sensor fromDTO() {
        SensorType sensorType = this.sensorTypeDTO.fromDTO();
        Location location = this.locationDTO.fromDTO();
        ReadingList readingList = new ReadingList();

        return new Sensor(this.id, this.designation, this.startDate, location, sensorType, this.unit, readingList);
    }
}
