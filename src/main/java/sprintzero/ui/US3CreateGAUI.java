package sprintzero.ui;

import sprintzero.controllers.US3CreateGACTRL;
import sprintzero.model.GAList;
import sprintzero.model.TypeGAList;

import java.util.Scanner;

public class US3CreateGAUI {

    private GAList mGAList;
    private US3CreateGACTRL mCtrlUS3;
    private US2GetTypeGAListUI ui2; //in order to invoke methods form ui2 to print out the list of GA types for the user to select

    /**
     * Class method constructor where GAList inputList and
     * TypeGAList typeGAList Lists parameters are received
     * @param inputList GAList input with reference to the overall GA's list
     * @param typeGAList GA Types List input with reference to the overall
     * GA's types list
     * Here ui2 is also invoked in order to present the user with US2 UI,
     * which in other words means with the GA Types List itself in order
     * for it to select one from the list for the new GA being created
     * with this US
     */
    public US3CreateGAUI(GAList inputList, TypeGAList typeGAList) {
        mGAList = inputList;
        mCtrlUS3 = new US3CreateGACTRL(inputList, typeGAList);
        ui2 = new US2GetTypeGAListUI (typeGAList);
    }

    /**
     * Main US3 method which presents the user with a list of questions
     * in order to get all the needed data for a GA creation
     * The method will receive all GA parameters and in the end
     * will call newGA2 method from US3 Controller in order to request the
     * GA creation in the respective class
     */
    public void run() {
        System.out.println("Insert the name of a geographical area:");
        Scanner read = new Scanner(System.in);

        String name = read.nextLine();
        System.out.println("Success");

        System.out.println("Choose a type of geographical area from the list below:");
        ui2.run ();
        int typeIndex = read.nextInt();

        //previous input read for GA type, now replaced to receive and int input instead of a String
        //System.out.println();
        /*System.out.println("Insert the type of a geographical area:");
        String type = read.nextLine();
        System.out.println("Success");*/

        System.out.println("Insert the width of a geographical area:");
        Double width = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the height of a geographical area:");
        Double height = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the latitude of a geographical area:");
        Double latitude = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the longitude of a geographical area:");
        Double longitude = read.nextDouble();
        System.out.println("Success");

        System.out.println("Insert the altitude of a geographical area:");
        Double altitude = read.nextDouble();
        System.out.println("Success");

        //mCtrlUS3.newGA(name, typeIndex, width, height, latitude, longitude, altitude);
        mCtrlUS3.newGA2(name, typeIndex, width, height, latitude, longitude, altitude);
        System.out.print("Name: " + mGAList.getGAList().get(0).getGeographicalAreaDesignation()
                + ", Type: " + mGAList.getGAList().get(0).getGeographicalAreaType());
    }
}
