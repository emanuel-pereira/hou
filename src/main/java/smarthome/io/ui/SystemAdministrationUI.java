package smarthome.io.ui;

import smarthome.model.DataTypeList;
import smarthome.model.GAList;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class SystemAdministrationUI {

    public static void systemAdministration(DataTypeList dataTypeList,GAList gaList) {
        TypeGAList typeGAList = new TypeGAList();
        GAList GAList = gaList;
        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("As System Administrator I want to:");
            System.out.println("Click 1. US01: Define a new type of geographical area to later classify the geographical areas");
            System.out.println("Click 2. US02: Get the list of previously defined types of geographical areas");
            System.out.println("Click 3. US03: Create a new geographical area");
            System.out.println("Click 4. US04: See which geographical areas correspond to a specific type");
            System.out.println("Click 5. US05: Specify a new meteorological characteristic that sensors can measure/register");
            System.out.println("Click 6. US06: Create a new sensor and associate it to a Geographical Area");
            System.out.println("Click 7. US07: Specify that a geographical area is added to another one");
            System.out.println("Click 8. US08: Check if a a geographical area is direct/indirectly included to another one");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US1CreateTypesGAUI ui1 = new US1CreateTypesGAUI(typeGAList);
                    ui1.createTypesGA();
                    break;
                case 2:
                    US2GetTypeGAListUI ui2 = new US2GetTypeGAListUI(typeGAList);
                    ui2.run();
                    break;
                case 3:
                    US3CreateGAUI ui3 = new US3CreateGAUI(GAList, typeGAList);
                    ui3.run();
                    break;
                case 4:
                    US4ReturnGAsWithThisTypeUI ui4 = new US4ReturnGAsWithThisTypeUI(GAList, typeGAList);
                    ui4.run();
                    break;
                case 5:
                    US5DefineSensorDataTypeUI ui5 = new US5DefineSensorDataTypeUI(dataTypeList);
                    ui5.runUS5();
                    break;
                case 6:
                    US6CreateSensorUI ui6 = new US6CreateSensorUI(dataTypeList, GAList);
                    ui6.run();
                    break;
                case 7:
                    US7SetParentOfGAUI ui7 = new US7SetParentOfGAUI(GAList);
                    ui7.run();
                    break;
                case 8:
                    US8ListParentGAUI ui8 = new US8ListParentGAUI(GAList);
                    ui8.run();
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

}
