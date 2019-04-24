package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInRoomCTRL;
import smarthome.model.SensorTypeList;

public class GetCurrentTemperatureInRoomUI {


    private final GetCurrentTemperatureInRoomCTRL controller;
    private static final String TEMPERATURE = "temperature";
    private int roomIndex;
    private static final String MSG_TITLE = "Oops!";


    public GetCurrentTemperatureInRoomUI(SensorTypeList sensorTypeList) {
        this.controller = new GetCurrentTemperatureInRoomCTRL(sensorTypeList);
    }


    /**
     * Checks if the required sensor type Temperature was created by the Administrator
     */
    public void run() {
        if (this.controller.checkIfRequiredSensorTypeExists(TEMPERATURE)) {
            this.checkIfRoomExists();
        } else
            UtilsUI.showError(MSG_TITLE, "Please ask the Administrator to create a Temperature Sensor Type in the System");
    }

    /**
     * Checks if there are any rooms created. If so, shows a list of rooms that the user can choose from
     */
    public void checkIfRoomExists() {

        if (!this.controller.getRoomList().isEmpty()) {

            System.out.println("Choose the Room for which you want add this sensor from the list below:");
            System.out.println(this.controller.showRoomListInString());
            roomIndex = UtilsUI.requestIntegerInInterval(1, this.controller.getRoomList().size(), "Invalid option selected, please try again.");

            this.checkIfTempSensorExistInRooms();
        } else UtilsUI.showError(MSG_TITLE, "No rooms found. Please ask the House Administrator to create a room.");
    }

    /**
     * Checks if the sensor type Temperature exist in the chosen room. If so, shows the current TEMPERATURE of that room
     */
    private void checkIfTempSensorExistInRooms() {
        if (this.controller.checkIfSensorTypeExistsInRoom(roomIndex, TEMPERATURE)) {
            System.out.println("Current TEMPERATURE in the room: " + this.controller.getCurrentTemp(roomIndex));
        } else
            UtilsUI.showError(MSG_TITLE, "Please ask the House Administrator to add a Temperature Sensor to this Room");
    }


}
