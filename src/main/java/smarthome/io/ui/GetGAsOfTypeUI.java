package smarthome.io.ui;

import smarthome.controller.GetGAsOfTypeCTRL;
import smarthome.model.GAList;

import java.util.Scanner;

import static smarthome.model.TypeGAList.getTypeGAList;

public class GetGAsOfTypeUI {

    private GAList gaList;
    private GetGAsOfTypeCTRL ctrlUS4;
    private GetTypeGAListUI ui2;

    public GetGAsOfTypeUI(GAList inputGAList) {
        gaList = inputGAList;
        ctrlUS4 = new GetGAsOfTypeCTRL(gaList);
        ui2 = new GetTypeGAListUI();
    }

    public void run() {
        Scanner read = new Scanner(System.in);
        System.out.println("Choose the Type of Geographical Area you wish to see from the list below.");
        //calls the list of previously inserted types for the user to choose from.
        ui2.run();
        //user chooses the number of type he wishes to see.
        int typeIndex = read.nextInt();
        String areaType = getTypeGAList().get(typeIndex - 1).toString();

        //if there are GAs from the chosen type they are returned
        if (!ctrlUS4.getGAListFromType(typeIndex).isEmpty()) {
            System.out.println("Here are the Geographical Areas from " + areaType + " type:\n" + ctrlUS4.showListInString(typeIndex));
            UtilsUI.backToMenu();
        }
        //if there are no GAs from the chosen type the following message is shown
        else{
            System.out.println("No results were found. Please try again.\n\n");
            UtilsUI.backToMenu();
        }
    }

}
