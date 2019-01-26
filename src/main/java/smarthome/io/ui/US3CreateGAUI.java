package smarthome.io.ui;

import smarthome.controller.US3CreateGACTRL;
import smarthome.model.GAList;
import smarthome.model.GPSValidations;
import smarthome.model.TypeGAList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class US3CreateGAUI {

    private GAList mGAList;
    private TypeGAList mtypeGAList;
    private US3CreateGACTRL mCtrlUS3;
    private US2GetTypeGAListUI ui2; //in order to invoke methods form ui2 to print out the list of GA types for the user to select
    GPSValidations validations = new GPSValidations();

    /**
     * Class method constructor where GAList inputList and
     * TypeGAList typeGAList Lists parameters are received
     *
     * Here ui2 is also invoked in order to present the user with US2 UI,
     * which in other words means with the GA Types List itself in order
     * for it to select one from the list for the new GA being created
     * with this US
     * @param inputList  GAList input with reference to the overall GA's list
     * @param typeGAList GA Types List input with reference to the overall
     *                   GA's types list
     */
    public US3CreateGAUI(GAList inputList, TypeGAList typeGAList) {
        mGAList = inputList;
        mtypeGAList = typeGAList;
        mCtrlUS3 = new US3CreateGACTRL(inputList, typeGAList);
        ui2 = new US2GetTypeGAListUI(typeGAList);
    }



    /**
     * Main US3 method which presents the user with a list of questions
     * in order to get all the needed data for a GA creation
     * The method will receive all GA parameters and in the end
     * will call newGA2 method from US3 Controller in order to request the
     * GA creation in the respective class
     */
    public void run() {
        if (mtypeGAList.getTypeGAList().size() != 0) {
            Scanner read = new Scanner(System.in);
            System.out.println("Insert the ID of the new geographical area:");
            String id = read.nextLine();
            System.out.println("Success");



            System.out.println("Insert the description of the new geographical area:");
            String name = read.nextLine();
            System.out.println("Success");


            System.out.println("Choose a type of geographical area from the list below:");
            ui2.run();
            int typeIndex = read.nextInt();

            //previous input read for GA type, now replaced to receive and int input instead of a String
            //System.out.println();
        /*System.out.println("Insert the type of a geographical area:");
        String type = read.nextLine();
        System.out.println("Success");*/

            System.out.println("Insert the width of the new geographical area:");
            Double width = read.nextDouble(

            );
            System.out.println("Success");

            System.out.println("Insert the length of the new geographical area:");
            Double height = read.nextDouble();
            System.out.println("Success");

            double latitude;
            double longitude;
            double altitude;

            while (true) {
                try {
                    System.out.println("Insert the latitude of the new geographical area:");
                    latitude = read.nextDouble();
                    if (validations.latitudeIsValid(latitude))
                        break;
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Insert the longitude of the new geographical area:");
                    longitude = read.nextDouble();
                    if (validations.longitudeIsValid(longitude))
                        break;
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Insert the altitude of the new geographical area:");
                    altitude = read.nextDouble();
                    if (validations.altitudeIsValid(altitude))
                        break;
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
            }

            //mCtrlUS3.newGA(name, typeIndex, width, height, latitude, longitude, altitude);
            mCtrlUS3.newGA2(id, name, typeIndex, width, height, latitude, longitude, altitude);
            System.out.println("The geographical area " + mGAList.getGAList().get(mGAList.getGAList().size() - 1).getGeographicalAreaDesignation()
                    + " of type " + mGAList.getGAList().get(mGAList.getGAList().size() - 1).getGeographicalAreaType() + " was created.\n");
        } else
            System.out.println("No Geographical Area type were found, please insert one first in US1 \n \n");
    }
}
