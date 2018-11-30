package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GeographicalAreaTest {

    @Test
    void GeographicalAreaConstructorStreet() {
        GeographicalArea GA = new GeographicalArea("Aliados", TypeOfGeographicalArea.STREET);
    }

    @Test
    public void defineTypesOfGeograficalAreaVillage() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.VILLAGE;
        String geographicalAreaName = "Paranhos";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @Test
    public void defineTypesOfGeograficalAreaCity() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.CITY;
        String geographicalAreaName = "Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @Test
    public void defineTypesOfGeograficalAreaDistrict() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.DISTRICT;
        String geographicalAreaName = "Area Metropolitana do Porto";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

    @Test
    public void defineTypesOfGeograficalAreaCountry() {
        TypeOfGeographicalArea tga = TypeOfGeographicalArea.COUNTRY;
        String geographicalAreaName = "Portugal";
        GeographicalArea GA = new GeographicalArea(geographicalAreaName, tga);
    }

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
