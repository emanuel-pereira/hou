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
                    System.out.println(UtilsUI.insertValidOptionMsg());
            }
        }
    }

    private void selectHGListPS() {
        this.showAndSelectHG();
        if(isValidIndexOfHG()){
            this.listPSinHG();
        }
    }

    private void listPSinHG(){
        if(hgPSListIsEmpty()){
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
            System.out.println("List of Power Sources in " +mCtrl.getHouseGridName(mIndexOfHG)+ " is empty. Please add one first.");
            return true;
        }
        return false;
    }

    private void selectHGAddPS() {
        this.showAndSelectHG();
        if(isValidIndexOfHG()){
            this.insertPSName();
        }
    }

    private void insertPSName(){
        while (true) {
            System.out.println("Insert the Power Source name:");
            mName = mRead.nextLine();
            if (mCtrl.alphanumericName(mName)) {
                this.insertPSType();
                break;
            }
            else
                System.out.println(UtilsUI.insertValidParameter("name"));
        }
    }

    private void insertPSType(){
        while (true) {
            System.out.println("Insert the Power Source type:");
            mType = mRead.nextLine();
            if (mCtrl.alphanumericName(mType)) {
                this.insertPSMaxPower();
                break;
            } else
                System.out.println(UtilsUI.insertValidParameter("type"));
        }
    }

    private void insertPSMaxPower() {
        while (true) {
            System.out.println("Insert the Maximum Power (kW):");
            mMaxPower = mRead.nextDouble();
            mRead.nextLine();
            if (mMaxPower > 0) {
                this.insertPSStorageCapacity();
                break;
            } else System.out.println("Please insert only positive values.");
        }
    }

    private void insertPSStorageCapacity() {
        while (true) {
            System.out.println("Insert the Storage Capacity (kW):");
            mStorageCapacity = mRead.nextDouble();
            mRead.nextLine();
            if (mStorageCapacity > 0) {
                this.addPowerSource();
                break;
            } else System.out.println("Please insert only positive values.");
        }
    }

    private void addPowerSource () {
        mCtrl.addNewPSToGrid(mIndexOfHG,mName,mType,mMaxPower,mStorageCapacity);
        System.out.println("The following Power Source was successfully created:");
        System.out.println("[NAME]: " + mName);
        System.out.println("[TYPE]: " + mType);
        System.out.println("[MAX POWER]: " + mMaxPower);
        System.out.println("[STORAGE CAPACITY]: " + mStorageCapacity);
        UtilsUI.backToMenu();
    }

    private void showAndSelectHG (){
        System.out.println("Choose a house grid from the list below to add a Power Source to it:");
        System.out.println(mCtrl.getHGListInStringCtrl());
        mIndexOfHG = mRead.nextInt();
        mRead.nextLine();
    }

    private boolean isValidIndexOfHG() {
        while(mIndexOfHG > mCtrl.getHGListSizeCtrl() || mIndexOfHG <= 0) {
            System.out.println(UtilsUI.insertValidOptionMsg());
            System.out.println("--");
            this.showAndSelectHG();
        }
        return true;
    }
}
