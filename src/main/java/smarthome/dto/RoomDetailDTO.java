package smarthome.dto;

import java.util.Objects;

public class RoomDetailDTO {
    private String id;
    private String description;
    private Integer floor;
    private double length;
    private double width;
    private double height;

    public RoomDetailDTO() {
    }

    public RoomDetailDTO(String id, String description, Integer floor, double length, double width, double height) {
        this.id = id;
        this.description = description;
        this.floor = floor;
        this.length = length;
        this.width = width;
        this.height = height;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomDetailDTO)) return false;
        RoomDetailDTO that = (RoomDetailDTO) o;
        return Double.compare(that.length, length) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0 &&
                id.equals(that.id) &&
                description.equals(that.description) &&
                floor.equals(that.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, floor, length, width, height);
    }
}
