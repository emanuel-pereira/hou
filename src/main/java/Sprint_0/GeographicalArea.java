package Sprint_0;


import java.util.ArrayList;
import java.util.List;

public class GeographicalArea {
    String mDesignation;
    TypeOfGeographicalArea mTypeArea;
    Location mLocation;
    List<Sensor> mSensorList;

    public GeographicalArea(String designation, TypeOfGeographicalArea typeArea) {
        mDesignation = designation;
        mTypeArea = typeArea;
    }

    public GeographicalArea(String designation, TypeOfGeographicalArea typeArea, Location location) {
        mDesignation = designation;
        mTypeArea = typeArea;
        mLocation = location;
    }

    public void addSensor(Sensor sensor) {
        this.mSensorList.add(sensor);
    }

    public List<Sensor> getListOfSensor() {
        return this.mSensorList;
    }

    public List<Reading> getLastValuesOfSensorsInGeographicalArea() {
        List<Reading> lastSensorsReadings = new ArrayList<>(mSensorList.size());
        Sensor s = null;
        for (int i = 0; i < mSensorList.size(); i++) {
            lastSensorsReadings.add(s.getListReadingLastValuePerSensor(mSensorList.get(i)));
        }
        return lastSensorsReadings;
    }
}

