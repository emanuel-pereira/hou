package smarthome.controller;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.mapper.SensorMapper;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.SensorList;

import java.util.Calendar;
import java.util.List;

public class DeactivateSensorCTRL {

    GAList gaList;
    private final SensorMapper sensorMapper = new SensorMapper ();
    private final GeographicalAreaMapper gaMapper = new GeographicalAreaMapper ();

    public DeactivateSensorCTRL(GAList gaList) {
        this.gaList = gaList;

    }

    /**
     * Show list of geographical list
     * @return List of GAs
     */
    public List<GeographicalAreaDTO> getGAList() {
        return this.gaMapper.toDtoList (gaList);
    }


    /**
     * Get the GA by ID
     * @param gaDTOId DTO id
     * @return Geographical area
     */
    public GeographicalArea getGAById(String gaDTOId) {
        for (GeographicalArea geographicalArea : this.gaList.getGAList ()) {
            if (geographicalArea.getId ().matches (gaDTOId)) {
                return geographicalArea;
            }
        }
        throw new NullPointerException ();
    }

    /**
     * Deactivate selected sensor by searching the SensorList of the GA using the DTO id
     * @param gaDTOId id of the GA DTO
     * @param sensorDTOId id of the sensor DTO
     * @param pauseDate deactivation date
     * @return true if deactivated with success
     */
    public boolean deactivateSensor(String gaDTOId, String sensorDTOId, Calendar pauseDate) {
        GeographicalArea ga = getGAById (gaDTOId);
        SensorList sensorList = ga.getSensorListInGA ();
        for (Sensor s : sensorList.getSensorList ()) {
            if (s.getId ().matches (sensorDTOId)) {
                sensorList.deactivateSensor (sensorDTOId, pauseDate);
                return true;
            }
        }
        return false;
    }

    /**
     * List of active sensors in the GA
     * @param gaDTOId id og the GA
     * @return List of sensors
     */
    public List<SensorDTO> getSensorIfActiveDto(String gaDTOId) {
        GeographicalArea ga = this.getGAById (gaDTOId);
        SensorList sensorList = ga.getSensorListInGA ();
        SensorList activeSensors = sensorList.getActiveSensors ();
        return this.sensorMapper.toDtoList (activeSensors);
    }

    /**
     * Check if the sensor is active (true) or not (false)
     * @param gaDTOId id of the GA DTO
     * @param sensorDTOId id of the Sensor DTO
     * @return True if active
     */
    public boolean sensorStatus(String gaDTOId, String sensorDTOId) {
        GeographicalArea ga = getGAById (gaDTOId);
        SensorList sensorList = ga.getSensorListInGA ();
        for (Sensor s : sensorList.getSensorList ())
            if (s.getId ().matches (sensorDTOId) && s.isActive ()) {
                return true;
            }
        return false;
    }
}