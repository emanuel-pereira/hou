package smarthome.io.ui;

import smarthome.controller.AddPowerSourceToGridCTRL;
import smarthome.model.*;

import java.util.Scanner;

public class AddPowerSourceToGridUI {

    private AddPowerSourceToGridCTRL mCtrl;
    private Scanner mRead = new Scanner(System.in);
    private int mIndexOfHG;
    private String mName;
    private String mType;
    private double mMaxPower;
    private double mStorageCapacity;

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
            System.out.println("Choose an option from the list below:");
            System.out.println("1 - Add a Power Source to a House Grid");
            System.out.println("2 - List Power Sources in a House Grid");
            System.out.println("0 - Exit");
            option = mRead.nextInt();
            mRead.nextLine();
            switch (option) {
                case 1:
                    this.selectHGAddPS();
                    break;
                case 2:
                    this.selectHGListPS();
                    break;
                default:
                    UtilsUI.printLnInsertValidOptionMsg();
            }
        }
    }

    private void selectHGListPS() {
        this.showAndSelectHG();
        if (isValidIndexOfHG()) {
            this.listPSinHG();
        }
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
        if (isValidIndexOfHG()) {
            this.insertPSName();
        }
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
        mMaxPower = UtilsUI.requestDoubleInInterval(0,Double.MAX_VALUE,"Please insert a numeric positive value");
        this.insertPSStorageCapacity();
    }

    private void insertPSStorageCapacity() {
        System.out.println("Insert the Storage Capacity (kW):");
        mStorageCapacity = UtilsUI.requestDoubleInInterval(0,Double.MAX_VALUE,"Please insert a numeric positive value");
        this.addPowerSource();
    }

    private void addPowerSource() {
        mCtrl.addNewPSToGrid(mIndexOfHG, mName, mType, mMaxPower, mStorageCapacity);
        System.out.println("The following Power Source was successfully created:");
        System.out.println("[NAME]: " + mName);
        System.out.println("[TYPE]: " + mType);
        System.out.println("[MAX POWER]: " + mMaxPower);
        System.out.println("[STORAGE CAPACITY]: " + mStorageCapacity);
        UtilsUI.backToMenu();
    }

    private void showAndSelectHG() {
        System.out.println("Choose a house grid from the list below to add a Power Source to it:");
        System.out.println(mCtrl.getHGListInStringCtrl());
        mIndexOfHG = mRead.nextInt();
        mRead.nextLine();
    }

    private boolean isValidIndexOfHG() {
        while (mIndexOfHG > mCtrl.getHGListSizeCtrl() || mIndexOfHG <= 0) {
            UtilsUI.printLnInsertValidOptionMsg();
            System.out.println("--");
            this.showAndSelectHG();
        }
        return true;
    }
}
