package smarthome.io.ui;

import smarthome.controller.GetEnergyConsumptionInPeriodCTRL;
import smarthome.model.House;
import java.util.GregorianCalendar;

public class GetEnergyConsumptionInPeriodUI {

    private GetEnergyConsumptionInPeriodCTRL mCtrl;
    private int indexOfDevice;
    private int indexOfHG;
    private int indexOfRoom;
    private int option;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String timePeriodStr = "[Time Period]: ";
    private String ecString = "[Energy Consumption]: ";


    public GetEnergyConsumptionInPeriodUI(House house) {
        mCtrl = new GetEnergyConsumptionInPeriodCTRL(house);
    }


    public void selectOption() {
        this.option = -1;
        while (this.option != 0) {
            System.out.println("Get the total energy consumption, in a time interval, of a:");
            System.out.println("1 - Device");
            System.out.println("2 - Room");
            System.out.println("3 - Grid");
            System.out.println("0 - Exit");
            this.option = UtilsUI.requestIntegerInInterval(0,3,"Please choose a valid option between 1 and 3, or 0 to exit.");
            switch (this.option) {
                case 1:
                    this.selectDevice();
                    break;
                case 2:
                    this.selectRoom();
                    break;
                case 3:
                    this.selectHouseGrid();
                    break;
                default:
                    System.out.println(UtilsUI.insertValidOptionMsg());
            }
        }
    }

    private void selectDevice() {
        System.out.println("Choose a device from the list below:");
        System.out.println(mCtrl.showMeteredDevicesInStr());
        this.indexOfDevice = UtilsUI.requestIntegerInInterval(1, mCtrl.getMeteredDevicesInHouseSize(),
                "Not a valid option. Please select a device from the list below:\n" + mCtrl.showMeteredDevicesInStr());
        this.indexOfDevice--;
        this.getStartDate();
    }

    private void selectRoom() {
        System.out.println("Choose a room from the list below:");
        System.out.println(mCtrl.showRoomListInStr());
        this.indexOfRoom = UtilsUI.requestIntegerInInterval(1, mCtrl.getRoomListSize(),
                "Not a valid option. Please select a room from the list below:\n" + mCtrl.showRoomListInStr());
        this.indexOfRoom--;
        this.getStartDate();
    }

    private void selectHouseGrid() {
        System.out.println("Choose a grid from the list below:");
        System.out.println(mCtrl.showHouseGridListInString());
        this.indexOfHG = UtilsUI.requestIntegerInInterval(1, mCtrl.getRoomListSize(),
                "Not a valid option. Please select a grid from the list below:\n" + mCtrl.showRoomListInStr());
        this.indexOfHG--;
        this.getStartDate();
    }


    private void getStartDate() {
        System.out.println("Insert the time interval in which you want to get the energy consumption.");
        System.out.println("Insert the starting date in yyyy-mm-dd HH:mm format:");
        this.startDate = UtilsUI.requestDateTime("Please insert a valid start date and time in yyyy-MM-dd HH:mm format.");
        this.getEndDate();
    }

    private void getEndDate() {
        System.out.println("Insert the ending date in yyyy-mm-dd HH:mm format:");
        this.endDate = UtilsUI.requestDateTime("Please insert a valid end date and time in yyyy-MM-dd HH:mm format.");
        switch (this.option) {
            case 1:
                this.getDeviceEnergyConsumption();
                break;
            case 2:
                this.getRoomEnergyConsumption();
                break;
            case 3:
                this.getHouseGridEnergyConsumption();
                break;
            default:
                System.out.println(UtilsUI.insertValidOptionMsg());
        }
    }

    private void getDeviceEnergyConsumption() {
        String deviceName = mCtrl.getDeviceName(this.indexOfDevice);
        System.out.println("Total Energy Consumption of the device in the time period:");
        System.out.println("[Device]: " + deviceName);
        System.out.println(this.timePeriodStr + "[" + UtilsUI.dateToString(this.startDate) + " - " + UtilsUI.dateToString(this.endDate) + "]");
        System.out.println(this.ecString + mCtrl.getEnergyConsumptionInPeriod(this.indexOfDevice, this.startDate, this.endDate) + "\n");
    }

    private void getHouseGridEnergyConsumption() {
        String houseGrid = mCtrl.getHGName(this.indexOfHG);
        System.out.println("Total Energy Consumption of the grid in the time period:");
        System.out.println("[Grid]: " + houseGrid);
        System.out.println(this.timePeriodStr + "[" + UtilsUI.dateToString(this.startDate) + " - " + UtilsUI.dateToString(this.endDate) + "]");
        System.out.println(this.ecString + mCtrl.getHouseGridEnergyConsumptionInPeriod(this.indexOfHG, this.startDate, this.endDate) + "\n");
    }

    private void getRoomEnergyConsumption() {
        String room = mCtrl.getRoomName(this.indexOfRoom);
        System.out.println("Total Energy Consumption of the room in the time period:");
        System.out.println("[Room]: " + room);
        System.out.println(this.timePeriodStr + "[" + UtilsUI.dateToString(this.startDate) + " - " + UtilsUI.dateToString(this.endDate) + "]");
        System.out.println(this.ecString + mCtrl.getRoomEnergyConsumptionInPeriod(this.indexOfRoom, this.startDate, this.endDate) + "\n");
    }
}
