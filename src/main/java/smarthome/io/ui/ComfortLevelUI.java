package smarthome.io.ui;

import smarthome.controller.ComfortLevelCTRL;
import smarthome.dto.RoomDTO;

import java.util.*;

public class ComfortLevelUI {

    private ComfortLevelCTRL ctrl;
    private List<String> roomList;

    public ComfortLevelUI() {
        this.ctrl = new ComfortLevelCTRL();
    }

    /*------ Menu ------*/

    //Main
    public void run() {
        //Check if all needed data is available and end or continue user story sooner as needed showing user the appropriate error message.

        if (!ctrl.validateGeoAreaHasTemperatureSensorWithReadings()) {
            noTemperatureInGeoArea();
            return;
        }

        if (!ctrl.validateHouseHasRooms()) {
            noRoomsFound();
            return;
        }

        if (!ctrl.validateRoomsHaveTemperatureSensors()) {
            noTemperatureSensorsFoundInRooms();
            return;
        }

        if (!ctrl.validateTemperatureSensorsHaveReadings()) {
            noReadingsFound();
            return;
        }


        //Show rooms which have temperature sensors with readings and ask the user to select one
        List<RoomDTO> roomListDTO = ctrl.getRoomListDTO();

        RoomDTO selectedRoomIndex = selectRoomFromList(roomListDTO);

        System.out.println(selectedRoomIndex.getID());

        boolean maxOrMin = selectMaxOrMin();
        int category = selectComfortLevelCategory();

        //Ask user to select parameters needed for User Story and a time interval and
        //Start date
        GregorianCalendar startDate = selectDate("Please enter a start date for verification");
        //End date
        GregorianCalendar endDate = selectDate("Please enter a end date for verification");

        //Calculate 'stuff' and show result
        String results = ctrl.calculateThermalComfort(selectedRoomIndex, maxOrMin, category, startDate, endDate);
        displayResults(results);
    }


    // ------------------------------------------------------------------------------------------------------------------------------


    //Error messages

    private void noTemperatureInGeoArea() {

        UtilsUI.showError("Error", "No temperature sensor(s) with readings found in the house's geographical area. Unable to proceed.");
        UtilsUI.backToMenu();

    }

    private void noRoomsFound() {
        UtilsUI.showError("No rooms found", "Unable to locate rooms in the house. Add or import rooms and try again.");
        UtilsUI.backToMenu();
    }

    private void noTemperatureSensorsFoundInRooms() {
        UtilsUI.showError("No temperature sensors found", "No rooms in the house have temperature sensors. Add or import temperature sensors to one or more rooms and try again.");
        UtilsUI.backToMenu();
    }

    private void noReadingsFound() {
        UtilsUI.showError("No readings found", "One or more rooms has a temperature sensor, but no readings are available. Add or import readings and try again.");
        UtilsUI.backToMenu();
    }

    //User input requests

    //Unwraps the DTO and creates a names list for display
    private List<String> getRoomNamesFromDTO(List<RoomDTO> roomDTOList) {
        List<String> result = new ArrayList<>();
        for (RoomDTO r : roomDTOList) {
            result.add(r.getID() + "|" + r.getName());
        }
        return result;
    }


    private RoomDTO selectRoomFromList(List<RoomDTO> roomDTOList) {
        List<String> roomList = getRoomNamesFromDTO(roomDTOList);

        UtilsUI.showList("Please select a room from the list below", roomList, true, 10);
        int i = UtilsUI.requestIntegerInInterval(1, roomList.size(), "Please enter a valid option");

        return roomDTOList.get(i - 1);
    }

    private GregorianCalendar selectDate(String message) {
        GregorianCalendar date = new GregorianCalendar();
        System.out.println(message);
        date = UtilsUI.requestDate("Invalid date entered. Dates must be in yyyy-mm-dd format.");
        return date;
    }

    /**
     * @return true if user selects max, false if min
     */
    private boolean selectMaxOrMin() {
        List<String> options = new ArrayList<>();
        options.add("[A] Display readings ABOVE maximum temperature in category");
        options.add("[B] Display readings BELOW maximum temperature in category");
        UtilsUI.showList("Please select an option", options, false, 10);
        String input = UtilsUI.requestText("Please enter 'A' or 'B'", "[A-B|a-b]");
        return input.equalsIgnoreCase("a");
    }

    private int selectComfortLevelCategory() {
        UtilsUI.showInfo("Comfort level", "Please enter the desired comfort level category (1, 2 or 3) according to EN15251:2006.");
        return UtilsUI.requestIntegerInInterval(1, 3, "Please enter a value between 1 and 3.");
    }

    //Outputs
    private void displayResults(String results) {
        UtilsUI.showInfo("Results", "The following tables show the readings outside the allowable range of the selected comfort level category");

    }
}
