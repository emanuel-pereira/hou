package smarthome.dto;

import java.util.Calendar;
import java.util.List;

public class InternalSensorBehaviorDTO {
    private GenericNameDTO name;
    private SensorTypeDTO sensorType;
    private Calendar startDate;
    private Calendar pauseDate;
    private String unit;
    private boolean active;
    private List<InternalReadingDTO> readingList;

    public InternalSensorBehaviorDTO(){}

    public InternalSensorBehaviorDTO(GenericNameDTO sensorName, Calendar startDate, SensorTypeDTO sensorType, String unit, List<InternalReadingDTO> readings) {
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

    public List<InternalReadingDTO> getReadingList() {
        return readingList;
    }

    public void setReadingList(List<InternalReadingDTO> readingList) {
        this.readingList = readingList;
    }
}
