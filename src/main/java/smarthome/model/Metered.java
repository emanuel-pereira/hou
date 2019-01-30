package smarthome.model;

import java.util.Calendar;

public interface Metered {

    double getEnergyConsumptionInPeriod(Calendar startHour, Calendar endHour);
}
