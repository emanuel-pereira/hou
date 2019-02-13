package smarthome.io.ui;

import smarthome.controller.AddRoomToHouseCTRL;
import smarthome.model.House;

import java.util.Scanner;

public class AddRoomToHouseUI {

    Scanner read = new Scanner (System.in);

    private AddRoomToHouseCTRL mUS105CTRL;

    private String name;
    private String tempFloor;
    private Integer floor;
    private String tempLength;
    private double length;
    private String tempWidth;
    private double width;
    private String tempHeight;
    private double height;


    public AddRoomToHouseUI(House house) {
        mUS105CTRL = new AddRoomToHouseCTRL (house);
    }

    /**
     * First: checks if the size of the room list in the house is 0, if so it doesn't exist and it needs to be create in the previous US.
     * Second: several cycles check if a validation method is not null. If true, the parameter is added to the Room.
     * If false (not validate) the cycle warns for the need for a new input until correct validation.
     * Third: All validations are for strings, so at this point they are "converted" to the correspondent type.
     * Fourth: The inputs are used to create a new Room and a success message is shown.
     */
    public void addRoomToHouse() {

        while (true) {
            System.out.println ("Insert the name of the room:");
            name = nameIsValid ();
            if (name != null)
                break;
            else
                this.tryAgainMessage ();
        }
        this.insertFloor ();
    }


    private void insertFloor() {
        while (true) {
            System.out.println ("Insert the floor where the room is:");
            tempFloor = floorIsValid ();
            if (tempFloor != null)
                break;
            else
                this.tryAgainMessage ();
        }
        this.insertLength ();
    }

    private void insertLength() {

        while (true) {
            System.out.println ("Insert the length of the room (in meters):");
            tempLength = lengthWidthOrHeightAreValid ();
            if (tempLength != null)
                break;
            else
                this.tryAgainMessage ();
        }
        this.insertWidth ();
    }


    private void insertWidth() {

        while (true) {
            System.out.println ("Insert the width of the room (in meters):");
            tempWidth = lengthWidthOrHeightAreValid ();
            if (tempWidth != null)
                break;
            else
                this.tryAgainMessage ();
        }
        this.insertHeight ();
    }

    private void insertHeight() {
        while (true) {
            System.out.println ("Insert the height of the room (in meters):");
            tempHeight = lengthWidthOrHeightAreValid ();
            if (tempHeight != null)
                break;
            else
                this.tryAgainMessage ();
        }
        this.addNewRoom ();
    }


    private void addNewRoom() {
        Integer floor;
        double length;
        double width;
        double height;
        floor = Integer.parseInt (tempFloor);
        length = Double.parseDouble (tempLength);
        width = Double.parseDouble (tempWidth);
        height = Double.parseDouble (tempHeight);
        if (mUS105CTRL.newRoom (name, floor, length, width, height)) {
            System.out.println ("Success. The " + name + " on the " + floor + " floor with " + height + "m of height and " + length * width + "m² was created.");
        } else {
            System.out.println ("Fail! Please try again.");
        }

    }

    private void tryAgainMessage() {
        System.out.print ("Try again. ");
    }


    public String nameIsValid() {
        String name = read.nextLine ();
        if (name == null || name.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ("Empty spaces are not accepted");
            return null;
        }
        if (!name.matches ("^(?![\\s]).*")) {
            System.out.println ("Please start with words.");
            return null;
        }
        return name;
    }


    private String floorIsValid() {
        String floor = read.nextLine ();
        if (floor == null || floor.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ("Empty spaces are not accepted.");
            return null;
        }
        if (!floor.matches ("^(?![\\s])\\d*")) {
            System.out.println ("Please use only numbers.");
            return null;
        }
        return floor;
    }


    private String lengthWidthOrHeightAreValid() {
        String number = read.nextLine ();
        if (number == null || number.trim ().isEmpty ()) {//verification of empty and null parameters
            System.out.println ("Empty spaces aren't accepted.");
            return null;
        }
        if (!number.matches ("[0-9]+([,.][0-9]{1,2})?")) {
            System.out.println ("Please use only numbers and dots if necessary.");
            return null;
        }
        return number;
    }

}
