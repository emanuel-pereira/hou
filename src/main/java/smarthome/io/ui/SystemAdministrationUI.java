package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.House;
import smarthome.model.SensorTypeList;
import smarthome.model.TypeGAList;
import smarthome.repository.LocationRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.repository.TypeGARepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public final class SystemAdministrationUI {

    private SystemAdministrationUI() {
    }

    public static void systemAdministration(House house, TypeGAList typeGAList, GAList gaList,
                                            SensorTypeList sensorTypeList, TypeGARepository typeRep,
                                            SensorTypeRepository unitRep, LocationRepository locRep) throws ParseException,
            org.json.simple.parser.ParseException, IOException {

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
            options.add("[9] Import geographical areas and sensors from a JSONFile");
            options.add("[10] Import readings from a CSVFile");

            options.add("[0] Exit");

            UtilsUI.showList("System Administrator", options, false, 5);

            option = UtilsUI.requestIntegerInInterval(0, 10, "Please choose an action " +
                    "between 1 and 10, or 0 to exit the program");

            switch (option) {
                case 1:
                    NewTypeGAUI ui1 = new NewTypeGAUI(typeGAList, typeRep);
                    ui1.run();
                    break;
                case 2:
                    GetTypeGAListUI ui2 = new GetTypeGAListUI(typeGAList);
                    ui2.run();
                    break;
                case 3:
                    NewGeographicalAreaUI ui3 = new NewGeographicalAreaUI(gaList, typeGAList);
                    ui3.checkIfTypeGAListIsEmpty();
                    break;
                case 4:
                    GetGAsOfTypeUI ui4 = new GetGAsOfTypeUI(gaList, typeGAList);
                    ui4.run();
                    break;
                case 5:
                    NewSensorTypeUI ui5 = new NewSensorTypeUI(sensorTypeList, unitRep);
                    ui5.runUS5();
                    break;
                case 6:
                    NewSensorUI ui6 = new NewSensorUI(house, sensorTypeList, gaList, unitRep, locRep);
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
                    DataImportUI ui9 = new DataImportUI(gaList, locRep);
                    ui9.loadJSON();
                    break;
                case 10:
                    DataImportUI ui10 = new DataImportUI(gaList, locRep);
                    ui10.importDataFromCSVFile();
                    break;
                default:
                    //no action needed
            }
        }
    }

}
