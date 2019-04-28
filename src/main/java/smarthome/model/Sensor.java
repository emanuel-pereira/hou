package smarthome.model;

import java.util.Calendar;

public interface Sensor {

    String getId();
    String getDesignation();
    SensorType getSensorType();
    boolean isActive();
    boolean deactivate(Calendar pauseDate);
    ReadingList getReadingList();
    Calendar getStartDate();
    double getLastReadingValuePerSensor();
    String getUnit();
    Reading getLastReadingPerSensor();


}
