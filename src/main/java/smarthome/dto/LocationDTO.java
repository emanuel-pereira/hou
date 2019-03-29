package smarthome.dto;

import smarthome.model.Location;

public class LocationDTO {
    private double latitude;
    private double longitude;
    private double altitude;

    public LocationDTO(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public Location fromDTO() {
        return new Location(this.latitude, this.longitude, this.altitude);
    }
}