
package smarthome.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smarthome.model.*;
import smarthome.repository.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

@Component
public class SmartHomeUI {
    private static SensorTypeList sensorTypeList;
    private static GAList gaList;
    private static TypeGAList typeGAList;
    private static House house;
    private static LocationRepository locRep;
    private static OccupationAreaRepository ocRep;
    private static SensorRepository sensorRep;
    private static SensorTypeRepository sensorTypeRep;

    public SmartHomeUI() {
        init();
        //FIXME BootStrap.run(house, typeGAList, sensorTypeList);
    }

    @Autowired
    public static void menuOptions(TypeGARepository typeRep, SensorTypeRepository sensorTypeRepository, LocationRepository rep, OccupationAreaRepository occupRep, SensorRepository sensorRepository) throws IllegalAccessException, InstantiationException, ClassNotFoundException, ParseException, org.json.simple.parser.ParseException, IOException {
        locRep = rep;
        ocRep=occupRep;
        sensorRep=sensorRepository;
        sensorTypeRep=sensorTypeRepository;
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
                    systemAdministration(house, typeGAList, gaList, typeRep, sensorTypeRep, locRep,ocRep,sensorRep,sensorTypeRep,sensorTypeList);
                    break;
                case 2:
                    houseAdministration(sensorTypeList, gaList, house, sensorTypeRepository, locRep);
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


    private static void init() {
        sensorTypeList = new SensorTypeList();
        gaList = new GAList();
        house = new House();
        typeGAList= new TypeGAList();
    }
}