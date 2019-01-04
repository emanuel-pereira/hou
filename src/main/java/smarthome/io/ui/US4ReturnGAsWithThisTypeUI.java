package smarthome.io.ui;

import smarthome.controller.US4ReturnGAsWithThisTypeCTRL;
import smarthome.model.GAList;
import smarthome.model.TypeGAList;

import java.util.Scanner;

public class US4ReturnGAsWithThisTypeUI {

    private GAList mGAList;
    private TypeGAList mTypeGAList;
    private US4ReturnGAsWithThisTypeCTRL mCtrlUS4;
    private US2GetTypeGAListUI ui2;

    public US4ReturnGAsWithThisTypeUI (GAList inputGAList, TypeGAList inputTypeList) {
        mGAList =  inputGAList;
        mTypeGAList = inputTypeList;
        mCtrlUS4 = new US4ReturnGAsWithThisTypeCTRL(mGAList,mTypeGAList);
        ui2 = new US2GetTypeGAListUI(mTypeGAList);
    }

   public void run () {
           Scanner read = new Scanner(System.in);
           System.out.println("Choose the Type of Geographical Area you wish to see from the list below.");
           //calls the list of previously inserted types for the user to choose from.
           ui2.run();
           //user chooses the number of type he wishes to see.
           int typeIndex = read.nextInt();
           String areaType = mTypeGAList.getTypeGAList().get(typeIndex-1).toString();

           //if there are GAs from the chosen type they are returned
           if(mCtrlUS4.getGAListFromType(typeIndex).size() > 0) {
               System.out.println("Here are the Geographical Areas from " + areaType + " type:\n" + mCtrlUS4.showListInString(typeIndex));
           }
           //if there are no GAs from the chosen type the following message is shown
           if(mCtrlUS4.getGAListFromType(typeIndex).size() == 0) {
               System.out.println("No results were found. Please try again.\n\n");
           }
   }

}
