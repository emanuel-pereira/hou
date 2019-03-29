package smarthome.io.ui;

import smarthome.controller.GetGAsOfTypeCTRL;
import smarthome.model.GAList;
import smarthome.model.TypeGAList;
import smarthome.model.validations.Utils;

import java.util.Scanner;

public class GetGAsOfTypeUI {

    private GAList mGAList;
    private TypeGAList mTypeGAList;
    private GetGAsOfTypeCTRL mCtrlUS4;
    private GetTypeGAListUI ui2;

    public GetGAsOfTypeUI(GAList inputGAList, TypeGAList inputTypeList) {
        mGAList = inputGAList;
        mTypeGAList = inputTypeList;
        mCtrlUS4 = new GetGAsOfTypeCTRL(mGAList, mTypeGAList);
        ui2 = new GetTypeGAListUI(mTypeGAList);
    }

    public void run() {
        Scanner read = new Scanner(System.in);
        System.out.println("Choose the Type of Geographical Area you wish to see from the list below.");
        //calls the list of previously inserted types for the user to choose from.
        ui2.run();
        //user chooses the number of type he wishes to see.
        int typeIndex = read.nextInt();
        String areaType = mTypeGAList.getTypeGAList().get(typeIndex - 1).toString();

        //if there are GAs from the chosen type they are returned
        if (!mCtrlUS4.getGAListFromType(typeIndex).isEmpty()) {
            System.out.println("Here are the Geographical Areas from " + areaType + " type:\n" + mCtrlUS4.showListInString(typeIndex));
            UtilsUI.backToMenu();
        }
        //if there are no GAs from the chosen type the following message is shown
        else{
            System.out.println("No results were found. Please try again.\n\n");
            UtilsUI.backToMenu();
        }
    }

}
