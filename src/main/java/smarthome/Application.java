package smarthome;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;
import smarthome.io.ui.SmartHomeUI;
import smarthome.model.*;
import smarthome.repository.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class Application  {

    private static final String DEFAULT = "Default";
    Location loc = new Location(1, 1, 1);
    Address a1 = new Address(DEFAULT, DEFAULT, "0000-000", DEFAULT, DEFAULT, loc);
    OccupationArea oc = new OccupationArea(1, 1);
    GeographicalArea g1 = new GeographicalArea(DEFAULT, DEFAULT, DEFAULT, oc, loc);
    House house = House.getHouseInstance(a1, g1);
    TypeGAList typeGAList = TypeGAList.getTypeGAListInstance();

    static final Logger log = Logger.getLogger(Application.class);

    /**
     * Main class that executes Spring application and invokes the init method to initialize the lists used
     * in the application and invokes the menuOptions() method that lists all UI options that users can select.
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException, SAXException, ParserConfigurationException, ParseException {
        SpringApplication.run(Application.class);
        SmartHomeUI.init();
        SmartHomeUI.menuOptions();
    }

    @Bean
    public CommandLineRunner demo(GeoRepository geoRep, RoomRepository rRep, SensorTypeRepository unitRep, TypeGARepository typeRep,
                                  ExternalSensorRepository extSensorRep, InternalSensorRepository intSensorRep, GridRepository gridsRep) {

        Repositories.setTypeGARepository(typeRep);
        Repositories.setGeoRepository(geoRep);
        Repositories.setRoomRepository(rRep);
        Repositories.setSensorTypeRepository(unitRep);
        Repositories.setExternalSensorRepository(extSensorRep);
        Repositories.setInternalSensorRepository(intSensorRep);
        Repositories.setGridsRepository(gridsRep);

        return args -> log.info("Application Start-Up");
    }


}
