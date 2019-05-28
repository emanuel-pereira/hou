package smarthome.repository;

import smarthome.model.*;

import java.util.Calendar;
import java.util.List;

public final class Repositories {

    private static GeoRepository geoRepository = null;
    private static ExternalSensorRepository externalSensorRepository = null;
    private static InternalSensorRepository internalSensorRepository = null;
    private static SensorTypeRepository sensorTypeRepository = null;
    private static TypeGARepository typeGARepository = null;
    private static RoomRepository roomRepository = null;
    private static HouseGridRepository gridsRepository = null;

    /**
     * Private constructor to hide the implicit one
     */
    private Repositories() {
    }

    public static void setGeoRepository(GeoRepository geoRepository) {
        Repositories.geoRepository = geoRepository;
    }

    public static void setExternalSensorRepository(ExternalSensorRepository externalSensorRepository) {
        Repositories.externalSensorRepository = externalSensorRepository;
    }

    public static void setInternalSensorRepository(InternalSensorRepository internalSensorRepository) {
        Repositories.internalSensorRepository = internalSensorRepository;
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

    public static void setGridsRepository(HouseGridRepository gridsRepository) {
        Repositories.gridsRepository = gridsRepository;
    }

    public static GeoRepository getGeoRepository() {
        return geoRepository;
    }


    public static ExternalSensorRepository getExternalSensorRepository() {
        return externalSensorRepository;
    }

    public static ReadingList getReadingsExternalSensor (String id, Calendar startDate, Calendar endDate){

        ExternalSensor sensor = getExternalSensorRepository().findById(id).get();

        return sensor.getSensorBehavior().getReadingList().filterByDate(startDate, endDate);
    }

    public static InternalSensorRepository getInternalSensorRepository() {
        return internalSensorRepository;
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

    public static HouseGridRepository getGridsRepository() {
        return gridsRepository;
    }

    public static void saveGA(GeographicalArea ga) {
        Repositories.geoRepository.save(ga);

        SensorList sensorList = ga.getSensorListInGa();
        List<Sensor> sensors = sensorList.getSensorList();
        for (Sensor sensor : sensors) {
            saveExternalSensor((ExternalSensor) sensor);
        }
    }

    public static void saveRoom(Room r) {
        Repositories.roomRepository.save(r);

        SensorList sensorList = r.getSensorListInRoom();
        List<Sensor> sensors = sensorList.getSensorList();
        for (Sensor sensor : sensors) {
            saveInternalSensor((InternalSensor) sensor);
        }
    }


    public static void saveExternalSensor(ExternalSensor s) {
        Repositories.externalSensorRepository.save(s);
    }

    public static void saveInternalSensor(InternalSensor s) {
        Repositories.internalSensorRepository.save(s);
    }

}