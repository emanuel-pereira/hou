package smarthome.dto;

import smarthome.model.GenericName;

import java.util.Calendar;
import java.util.List;

public class SensorBehaviorDTO {

    private GenericNameDTO name;
    private SensorTypeDTO sensorType;
    private Calendar startDate;
    private Calendar pauseDate;
    private String unit;
    private boolean active;
    private List<ExternalReadingDTO> readingList;

    public SensorBehaviorDTO(){}

    public SensorBehaviorDTO(GenericNameDTO sensorName, Calendar startDate, SensorTypeDTO sensorType, String unit, List<ExternalReadingDTO> readings) {
        this.name = sensorName;
        this.startDate = startDate;
        this.sensorType = sensorType;
        this.unit = unit;
        this.active = true;
        this.readingList = readings;
    }

    public GenericNameDTO getName() {
        return name;
    }

    public void setName(GenericNameDTO name) {
        this.name = name;
    }

    public SensorTypeDTO getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorTypeDTO sensorType) {
        this.sensorType = sensorType;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(Calendar pauseDate) {
        this.pauseDate = pauseDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ExternalReadingDTO> getReadingList() {
        return readingList;
    }

    public void setReadingList(List<ExternalReadingDTO> readingList) {
        this.readingList = readingList;
    }
}
