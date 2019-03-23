package smarthome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import smarthome.io.ui.UtilsUI;
import smarthome.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static SensorTypeList sensorTypeList;
    private static GAList gaList;
    private static TypeGAList typeGAList;
    private static House house;
    private static GeoRepository geoRep;
    private static LocationRepository locRep;
    private static TypeGARepository typeRep;

    public static void main(String[] args) throws IllegalAccessException, ParseException, InstantiationException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException {
        SpringApplication.run(Application.class);
        init();
        //BootStrap.run(house, typeGAList, sensorTypeList);
        menuOptions(geoRep, locRep, typeRep);
    }

    private static void init() {
        sensorTypeList = new SensorTypeList();
        gaList = new GAList();
        house = new House();
        typeGAList = new TypeGAList();
    }

    private static void menuOptions(GeoRepository geoRep, LocationRepository locRep, TypeGARepository typeRep) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException, org.json.simple.parser.ParseException, IOException {
        int option = -1;
        while (option != 0) {


            ArrayList<String> options = new ArrayList<>();
            options.add("[1] System Administration");
            options.add("[2] House Administration");
            options.add("[3] Regular User");
            options.add("[4] Power User");
            options.add("[5] Room Owner");
            options.add("[0] Exit");

            UtilsUI.showList("Main Menu", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 5, or 0 to exit the program");
            switch (option) {
                case 1:
                    systemAdministration(house, sensorTypeList, typeGAList, gaList, geoRep, locRep, typeRep);
                    break;
                case 2:
                    houseAdministration(sensorTypeList, gaList, house);
                    break;
                case 3:
                    regularUsage(house, sensorTypeList);
                    break;
                case 4:
                    powerUser(house);
                    break;
                case 5:
                    roomOwner(house);
                    break;
                default:
                    //no action needed
            }
        }
    }

    @Bean
    public CommandLineRunner demo(TypeGARepository typeGARepository) {
        typeGARepository.save(new TypeGA("novo tipo"));
        typeRep = typeGARepository;
        return args -> {
        };
    }
}
