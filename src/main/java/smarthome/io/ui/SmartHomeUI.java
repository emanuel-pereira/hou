
package smarthome.io.ui;

import org.modelmapper.ModelMapper;
import org.xml.sax.SAXException;
import smarthome.model.*;
import smarthome.repository.Repositories;
import smarthome.services.RoomService;
import smarthome.services.SensorTypeService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static smarthome.io.ui.HouseAdministrationUI.houseAdministration;
import static smarthome.io.ui.PowerUserUI.powerUser;
import static smarthome.io.ui.RegularUsageUI.regularUsage;
import static smarthome.io.ui.RoomOwnerUI.roomOwner;
import static smarthome.io.ui.SystemAdministrationUI.systemAdministration;

public final class SmartHomeUI {
    private static SensorTypeList sensorTypeList;
    private static GAList gaList;

    private static final String DEFAULT = "Default";
    Location loc = new Location(1, 1, 1);
    Address a1 = new Address(DEFAULT, DEFAULT, "0000-000", DEFAULT, DEFAULT, loc);
    OccupationArea oc = new OccupationArea(1, 1);
    GeographicalArea g1 = new GeographicalArea(DEFAULT, DEFAULT, DEFAULT, oc, loc);
    House house = House.getHouseInstance(a1, g1);


    private SmartHomeUI(){}

    public static void menuOptions() throws SAXException, ParserConfigurationException, IllegalAccessException, InstantiationException, ClassNotFoundException, org.json.simple.parser.ParseException, IOException, ParseException, NoSuchFieldException {
        int option = -1;
        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();
            options.add("[1] System Administration");
            options.add("[2] House Administration");
            options.add("[3] Regular User");
            options.add("[4] Power User");
            options.add("[5] Room Owner");
            UtilsUI.showList("Main Menu", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 5, "Please choose an action between 1 and 5, or 0 to exit the program");
            switch (option) {
                case 1:
                    systemAdministration(gaList, sensorTypeList);
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
        /*RoomList roomList = new RoomList();
        RoomService service1 = new RoomService();
        List<Room> list1 = new ArrayList<>();
        service1.findAll().forEach(roomDTO -> list1.add(mapper.map(roomDTO, Room.class)));
        roomList.getRoomList().addAll(list1);*/


        /*ModelMapper mapper = new ModelMapper();
        SensorTypeList typeList = new SensorTypeList();

        SensorTypeService service2 = new SensorTypeService();
        List<SensorType> list2 = new ArrayList<>();
        service2.findAll().forEach(sensorTypeDTO -> list2.add(mapper.map(sensorTypeDTO, SensorType.class)));
        typeList.getSensorTypeList().addAll(list2);*/
        sensorTypeList = new SensorTypeList();

        gaList = new GAList();
    }
}