package smarthome.io.ui;

import smarthome.controller.cli.NewGeographicalAreaCTRL;
import smarthome.model.GAList;
import smarthome.model.Location;
import smarthome.model.OccupationArea;

public class NewGeographicalAreaUI {

    private final NewGeographicalAreaCTRL ctrl;
    private String id;
    private String name;
    private int typeIndex;
    private OccupationArea occupationArea;
    private Location location;


    /**
     * Class method constructor where GAList inputList and
     * TypeGAList typeGAList Lists parameters are received
     * <p>
     * Here ui2 is also invoked in order to present the user with US2 UI,
     * which in other words means with the GA Types List itself in order
     * for it to select one from the list for the new GA being created
     * with this US
     *
     * @param inputList  GAList input with reference to the overall GA's list
     *
     */
    NewGeographicalAreaUI(GAList inputList) {
        this.ctrl = new NewGeographicalAreaCTRL(inputList);
    }


    void checkIfTypeGAListIsEmpty() {

        if (this.ctrl.typeGAListSize() == 0) {
            System.out.println("There are no types of Geographical Areas created. Please create one first.\n");
            UtilsUI.backToMenu();
            return;
        }
        this.insertIDAndName();
    }

    private void insertIDAndName() {
        System.out.println("Insert the ID of the new geographical area:");
        this.id = UtilsUI.requestText("Only alphanumeric characters area accepted.", "^[A-Za-z0-9 -]+$");
        System.out.println("Insert the description of the new geographical area:");
        this.name = UtilsUI.requestText("Only alphanumeric characters area accepted.", "^[A-Za-z0-9 -]+$");
        this.insertType();
    }

    private void insertType() {
        System.out.println("Choose a type of geographical area from the list below:");
        System.out.println(this.ctrl.showTypeGAListInStr());

        this.typeIndex = UtilsUI.requestIntegerInInterval(1, this.ctrl.typeGAListSize(),
                "Please choose a valid option.\n" + this.ctrl.showTypeGAListInStr());
        typeIndex--;
        this.insertOccupation();
    }

    private void insertOccupation() {
        System.out.println("Insert the length of the new geographical area:");
        double length = UtilsUI.requestDoubleInInterval(1, 100000, "Please insert only positive values.");
        System.out.println("Insert the width of the new geographical area:");
        double width = UtilsUI.requestDoubleInInterval(1, 100000, "Please insert only positive values.");
        this.occupationArea = new OccupationArea(length, width);
        this.insertLocation();
    }

    private void insertLocation() {
        double latitude;
        double longitude;
        double altitude;

        System.out.println("Insert the central latitude of the new geographical area:");
        latitude = UtilsUI.requestDoubleInInterval(-90, 90, "Latitude must be between [-90º,90º]");
        System.out.println("Insert the central longitude of the new geographical area:");
        longitude = UtilsUI.requestDoubleInInterval(-180, 180, "Latitude must be between [-180º,180º]");
        System.out.println("Insert the central altitude in meters of the new geographical area:");
        altitude = UtilsUI.requestDoubleInInterval(-12500, 8848, "Altitude must be between [-12.500m, 8848m]");
        this.location = new Location(latitude, longitude, altitude);
        this.createGA();
    }

    private void createGA() {
        this.ctrl.newGA(id, name, typeIndex, occupationArea, location);

        System.out.println("The geographical area " + this.ctrl.getGAName()
                + " of type " + this.ctrl.getGAType() + " was created.\n");
        UtilsUI.backToMenu();
    }
}
