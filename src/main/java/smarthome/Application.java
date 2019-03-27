package smarthome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.OccupationArea;
import smarthome.model.TypeGA;
import smarthome.repository.*;

import java.io.IOException;
import java.text.ParseException;

import static smarthome.io.ui.SmartHomeUI.menuOptions;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IllegalAccessException, ParseException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException {
        SpringApplication.run(Application.class);

        menuOptions();
    }


    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, LocationRepository locRep, TypeGARepository typeRep, SensorTypeRepository unitRep, OccupationAreaRepository occupRep, SensorRepository sensorRep) {
        Repositories.typeGARepository = typeRep;
        Repositories.locationRepository = locRep;
        Repositories.geoRepository = geoRep;
        Repositories.occupationAreaRepository = occupRep;
        Repositories.sensorTypeRepository = unitRep;
        Repositories.sensorRepository = sensorRep;

        TypeGA testType = new TypeGA("tipo de teste");
        Repositories.typeGARepository.save(testType);
        Location testLocation = new Location(-8.606409, 41.17923, 125);
        Repositories.locationRepository.save(testLocation);
        OccupationArea testOccupation = new OccupationArea(0.249, 0.064989);
        Repositories.occupationAreaRepository.save(testOccupation);
        GeographicalArea testArea = new GeographicalArea("testID", "testArea", testType, testOccupation, testLocation);
        Repositories.geoRepository.save(testArea);


        return args -> {
            /*// fetch all area types
            log.info("\u001B[31;1mArea types found with findAll():\u001B[0m");
            log.info("\u001B[31;1m-------------------------------");
            for (TypeGA typeGA : Repositories.typeGARepository.findAll()) {
                log.info(typeGA.toString());
            }
            log.info("");

            // fetch all locations
            log.info("\u001B[31;1mUsed Locations found with findAll():\u001B[0m");
            log.info("\u001B[31;1m-------------------------------");
            for (Location location: Repositories.locationRepository.findAll()) {
                log.info(location.toString());
            }
            log.info("");

            // fetch all occupation areas
            log.info("\u001B[31;1mDefined Occupation areas found with findAll():\u001B[0m");
            log.info("\u001B[31;1m-------------------------------");
            for (OccupationArea occupationArea: Repositories.occupationAreaRepository.findAll()) {
                log.info(occupationArea.toString());
            }
            log.info("");

            // fetch all areas
            log.info("\u001B[31;1mAreas found with findAll():\u001B[0m");
            log.info("\u001B[31;1m-------------------------------");
            for (GeographicalArea geographicalArea : Repositories.geoRepository.findAll()) {
                log.info(geographicalArea.toString());
            }
            log.info("");*/
        };
    }
}
