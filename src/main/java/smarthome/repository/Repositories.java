package smarthome.repository;

import smarthome.model.*;

public class Repositories {
    public static GeoRepository geoRepository;
    public static LocationRepository locationRepository;
    public static OccupationAreaRepository occupationAreaRepository;
    public static SensorRepository sensorRepository;
    public static SensorTypeRepository sensorTypeRepository;
    public static TypeGARepository typeGARepository;
    public static ReadingRepository readingRepository;

    public static void saveGA(GeographicalArea ga) {
        Repositories.occupationAreaRepository.save(ga.getOccupation());
        Repositories.locationRepository.save(ga.getLocation());
        Repositories.typeGARepository.save(ga.getType());
        Repositories.geoRepository.save(ga);
    }

    public static void saveSensor(Sensor s) {
        SensorType save = Repositories.sensorTypeRepository.save(s.getSensorType());
        Location save1 = Repositories.locationRepository.save(s.getLocation());
        Sensor save2 = Repositories.sensorRepository.save(s);

        for (Reading reading : s.getReadingList().getReadingsList()) {
            reading.setSensor(s);
            Repositories.readingRepository.save(reading);
        }
    }
}
