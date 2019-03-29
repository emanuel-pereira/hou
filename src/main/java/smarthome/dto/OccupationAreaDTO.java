package smarthome.dto;

import smarthome.model.OccupationArea;

public class OccupationAreaDTO {
    private double width;
    private double length;


    public OccupationAreaDTO(double width, double length) {
        this.width = width;
        this.length = length;

    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }

    public OccupationArea fromDTO() {
        return new OccupationArea(this.width, this.length);
    }
}