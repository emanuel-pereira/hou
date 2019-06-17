package smarthome.controller.rest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.services.GeoAreaService;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002"}, maxAge = 3600)
@RestController
@RequestMapping("/geoareas")
public class GeoAreaCTRL {


    private GeoAreaService geoAreaService;


    GeoAreaCTRL() {
        geoAreaService = new GeoAreaService();
    }


    @GetMapping
    public ResponseEntity<Resources<Resource<GeographicalAreaDTO>>> findAllGeoAreas() throws NoSuchFieldException {

        List<GeographicalAreaDTO> geoAreas = geoAreaService.findAll();
        List<Resource<GeographicalAreaDTO>> resources = new ArrayList<>();

        for (GeographicalAreaDTO geographicalAreaDTO : geoAreas) {
            Resource<GeographicalAreaDTO> geoAreaResource = new Resource<>(geographicalAreaDTO,
                    linkTo(methodOn(GeoAreaCTRL.class).findGeoArea(geographicalAreaDTO.getIdentification())).withRel("geoArea"));
            resources.add(geoAreaResource);
        }

        Resources<Resource<GeographicalAreaDTO>> resourceList = new Resources<>(resources,
                linkTo(methodOn(GeoAreaCTRL.class).findAllGeoAreas()).withSelfRel());

        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource<GeographicalAreaDTO>> findGeoArea(@PathVariable String id) throws NoSuchFieldException {

        GeographicalAreaDTO geographicalArea = geoAreaService.findById(id);

        Resource<GeographicalAreaDTO> resource = new Resource<>(geographicalArea,
                linkTo(methodOn(GeoAreaCTRL.class).findGeoArea(id)).withSelfRel(),
                linkTo(methodOn(GeoAreaCTRL.class).findAllGeoAreas()).withRel("geoAreas"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> addGeoArea(@RequestBody GeographicalAreaDTO geoAreaDto) throws InvalidParameterException {

        GeographicalAreaDTO responseDto;

        try {
            responseDto = this.geoAreaService.addNewGeoArea(geoAreaDto);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.PRECONDITION_FAILED);

        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }


    @PostMapping("/setParent")
    public ResponseEntity<Object> setParentOfGAWebCTRL(@RequestParam String id, String idParent) {

        if (this.geoAreaService.setParentGaWebCTRL(id, idParent)) {
            return new ResponseEntity<>("The Geographical area was set to another", HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>("The Geographical area wasn't set to another", HttpStatus.BAD_REQUEST);
    }

}
