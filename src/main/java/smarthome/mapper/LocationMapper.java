package smarthome.mapper;

import smarthome.dto.LocationDTO;
import smarthome.model.Location;

public class LocationMapper {

    public LocationDTO toDTO(Location location){
        LocationDTO locationDTO= new LocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setAltitude(location.getAltitude());
        return locationDTO;
    }

    public Location toEntity(LocationDTO locationDTO){
        Location location=new Location();
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setAltitude(locationDTO.getAltitude());
        return location;
    }

}
