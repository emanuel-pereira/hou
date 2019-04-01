package smarthome;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;
import smarthome.repository.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

import static smarthome.io.ui.SmartHomeUI.init;
import static smarthome.io.ui.SmartHomeUI.menuOptions;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException, SAXException, ParserConfigurationException {
        SpringApplication.run(Application.class);
        init();
        menuOptions();
    }


    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, LocationRepository locRep, TypeGARepository typeRep,
                                  SensorTypeRepository unitRep, OccupationAreaRepository occupRep,
                                  SensorRepository sensorRep, ReadingRepository readingRep) {
        Repositories.setTypeGARepository(typeRep);
        Repositories.setLocationRepository(locRep);
        Repositories.setGeoRepository(geoRep);
        Repositories.setOccupationAreaRepository(occupRep);
        Repositories.setSensorTypeRepository(unitRep);
        Repositories.setSensorRepository(sensorRep);
        Repositories.setReadingRepository(readingRep);

        return args ->
                Logger.getLogger("Application initialization");

    }
}
