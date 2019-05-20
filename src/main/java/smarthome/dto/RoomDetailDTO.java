package smarthome.dto;

import smarthome.model.OccupationArea;

import javax.persistence.Embedded;

public class RoomDetailDTO {
    private String id;
    private String description;
    private Integer floor;
    private OccupationArea area;
    private Double height;

    public RoomDetailDTO(String id, String description, Integer floor, double length, double width, Double height) {
        this.id = id;
        this.description = description;
        this.description = description;
        this.floor = floor;
        this.area = new OccupationArea(length, width);
        this.height = height;
    }

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

    public OccupationArea getArea() {
        return area;
    }

    public void setArea(OccupationArea area) {
        this.area = area;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
