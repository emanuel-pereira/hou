package smarthome.model;

import java.util.Calendar;

public interface Metered {

    double getEnergyConsumption(Calendar startHour, Calendar endHour);

    void setTime(double runningTimeInHours);

    double getEstimatedEnergyConsumption();


    /**
     * Metered designation is a way of classifying metered elements in the house; it can be applied to devices, rooms, grids, etc...
     *
     * @return the metered type designation
     */
    String getMeteredDesignation();


}
