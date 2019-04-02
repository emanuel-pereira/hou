package smarthome;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;
import smarthome.io.ui.SmartHomeUI;
import smarthome.repository.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class Application {

    static final Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException, SAXException, ParserConfigurationException {
        SpringApplication.run(Application.class);
        SmartHomeUI.init();
        SmartHomeUI.menuOptions();
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
        {
            log.info("Application Start-Up");
        };

    }


}
