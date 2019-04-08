package smarthome;

import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;
import smarthome.io.ui.BootStrap;
import smarthome.io.ui.SmartHomeUI;
import smarthome.repository.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class Application {

    static final Logger log = Logger.getLogger(Application.class);

    /**
     * Main class that executes Spring application and invokes the init method to initialize the lists used
     * in the application and invokes the menuOptions() method that lists all UI options that users can select.
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException, SAXException, ParserConfigurationException {
        SpringApplication.run(Application.class);
        SmartHomeUI.init();
        SmartHomeUI.menuOptions();
    }

    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, SensorTypeRepository unitRep, TypeGARepository typeRep,
                                  SensorRepository sensorRep, ReadingRepository readingRep) {


        Repositories.setTypeGARepository(typeRep);
        Repositories.setGeoRepository(geoRep);
        Repositories.setSensorTypeRepository(unitRep);
        Repositories.setSensorRepository(sensorRep);
        Repositories.setReadingRepository(readingRep);

        return args -> log.info("Application Start-Up");
    }


}
