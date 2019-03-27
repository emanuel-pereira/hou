package smarthome.controller;

import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.mapper.SensorMapper;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Sensor;
import smarthome.model.SensorList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DeactivateSensorCTRL {

    GAList gaList;
    private SensorMapper sensorMapper = new SensorMapper ();
    private GeographicalAreaMapper gaMapper = new GeographicalAreaMapper ();

    public DeactivateSensorCTRL(GAList gaList) {
        this.gaList = gaList;

    }

    public List<GeographicalAreaDTO> getGAList() {
        return this.gaMapper.toDtoList (gaList);
    }


    public GeographicalArea getGAById(String gaDTOId) {
        for (GeographicalArea geographicalArea : this.gaList.getGAList ()) {
            if (geographicalArea.getIdentification ().matches (gaDTOId)) {
                return geographicalArea;
            }
        }
        throw new NullPointerException ();
    }

    public int getGAListSize() {
        return this.gaList.size ();
    }

    public boolean deactivateSensor(String gaDTOId, String sensorDTOId, Calendar pauseDate) {
        GeographicalArea ga = getGAById (gaDTOId);
        SensorList sensorList = ga.getSensorListInGA ();
        for (Sensor s : sensorList.getSensorList ()) {
            if (s.getId ().matches (sensorDTOId)) {
                s.deactivate (pauseDate);
                return true;
            }
        }
        return false;
    }

    public List<SensorDTO> getSensorIfActiveToDto(String gaDTOId) {
        List<SensorDTO> sensorListIsActiveDTO = new ArrayList<> ();
        GeographicalArea ga = this.getGAById (gaDTOId);
        SensorList sensorList = ga.getSensorListInGA ();
        for (Sensor s : sensorList.getActiveSensors ().getSensorList ()) {
            SensorDTO sDTO = this.sensorMapper.toDto (s);
            sensorListIsActiveDTO.add (sDTO);
        }
        return sensorListIsActiveDTO;
    }

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


