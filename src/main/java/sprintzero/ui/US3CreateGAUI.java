package sprintzero.ui;

import sprintzero.controllers.US3CreateGACTRL;
import sprintzero.model.GAList;

import java.util.Scanner;

public class US3CreateGAUI {

    //Atributos

    GAList mGAList;
    US3CreateGACTRL mCtrlUS3;

    //Contrutor

    public US3CreateGAUI(GAList inputList) {
        mGAList = inputList;
        mCtrlUS3 = new US3CreateGACTRL(inputList);
    }

    //Métodos

    public void run() {
        System.out.println("Insert the name of a geographical area:");
        Scanner read = new Scanner(System.in);

        String name = read.nextLine();
        System.out.println("Success");

        System.out.println("Insert the type of a geographical area:");
        String type = read.nextLine();
        System.out.println("Success");

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

        mCtrlUS3.newGA(name, type, width, height, latitude, longitude, altitude);
        System.out.println(mGAList.getGAList());

    }
}
