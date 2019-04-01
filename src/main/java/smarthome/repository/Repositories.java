package smarthome.repository;

import smarthome.model.GeographicalArea;
import smarthome.model.Reading;
import smarthome.model.Sensor;
import smarthome.model.SensorList;

import java.util.List;

public final class Repositories {
    private static GeoRepository geoRepository = null;
    private static LocationRepository locationRepository = null;
    private static OccupationAreaRepository occupationAreaRepository = null;
    private static SensorRepository sensorRepository = null;
    private static SensorTypeRepository sensorTypeRepository = null;
    private static TypeGARepository typeGARepository;
    private static ReadingRepository readingRepository = null;

    /**
     * Private constructor to hide the implicit one
     */
    private Repositories() {
    }

    public static void setGeoRepository(GeoRepository geoRepository) {
        Repositories.geoRepository = geoRepository;
    }

    public static void setLocationRepository(LocationRepository locationRepository) {
        Repositories.locationRepository = locationRepository;
    }

    public static void setOccupationAreaRepository(OccupationAreaRepository occupationAreaRepository) {
        Repositories.occupationAreaRepository = occupationAreaRepository;
    }

    public static void setSensorRepository(SensorRepository sensorRepository) {
        Repositories.sensorRepository = sensorRepository;
    }

    public static void setSensorTypeRepository(SensorTypeRepository sensorTypeRepository) {
        Repositories.sensorTypeRepository = sensorTypeRepository;
    }

    public static void setTypeGARepository(TypeGARepository typeGARepository) {
        Repositories.typeGARepository = typeGARepository;
    }

    public static void setReadingRepository(ReadingRepository readingRepository) {
        Repositories.readingRepository = readingRepository;
    }

    public static GeoRepository getGeoRepository() {
        return geoRepository;
    }

    public static LocationRepository getLocationRepository() {
        return locationRepository;
    }

    public static OccupationAreaRepository getOccupationAreaRepository() {
        return occupationAreaRepository;
    }

    public static SensorRepository getSensorRepository() {
        return sensorRepository;
    }

    public static SensorTypeRepository getSensorTypeRepository() {
        return sensorTypeRepository;
    }

    public static TypeGARepository getTypeGARepository() {
        return typeGARepository;
    }

    public static ReadingRepository getReadingRepository() {
        return readingRepository;
    }

    public static void saveGA(GeographicalArea ga) {
        Repositories.occupationAreaRepository.save(ga.getOccupation());
        Repositories.locationRepository.save(ga.getLocation());
        Repositories.typeGARepository.save(ga.getType());
        Repositories.geoRepository.save(ga);

        SensorList sensorList = ga.getSensorListInGA();
        List<Sensor> sensors = sensorList.getSensorList();
        for (Sensor sensor : sensors) {
            saveSensor(sensor);
        }
    }


    public static void saveSensor(Sensor s) {
        Repositories.sensorTypeRepository.save(s.getSensorType());
        Repositories.locationRepository.save(s.getLocation());
        Repositories.sensorRepository.save(s);

        for (Reading reading : s.getReadingList().getReadingsList()) {
            reading.setSensor(s);
            Repositories.readingRepository.save(reading);

        }
    }
}
