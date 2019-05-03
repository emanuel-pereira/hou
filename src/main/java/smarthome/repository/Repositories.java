package smarthome.repository;

import smarthome.model.GeographicalArea;
import smarthome.model.Room;
import smarthome.model.Sensor;
import smarthome.model.SensorList;

import java.util.List;

public final class Repositories {

    private static GeoRepository geoRepository = null;
    private static SensorRepository sensorRepository = null;
    private static SensorTypeRepository sensorTypeRepository = null;
    private static TypeGARepository typeGARepository= null;
    private static RoomRepository roomRepository = null;
    private static GridRepository gridsRepository = null;

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

    public static void setRoomRepository(RoomRepository roomRepository) {
        Repositories.roomRepository = roomRepository;
    }

    public static void setGridsRepository(GridRepository gridsRepository) {
        Repositories.gridsRepository = gridsRepository;
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

    public static RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public static GridRepository getGridsRepository() {
        return gridsRepository;
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
        Repositories.getSensorTypeRepository().save(s.getSensorType());

        Repositories.sensorRepository.save(s);
    }



}
