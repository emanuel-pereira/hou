package smarthome.controller;

import smarthome.Application;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.*;
import smarthome.repository.Repositories;

import java.util.List;

public class RemoveGASensorCTRL {
    private GAList gaList;
    private GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Application.class);


    public RemoveGASensorCTRL(GAList gaList) {
        this.gaList = gaList;
    }


    /**
     * Method that iterates the geographical area list and converts each geographical area in a geographical area DTO which has only the necessary attributes
     * to display to the user.
     *
     * @return a list of geographical area DTOs with attributes id, designation, typeDTO and sensorListDTO
     */
    public List<GeographicalAreaDTO> getGAListDTO() {
        return gaMapper.toDtoList(gaList);
    }

    /**
     * This method iterates the geographical area comparing for each geographical area its id with the id passed as parameter.
     *
     * @param gaDTOId String value correspondent to the id of the geographical area passed as parameter
     * @return geographical area object if its id matches the parameter gaDTOid, else throws a null pointer exception.
     */
    private GeographicalArea getGAById(String gaDTOId) {
        for (GeographicalArea geographicalArea : gaList.getGAList()) {
            if (geographicalArea.getId().matches(gaDTOId)) {
                return geographicalArea;
            }
        }
        throw new NullPointerException();
    }


    /**
     * This method looks for the geographical area with the same Id as gaDTOId parameter and then looks
     * within its sensorList for the sensor which has the same id as the sensorDTOid parameter
     *
     * @param gaDTOId     String value correspondent to the geographical area DTO id
     * @param sensorDTOId String value correspondent to the geographical area DTO id
     * @return true if sensor is removed from the geographical area sensor list, otherwise returns false.
     */
    public boolean removeSensor(String gaDTOId, String sensorDTOId) {
        GeographicalArea ga = getGAById(gaDTOId);
        SensorList sensorList = ga.getSensorListInGA();
        for (Sensor sensor : sensorList.getSensorList()) {
            if (sensor.getId().matches(sensorDTOId)) {
                sensorList.removeSensor(sensor);
                try {
                    saveSensorReadings(sensor);
                    //Repository call
                    Repositories.getSensorRepository().delete(sensor);
                } catch (NullPointerException e) {
                    log.info("Repository unreachable");

                }
                return true;
            }
        }
        return false;
    }

    /**
     * Method that persists the readings in its repository of the sensor inputted as parameter.
     * @param sensor inputted as parameter
     */
    private static void saveSensorReadings(Sensor sensor) {
        ReadingList readingList = sensor.getReadingList();
        for (Reading reading : readingList.getReadingsList()) {
            //Repository call
            Repositories.getReadingRepository().delete(reading);
        }
    }
}
