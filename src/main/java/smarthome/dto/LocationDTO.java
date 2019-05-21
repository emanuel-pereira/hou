package smarthome.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import smarthome.model.Location;

@JsonSerialize
public class LocationDTO {
    private double latitude;
    private double longitude;
    private double altitude;


    public LocationDTO() {

    }

    public LocationDTO(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Location fromDTO() {

        return new Location(this.latitude, this.longitude, this.altitude);
    }
}