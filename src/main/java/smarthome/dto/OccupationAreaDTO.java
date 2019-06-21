package smarthome.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import smarthome.model.OccupationArea;

@JsonSerialize
public class OccupationAreaDTO {
    private double width;
    private double length;



    public OccupationAreaDTO(double length, double width) {
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
        return new OccupationArea(this.length, this.width);
    }
}