package smarthome.model;

import java.util.Calendar;

public interface Metered {

    double getEnergyConsumptionInTimeInterval(Calendar startHour, Calendar endHour);

    String getName();
}
