package Sprint_0;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GeographicalAreaTest {

    @DisplayName("set street name and type of GA for street")
    @Test
    void GeographicalAreaConstructorStreet() {
        GeographicalArea GA = new GeographicalArea("Aliados", TypeOfGeographicalArea.STREET);
    }

    @DisplayName("set village name and type of GA for village")
    @Test
    public void defineTypesOfGeograficalAreaVillage() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.VILLAGE;
        String geographicalAreaName = "Paranhos";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set city name and type of GA for city")
    @Test
    public void defineTypesOfGeograficalAreaCity() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.CITY;
        String geographicalAreaName = "Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set district name and type of GA for district")
    @Test
    public void defineTypesOfGeograficalAreaDistrict() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.DISTRICT;
        String geographicalAreaName = "Area Metropolitana do Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set country name and type of GA for country")
    @Test
    public void defineTypesOfGeograficalAreaCountry() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.COUNTRY;
        String geographicalAreaName = "Portugal";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @DisplayName("set country name and type of GA for country and set of a specific position")
    @Test
    public void defineTypesOfGeograficalAreaCityLocation() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.CITY;
        String geographicalAreaName = "Portugal";
        Location location = new Location(12.3,35.2,120);
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga, location);

        double[] expectedLocation = {12.3,35.2,120};
        double[] result = location.getLocation();

        assertArrayEquals(expectedLocation, result);
    }
}
