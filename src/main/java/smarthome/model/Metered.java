package smarthome.model;

import java.util.Calendar;

public interface Metered {

    double getEnergyConsumption(Calendar startHour, Calendar endHour);

    double getEstimatedEnergyConsumption();

    String getName();
}
