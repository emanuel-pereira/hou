package smarthome.dto;

import org.junit.jupiter.api.Test;
import smarthome.model.Location;

import static org.junit.jupiter.api.Assertions.*;

class LocationDTOTest {

    @Test
    void getLatitude() {
        LocationDTO location = new LocationDTO(1,2,3);

        double result = location.getLatitude();

        assertEquals(1,result);
    }

    @Test
    void getLongitude() {
        LocationDTO location = new LocationDTO(1,2,3);

        double result = location.getLongitude();

        assertEquals(2,result);
    }

    @Test
    void getAltitude() {
        LocationDTO location = new LocationDTO(1,2,3);

        double result = location.getAltitude();

        assertEquals(3,result);
    }

    @Test
    void fromDTO(){
        LocationDTO location = new LocationDTO(1,2,3);

        Location result = location.fromDTO();

        assertEquals(location.getLatitude(),result.getLatitude());
        assertEquals(location.getLongitude(),result.getLongitude());
        assertEquals(location.getAltitude(),result.getAltitude());
    }
}