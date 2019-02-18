/*package smarthome.io.ui;

import smarthome.controller.US610GetHighestDailyTemperatureInRoomCTRL;
import smarthome.model.House;
import smarthome.model.ReadingList;
import smarthome.model.Sensor;

import java.util.Scanner;


public class US610GetHighestDailyTemperatureInRoomUI {

    private House mHouse;
    private int roomIndex;
    private int sensorIndex;
    private US610GetHighestDailyTemperatureInRoomCTRL currentController;

    Scanner userInput = new Scanner(System.in);

    public US610GetHighestDailyTemperatureInRoomUI(House house) {

        mHouse = house;
        currentController = new US610GetHighestDailyTemperatureInRoomCTRL(mHouse);
    }

    public void run() {
        System.out.println("Please choose a room from the list below:");
        System.out.println(currentController.roomListMenu());
        int roomIndex = userInput.nextInt() - 1;
        System.out.println("You have chosen room " + roomIndex+1 + " just like I knew you would.");

        if (currentController.roomHasTemperatureSensor(roomIndex)) {
            System.out.println("Cool, there's a temperature sensor there.");
            Sensor currentSensor = currentController.getTemperatureSensor(roomIndex);
            String sensorDesignation = currentSensor.getDesignation();

            //Show user the sensor's readings
            System.out.println("Sensor " + sensorDesignation + " has the following readings:");
            ReadingList readings = currentSensor.getReadingList();
            System.out.println(currentController.readingsListMenu(readings));
        } else {
            System.out.println("Sorry, I couldn't find a temperature sensor there.\nMaybe the Administrator forgot to add it? (S)he's only human after all.");
        }


    }
}

*/



