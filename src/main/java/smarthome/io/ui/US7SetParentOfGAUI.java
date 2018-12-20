package smarthome.io.ui;

import smarthome.controller.US7SetParentOfGACTRL;
import smarthome.model.GAList;

import java.util.Scanner;
public class US7SetParentOfGAUI {

    private GAList mGAList;
    private US7SetParentOfGACTRL mCtrlUS7;

    public US7SetParentOfGAUI(GAList inputList) {
        mGAList = inputList;
        mCtrlUS7 = new US7SetParentOfGACTRL(inputList);
    }

    public void run() {
        Scanner read = new Scanner(System.in);

        System.out.println("Choose a Geographical Area from the list below:");
        System.out.println(mCtrlUS7.showListInString());
        int gaIndex1 = read.nextInt();
        System.out.println("Success");

        System.out.println("Choose a Geographical Area from the list below to set as parent of" + mGAList.getGAList().get(gaIndex1 - 1).getGeographicalAreaDesignation());
        System.out.println(mCtrlUS7.showListInString());
        int gaIndex2 = read.nextInt();
        System.out.println("Success");

        mCtrlUS7.setParentofGA(gaIndex1, gaIndex2);
    }
}
