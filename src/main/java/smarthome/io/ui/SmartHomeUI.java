
package smarthome.io.ui;

import org.xml.sax.SAXException;
import smarthome.model.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

public final class SmartHomeUI {
    private static SensorTypeList sensorTypeList;
    private static GAList gaList;
    private static TypeGAList typeGAList;


    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431","4200-072","Porto","Portugal",loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);

    House h = House.getHouseInstance(a1, g1);


    private SmartHomeUI(){}

    public static void menuOptions() throws SAXException, ParserConfigurationException, IllegalAccessException, InstantiationException, ClassNotFoundException, org.json.simple.parser.ParseException, IOException {
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
                    systemAdministration(typeGAList, gaList, sensorTypeList);
                    break;
                case 2:
                    houseAdministration(sensorTypeList, gaList);
                    break;
                case 3:
                    regularUsage(sensorTypeList);
                    break;
                case 4:
                    powerUser();
                    break;
                case 5:
                    roomOwner();
                    break;
                default:
                    //no action needed
            }
        }
    }

    public static void init() {
        sensorTypeList = new SensorTypeList();
        gaList = new GAList();
        typeGAList = new TypeGAList();
    }
}