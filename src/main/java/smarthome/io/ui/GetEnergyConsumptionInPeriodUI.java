package smarthome.io.ui;

import smarthome.controller.GetEnergyConsumptionInPeriodCTRL;

import java.util.GregorianCalendar;

public class GetEnergyConsumptionInPeriodUI {

    private final GetEnergyConsumptionInPeriodCTRL mCtrl;
    private int indexOfMetered;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private final String timePeriodStr = "[Time Period]: ";
    private final String ecString = "[Energy Consumption]: ";


    public GetEnergyConsumptionInPeriodUI() {
        mCtrl = new GetEnergyConsumptionInPeriodCTRL();
    }


    public void selectMetered() {
        System.out.println("Choose the metered object from the list below to get its total energy consumption in a time interval:");
        System.out.println(mCtrl.showMeteredCTRL());
        this.indexOfMetered = UtilsUI.requestIntegerInInterval(1, mCtrl.meteredListSize(),
                "Not a valid option. Please select a device from the list below:\n" + mCtrl.showMeteredCTRL());
        this.indexOfMetered--;
        this.getStartDate();
    }


    private void getStartDate() {
        System.out.println("Insert the time interval in which you want to get the energy consumption.");
        System.out.println("Insert the starting date in YYYY-MM-DD HH:mm format:");
        this.startDate = UtilsUI.requestDateTime("Please insert a valid start date and time in YYYY-MM-DD HH:mm format.");
        this.getEndDate();
    }

    private void getEndDate() {
        System.out.println("Insert the ending date in YYYY-MM-DD HH:mm format:");
        this.endDate = UtilsUI.requestDateTime("Please insert a valid end date and time in YYYY-MM-DD HH:mm format.");
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
