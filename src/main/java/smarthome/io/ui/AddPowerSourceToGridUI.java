package smarthome.io.ui;

import smarthome.controller.AddPowerSourceToGridCTRL;
import smarthome.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AddPowerSourceToGridUI {

    private AddPowerSourceToGridCTRL mCtrl;
    private Scanner mRead = new Scanner(System.in);
    private int mIndexOfHG;
    private String mName;
    private String mType;
    private double mMaxPower;
    private double mStorageCapacity;
    private String mYesOrNo;

    public AddPowerSourceToGridUI(House house) {
        mCtrl = new AddPowerSourceToGridCTRL(house);
    }

    public void checkIfHGListIsEmpty() {
        if (mCtrl.getHGListSizeCtrl() == 0) {
            System.out.println("List of HouseGrids is empty. Please insert a HouseGrid first.");
            return;
        }
        this.selectSubMenu();
    }

    private void selectSubMenu() {
        int option = -1;
        while (option != 0) {
            ArrayList<String> options = new ArrayList<>();

            options.add("[1] Add a Power Source to a Grid ");
            options.add("[2] List Power Sources in a Grid");
            options.add("[0] Exit");

            UtilsUI.showList("Choose an option:", options, false,5);

            option = UtilsUI.requestIntegerInInterval(0,2,"Please choose a valida option(1 or 2), or 0 to return to the previous Menu");

            switch (option) {
                case 1:
                    this.selectHGAddPS();
                    break;
                case 2:
                    this.selectHGListPS();
                    break;
                default:
                //UtilsUI.printLnInsertValidOptionMsg();
            }
        }
    }

    private void selectHGListPS() {
        this.showAndSelectHG();
        this.listPSinHG();

    }

    private void listPSinHG() {
        if (hgPSListIsEmpty()) {
            UtilsUI.backToMenu();
        }
        else{
        System.out.println("List of Power Sources attached to " + mCtrl.getHouseGridName(mIndexOfHG) + ":");
        System.out.println(mCtrl.showPowerSourceListInString(mIndexOfHG));
        UtilsUI.backToMenu();
        }
    }

    private boolean hgPSListIsEmpty() {
        if (mCtrl.getPSListSizeCtrl(mIndexOfHG) == 0) {
            System.out.println("List of Power Sources in " + mCtrl.getHouseGridName(mIndexOfHG) + " is empty. Please add one first.");
            return true;
        }
        return false;
    }

    private void selectHGAddPS() {
        this.showAndSelectHG();
        this.insertPSName();
    }

    private void insertPSName(){
        System.out.println("Insert the Power Source name:");
        mName = UtilsUI.requestText("Please insert a valid name");
        this.insertPSType();
    }

    private void insertPSType(){
        System.out.println("Insert the Power Source type:");
        mType = UtilsUI.requestText("Please insert a valid type");
        this.insertPSMaxPower();
    }

    private void insertPSMaxPower() {
        System.out.println("Insert the Maximum Power (kW):");
        mMaxPower = UtilsUI.requestDoubleInInterval(-1,Double.MAX_VALUE,"Please insert a numeric positive value");
        this.insertPSStorageCapacity();
    }

    private void insertPSStorageCapacity() {
        System.out.println("Insert the Storage Capacity (kW):");
        mStorageCapacity = UtilsUI.requestDoubleInInterval(0,Double.MAX_VALUE-100,"Please insert a numeric positive value");
        this.addPowerSource();
    }

    private void addPowerSource() {
        if(UtilsUI.confirmOption("Continue?(y/n)\n", "Please type y for Yes or n for No.", "[2yYnN]")){
        mCtrl.addNewPSToGrid(mIndexOfHG, mName, mType, mMaxPower, mStorageCapacity);
        System.out.println("The following Power Source was successfully created:" +
                "\n[NAME]: " + mName +
                "\n[TYPE]: " + mType +
                "\n[MAX POWER]: " + UtilsUI.formatDecimal(mMaxPower,2) +
                "\n[STORAGE CAPACITY]: " + UtilsUI.formatDecimal(mStorageCapacity,2));
        UtilsUI.backToMenu();}
        else{
            System.out.println("Operation was canceled!");
            UtilsUI.backToMenu();}

    }

    private void showAndSelectHG() {
        System.out.println("Choose a house grid from the list below to add a Power Source to it:");
        System.out.println(mCtrl.getHGListInStringCtrl());
        mIndexOfHG = UtilsUI.requestIntegerInInterval(1,mCtrl.getHGListSizeCtrl(),"Please insert a valid house grid index");
    }
}
