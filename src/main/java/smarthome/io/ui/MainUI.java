package smarthome.io.ui;

import smarthome.model.DataTypeList;
import smarthome.model.GAList;
import smarthome.model.SensorList;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        TypeGAList typeGAList = new TypeGAList();
        GAList GAList = new GAList();
        SensorList sensorList= new SensorList();
        DataTypeList dataTypeList = new DataTypeList(); //substituir lista por string
        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Click 1. US01: As System Administrator I want to define a new type of geographical area to later classify the geographical areas");
            System.out.println("Click 2. US02: As System Administrator I want to get the list of previously defined types of geographical areas");
            System.out.println("Click 3. US03: As System Administrator I want create a new geographical area");
            System.out.println("Click 4. US04: Incomplete");
            System.out.println("Click 5. US05: As System Administrator I want to specify a new meteorological characteristic that sensors can measure/register");
            System.out.println("Click 6. US06: Incomplete");
            System.out.println("Click 7. US07: Incomplete");
            System.out.println("Click 8. US08: As System Administrator I want to check direct/indirect Parent geographical areas");
            System.out.println("Click 0. Exit");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    US1CreateTypesGAUI ui1 = new US1CreateTypesGAUI(typeGAList);
                    ui1.run();
                    break;
                case 2:
                    US2GetTypeGAListUI ui2 = new US2GetTypeGAListUI (typeGAList);
                    ui2.run ();
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
                    US6CreateSensorUI ui6 = new US6CreateSensorUI(dataTypeList, GAList, sensorList);
                    ui6.run();
                    break;
                case 7:
                    /*US7UI ui7 = new ;
                    ui7.run();*/
                    break;
                case 8:
                    US8ListParentGAUI ui8 = new US8ListParentGAUI(GAList);
                    ui8.run();
                    break;
            }
        }
    }

}
