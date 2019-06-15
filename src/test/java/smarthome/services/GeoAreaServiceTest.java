/*
package smarthome.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.*;
import smarthome.repository.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
class GeoAreaServiceTest {

    @Test
    void setParentGaWebCTRL() {


        GeoAreaService geoAreaService = new GeoAreaService();

        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);

        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);


        geoAreaService.setParentGaWebCTRL("01", "02");

        boolean result = geoAreaService.checkIfIdExists("01");

        assertTrue(result);
    }

    @Test
    void setParentGaWebCTRLNotExists() {

        GeoAreaService geoAreaService = new GeoAreaService();

        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);

        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);


        geoAreaService.setParentGaWebCTRL("01", "02");

        boolean result = geoAreaService.checkIfIdExists("03");

        assertFalse(result);

    }

    @Test
    void findByIdGa() {


        GeoAreaService geoAreaService = new GeoAreaService();

        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);
        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);
        geoAreaService.findByIdGa("01");

        Repositories.getGeoRepository().findById("01");

        GeographicalArea result = geoAreaService.findByIdGa("01");

        assertEquals("01",result.getIdentification());

    }

    @Test
    void checkIfIdExists() {

        GeoAreaService geoAreaService = new GeoAreaService();


        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);
        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);
        geoAreaService.findByIdGa("01");


        boolean result = Repositories.getGeoRepository().existsById("01");
        assertTrue (result);


    }

    @Test
    void size() {

        GeoAreaService geoAreaService = new GeoAreaService();


        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);

        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);
        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);
        geoAreaService.size();

        long expectedResult = 2;
        long result = Repositories.getGeoRepository().count();

        assertEquals(expectedResult,result);

    }

    @Test
    void findAll() {

        GeographicalAreaMapper geoMapper = new GeographicalAreaMapper();
        GeoAreaService geoAreaService = new GeoAreaService();
        List<GeographicalArea> GAList = new ArrayList<>();


        TypeGA type = new TypeGA("city");
        Repositories.getTypeGARepository().save(type);
        TypeGA typeGA = new TypeGA("Country");
        Repositories.getTypeGARepository().save(typeGA);

        OccupationArea oc = new OccupationArea(34, 33);
        Location loc = new Location(12, 24, 22);
        GeographicalArea GA1 = new GeographicalArea("01", "Porto", type, oc, loc);
        GeographicalArea GA2 = new GeographicalArea("02", "Portugal", typeGA, oc, loc);
        geoAreaService.saveGA(GA1);
        geoAreaService.saveGA(GA2);

        //GeographicalAreaDTO GaDto1 = ((GeographicalAreaDTO.class.cast(GA1))); new GeographicalAreaDTO();
        //GeographicalAreaDTO GaDto2 = ((GeographicalAreaDTO.class.cast(GA2))); new GeographicalAreaDTO();
        //geoAreaService.findAll().add(1, GaDto1);
        //geoAreaService.findAll().add(2, GaDto2);
        //List<GeographicalAreaDTO> expectedResult = geoMapper.toDtoListParent(GAList);
        //List<GeographicalAreaDTO> result = Collections.unmodifiableList(geoMapper.toDtoListParent(GAList));

        Iterable<GeographicalAreaDTO> expectedResult = geoMapper.toDtoListParent(GAList);
        Iterable<GeographicalAreaDTO> result =  Collections.unmodifiableList(geoMapper.toDtoListParent(GAList));

        assertEquals(expectedResult,result);

    }
}*/
