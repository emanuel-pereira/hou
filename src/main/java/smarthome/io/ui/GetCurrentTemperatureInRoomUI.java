package smarthome.io.ui;

import smarthome.controller.GetCurrentTemperatureInRoomCTRL;
import smarthome.model.House;
import smarthome.model.SensorTypeList;

import java.util.Scanner;

public class GetCurrentTemperatureInRoomUI {

    Scanner read = new Scanner(System.in);

    private GetCurrentTemperatureInRoomCTRL controller;
    private String temperature = "temperature";
    private int roomIndex;


    public GetCurrentTemperatureInRoomUI(House house, SensorTypeList sensorTypeList) {
        this.controller = new GetCurrentTemperatureInRoomCTRL(house, sensorTypeList);
    }


    /**
     * Checks if the required sensor type Temperature was created by the Administrator
     */
    public void run() {
        if (this.controller.checkIfRequiredSensorTypeExists(this.temperature)) {
            this.checkIfRoomExists();
        } else System.out.println("Please ask the Administrator to create a Temperature Sensor Type in the System");
    }

    /**
     * Checks if there are any rooms created. If so, shows a list of rooms that the user can choose from
     */
    public void checkIfRoomExists() {

        if (!this.controller.getRoomList().isEmpty()) {
            while (true) {
                System.out.println("Choose the Room for which you want add this sensor, from the list below:");
                System.out.println(this.controller.showRoomListInString());
                roomIndex = read.nextInt();
                if (roomIndex > this.controller.getRoomList().size())
                    UtilsUI.printLnInsertValidOptionMsg();
                else break;
            }
            this.checkIfTempSensorExistInRooms();
        } else System.out.println("Please ask the House Administrator to create a Room");
    }

    /**
     * Checks if the sensor type Temperature exist in the chosen room. If so, shows the current temperature of that room
     */
    private void checkIfTempSensorExistInRooms() {
        if (this.controller.checkIfSensorTypeExistsInRoom(roomIndex, this.temperature)) {
            System.out.println("Current temperature in the room: " + this.controller.getCurrentTemp(roomIndex));
        } else System.out.println("Please ask the House Administrator to add a Temperature Sensor to this Room");
    }


}
