package smarthome;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import smarthome.repository.*;

import java.io.IOException;
import java.text.ParseException;

import static smarthome.io.ui.SmartHomeUI.menuOptions;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IllegalAccessException, ParseException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException {
        SpringApplication.run(Application.class);

        menuOptions();
    }


    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, LocationRepository locRep, TypeGARepository typeRep,
                                  SensorTypeRepository unitRep, OccupationAreaRepository occupRep,
                                  SensorRepository sensorRep, ReadingRepository readingRep) {
        Repositories.typeGARepository = typeRep;
        Repositories.locationRepository = locRep;
        Repositories.geoRepository = geoRep;
        Repositories.occupationAreaRepository = occupRep;
        Repositories.sensorTypeRepository = unitRep;
        Repositories.sensorRepository = sensorRep;
        Repositories.readingRepository = readingRep;

        return args -> {
        };
    }
}
