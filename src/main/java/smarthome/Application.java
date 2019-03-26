package smarthome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import smarthome.model.OccupationAreaRepository;
import smarthome.model.SensorType;
import smarthome.model.TypeGA;
import smarthome.repository.GeoRepository;
import smarthome.repository.LocationRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.repository.TypeGARepository;

import java.io.IOException;
import java.text.ParseException;

import static smarthome.io.ui.SmartHomeUI.menuOptions;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static GeoRepository geoRep;
    private static LocationRepository locRep;
    private static TypeGARepository typeRep;
    private static SensorTypeRepository unitRep;
    private static OccupationAreaRepository occupRep;

    public static void main(String[] args) throws IllegalAccessException, ParseException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException {
        SpringApplication.run(Application.class);

        menuOptions(typeRep, unitRep, locRep, occupRep);
    }


    @Bean
    public CommandLineRunner demo(TypeGARepository typeGARepository, SensorTypeRepository unitRepository, GeoRepository geoRepository, LocationRepository locationRep,OccupationAreaRepository ocRep) {
        typeGARepository.save(new TypeGA("novo tipo"));
        typeRep = typeGARepository;

        unitRepository.save(new SensorType("it's raining"));
        unitRep = unitRepository;
        occupRep=ocRep;

        geoRep = geoRepository;

        locRep = locationRep;
        return args -> {
        };
    }
}
