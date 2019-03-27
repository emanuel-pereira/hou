package smarthome.io.ui;

import smarthome.controller.DeactivateSensorCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;

import java.util.Calendar;
import java.util.List;

public class DeactivateSensorUI {


    DeactivateSensorCTRL controller;
    private GeographicalAreaDTO selectedGADto;
    private String designation = ". Designation: ";

    public DeactivateSensorUI(GAList gaList) {
        this.controller = new DeactivateSensorCTRL (gaList);
    }

    public void selectGA() {
        List<GeographicalAreaDTO> gaListDTO = this.controller.getGAList ();
        if (gaListDTO.isEmpty ()) {
            System.out.println ("There are no Geographical Areas. Please ask the Administrator to create/import them.\n");
            return;
        }
        System.out.println ("Please choose a Geographical Area from the list below:");
        int counter = 1;
        for (GeographicalAreaDTO gaDTO : gaListDTO) {
            System.out.print (counter++ + ". Id: " + gaDTO.getIdentification () + this.designation + gaDTO.getDesignation () + ".\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, gaListDTO.size (), "Please choose a valid option.");
        index--;
        this.selectedGADto = gaListDTO.get (index);
        this.selectSensor ();

    }

    public void selectSensor() {
        String gaDTOId = selectedGADto.getIdentification ();
        if (this.selectedGADto.getSensorListDTO ().isEmpty ()) {
            System.out.println ("The Geographical Area has no sensors. Please ask the Administrator to create/import them.\n");
            return;
        }
        List<SensorDTO> sensorListDTO = this.controller.getSensorIfActiveToDto (gaDTOId);
        if (sensorListDTO.isEmpty ()) {
            System.out.println ("There are no active sensors.\n");
            return;
        }
        System.out.println ("Choose an active Sensor from the list below:");
        int counter = 1;
        for (SensorDTO sensorDTO : sensorListDTO) {
            System.out.print (counter++ + " Id: " + sensorDTO.getId () + this.designation + sensorDTO.getDesignation () + ". Active: " + sensorDTO.isActive () + "\n");
        }
        int index = UtilsUI.requestIntegerInInterval (1, sensorListDTO.size (), "Please choose a valid option");
        index--;
        SensorDTO sensorDTO = sensorListDTO.get (index);
        String sensorDTOId = sensorDTO.getId ();
        System.out.println ("Insert the deactivation date for the sensor in yyyy-MM-dd format:");
        Calendar date = UtilsUI.requestDate ("Please insert a valid date (yyyy-MM-dd format).");
        if (this.controller.deactivateSensor (gaDTOId, sensorDTOId, date)) {
            System.out.println ("The following sensor was successfully deactivated from the geographical area " + selectedGADto.getDesignation () + ":");
            System.out.println (" - Id: " + sensorDTO.getId () + this.designation + sensorDTO.getDesignation () + ". Active: " + this.controller.sensorStatus (gaDTOId, sensorDTOId) + ".\n");}
    }


}
