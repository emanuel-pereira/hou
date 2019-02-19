package smarthome.io.ui;

import smarthome.model.GAList;
import smarthome.model.SensorTypeList;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public final class SystemAdministrationUI {

    private SystemAdministrationUI() {
    }

    public static void systemAdministration(SensorTypeList sensorTypeList, GAList gaList) {
        TypeGAList typeGAList = new TypeGAList();
        GAList inputGAList = gaList;
        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("As System Administrator I want to:");
            System.out.println("Click 1. Define a new type of geographical area to later classify the geographical areas");
            System.out.println("Click 2. Get the list of previously defined types of geographical areas");
            System.out.println("Click 3. Create a new geographical area");
            System.out.println("Click 4. See which geographical areas correspond to a specific type");
            System.out.println("Click 5. Specify a new meteorological characteristic that sensors can measure/register");
            System.out.println("Click 6. Create a new sensor and associate it to a Geographical Area");
            System.out.println("Click 7. Specify that a geographical area is added to another one");
            System.out.println("Click 8. Check if a a geographical area is direct/indirectly included to another one");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    NewTypeGAUI ui1 = new NewTypeGAUI(typeGAList);
                    ui1.createTypesGA();
                    break;
                case 2:
                    GetTypeGAListUI ui2 = new GetTypeGAListUI(typeGAList);
                    ui2.run();
                    break;
                case 3:
                    NewGeographicalAreaUI ui3 = new NewGeographicalAreaUI(inputGAList, typeGAList);
                    ui3.run();
                    break;
                case 4:
                    GetGAsOfTypeUI ui4 = new GetGAsOfTypeUI(inputGAList, typeGAList);
                    ui4.run();
                    break;
                case 5:
                    NewSensorTypeUI ui5 = new NewSensorTypeUI(sensorTypeList);
                    ui5.runUS5();
                    break;
                case 6:
                    NewSensorUI ui6 = new NewSensorUI(sensorTypeList, inputGAList);
                    ui6.checkIfGAListIsEmtpy();
                    break;
                case 7:
                    SetParentOfGAUI ui7 = new SetParentOfGAUI(inputGAList);
                    ui7.run();
                    break;
                case 8:
                    ListParentGAUI ui8 = new ListParentGAUI(inputGAList);
                    ui8.run();
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

}
