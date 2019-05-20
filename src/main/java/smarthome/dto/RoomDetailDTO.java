package smarthome.dto;

import smarthome.model.OccupationArea;

import javax.persistence.Embedded;

public class RoomDetailDTO {
    private String id;
    private String description;
    private Integer floor;
    private double length;
    private double width;
    private Double height;

    public RoomDetailDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
