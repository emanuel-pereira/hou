package smarthome.io.ui;

import smarthome.controller.GetEnergyConsumptionInPeriodCTRL;
import smarthome.model.House;

import java.util.GregorianCalendar;

public class GetEnergyConsumptionInPeriodUI {

    private GetEnergyConsumptionInPeriodCTRL mCtrl;
    private int indexOfMetered;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private String timePeriodStr = "[Time Period]: ";
    private String ecString = "[Energy Consumption]: ";


    public GetEnergyConsumptionInPeriodUI(House house) {
        mCtrl = new GetEnergyConsumptionInPeriodCTRL(house);
    }


    public void selectMetered() {
        System.out.println("Choose the metered object from the list below to get its total energy consumption in a time interval:");
        System.out.println(mCtrl.showMetered());
        this.indexOfMetered = UtilsUI.requestIntegerInInterval(1, mCtrl.meteredListSize(),
                "Not a valid option. Please select a device from the list below:\n" + mCtrl.showMeteredDevicesInStr());
        this.indexOfMetered--;
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
        this.getEnergyConsumption();
    }

    private void getEnergyConsumption() {
        String meteredName = mCtrl.getMeteredName(this.indexOfMetered);
        System.out.println("Total Energy Consumption in time period:");
        System.out.println("[Metered]: " + meteredName);
        System.out.println(this.timePeriodStr + "[" + UtilsUI.dateToString(this.startDate) + " - " + UtilsUI.dateToString(this.endDate) + "]");
        System.out.println(this.ecString + mCtrl.getEnergyConsumptionInPeriod(this.indexOfMetered, this.startDate, this.endDate) + "\n");
    }
}
