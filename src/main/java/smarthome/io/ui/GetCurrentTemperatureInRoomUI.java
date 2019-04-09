package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInRoomCTRL;
import smarthome.model.SensorTypeList;

public class GetCurrentTemperatureInRoomUI {


    private GetCurrentTemperatureInRoomCTRL controller;
    private String temperature = "temperature";
    private int roomIndex;
    private String msgTitle = "Oops!";


    public GetCurrentTemperatureInRoomUI(SensorTypeList sensorTypeList) {
        this.controller = new GetCurrentTemperatureInRoomCTRL(sensorTypeList);
    }


    /**
     * Checks if the required sensor type Temperature was created by the Administrator
     */
    public void run() {
        if (this.controller.checkIfRequiredSensorTypeExists(this.temperature)) {
            this.checkIfRoomExists();
        } else
            UtilsUI.showError(msgTitle, "Please ask the Administrator to create a Temperature Sensor Type in the System");
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
        } else UtilsUI.showError(msgTitle, "No rooms found. Please ask the House Administrator to create a room.");
    }

    /**
     * Checks if the sensor type Temperature exist in the chosen room. If so, shows the current temperature of that room
     */
    private void checkIfTempSensorExistInRooms() {
        if (this.controller.checkIfSensorTypeExistsInRoom(roomIndex, this.temperature)) {
            System.out.println("Current temperature in the room: " + this.controller.getCurrentTemp(roomIndex));
        } else
            UtilsUI.showError(msgTitle, "Please ask the House Administrator to add a Temperature Sensor to this Room");
    }


}
