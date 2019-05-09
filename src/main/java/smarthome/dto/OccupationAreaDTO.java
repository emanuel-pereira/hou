package smarthome.dto;

import smarthome.model.OccupationArea;

public class OccupationAreaDTO {
    private final double width;
    private final double length;


    public OccupationAreaDTO(double length,double width) {
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
        return new OccupationArea(this.length,this.width);
    }
}