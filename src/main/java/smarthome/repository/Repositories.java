package smarthome.repository;

import smarthome.model.*;

import java.util.List;

public final class Repositories {
    private static GeoRepository geoRepository = null;
    private static SensorRepository sensorRepository = null;
    private static SensorTypeRepository sensorTypeRepository = null;
    private static TypeGARepository typeGARepository= null;
    private static ReadingRepository readingRepository = null;
    private static RoomRepository roomRepository = null;

    /**
     * Private constructor to hide the implicit one
     */
    private Repositories() {
    }

    public static void setGeoRepository(GeoRepository geoRepository) {
        Repositories.geoRepository = geoRepository;
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

    public static void setRoomRepository(RoomRepository roomRepository) {
        Repositories.roomRepository = roomRepository;
    }

    public static GeoRepository getGeoRepository() {
        return geoRepository;
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

    public static RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public static void saveGA(GeographicalArea ga) {

        Repositories.geoRepository.save(ga);

        SensorList sensorList = ga.getSensorListInGA();
        List<Sensor> sensors = sensorList.getSensorList();
        for (Sensor sensor : sensors) {
            saveSensor(sensor);
        }
    }

    public static void saveRoom(Room r) {

        Repositories.roomRepository.save(r);

        SensorList sensorList = r.getSensorListInRoom();
        List<Sensor> sensors = sensorList.getSensorList();
        for (Sensor sensor : sensors) {
            saveSensor(sensor);
        }
    }

    public static void saveSensor(Sensor s) {
        Repositories.sensorRepository.save(s);

        for (Reading reading : s.getReadingList().getReadingsList()) {
            reading.setSensor(s);
            Repositories.readingRepository.save(reading);
        }
    }



}
