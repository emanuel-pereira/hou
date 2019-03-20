package smarthome.model.readers;

public interface Readable {

    String getID();
    String getName();
    String getType();
    String getWidth();
    String getLength();
    String getLocation();
    String getUnits();
    String getStartDate();
    String getReadingValue();
    String getReadingTimeStamp();
}
