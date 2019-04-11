package smarthome.io.ui;

import org.xml.sax.SAXException;
import smarthome.model.GAList;
import smarthome.model.SensorTypeList;
import smarthome.model.TypeGAList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public final class SystemAdministrationUI {

    private SystemAdministrationUI() {
    }

    public static void systemAdministration(GAList gaList, SensorTypeList sensorTypeList)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, org.json.simple.parser.ParseException, ParserConfigurationException, SAXException {

        int option = -1;
        while (option != 0) {

            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Define a new type of geographical area to later classify the geographical areas");
            options.add("[2] Get the list of previously defined types of geographical areas");
            options.add("[3] Create a new geographical area");
            options.add("[4] See which geographical areas correspond to a specific type");
            options.add("[5] Specify a new meteorological characteristic that sensors can measure/register");
            options.add("[6] Create a new sensor and associate it to a Geographical Area");
            options.add("[7] Specify that a geographical area is added to another one");
            options.add("[8] Check if a a geographical area is direct/indirectly included to another one");
            options.add("[9] Deactivate a sensor in a geographical area");
            options.add("[10] Remove a sensor from a geographical area, so that it will no longer be used.");
            options.add("[11] Import geographical areas and sensors from a File (XML,JSON)");
            options.add("[12] Import readings from a File(CSV,XML,JSON)");

            options.add("[0] Exit");

            UtilsUI.showList("System Administrator", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 12, "Please choose an action " +
                    "between 1 and 12, or 0 to return");

            switch (option) {
                case 1:
                    NewTypeGAUI ui1 = new NewTypeGAUI();
                    ui1.run();
                    break;
                case 2:
                    GetTypeGAListUI ui2 = new GetTypeGAListUI();
                    ui2.run();
                    break;
                case 3:
                    NewGeographicalAreaUI ui3 = new NewGeographicalAreaUI(gaList);
                    ui3.checkIfTypeGAListIsEmpty();
                    break;
                case 4:
                    GetGAsOfTypeUI ui4 = new GetGAsOfTypeUI(gaList);
                    ui4.run();
                    break;
                case 5:
                    NewSensorTypeUI ui5 = new NewSensorTypeUI(sensorTypeList);
                    ui5.run();
                    break;
                case 6:
                    NewSensorUI ui6 = new NewSensorUI(sensorTypeList, gaList);
                    ui6.checkIfGAListIsEmpty();
                    break;
                case 7:
                    SetParentOfGAUI ui7 = new SetParentOfGAUI(gaList);
                    ui7.run();
                    break;
                case 8:
                    ListParentGAUI ui8 = new ListParentGAUI(gaList);
                    ui8.run();
                    break;
                case 9:
                    DeactivateSensorUI ui9 = new DeactivateSensorUI(gaList);
                    ui9.run ();
                    break;
                case 10:
                    RemoveGASensorUI ui10 = new RemoveGASensorUI(gaList);
                    ui10.selectGA();
                    break;
                case 11:
                    DataImportUI ui11 = new DataImportUI(gaList);
                    ui11.loadGeoAreaFile();
                    break;
                case 12:
                    DataImportUI ui12 = new DataImportUI(gaList);
                    ui12.importReadings(gaList);
                    break;
                default:
                    //no action needed
            }
        }
    }

}
