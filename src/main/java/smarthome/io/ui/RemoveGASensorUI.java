package smarthome.io.ui;

import smarthome.controller.RemoveGASensorCTRL;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.dto.SensorDTO;
import smarthome.model.GAList;

import java.util.List;


public class RemoveGASensorUI {

    private RemoveGASensorCTRL ctrl;
    private GeographicalAreaDTO selectedGADTO;
    private String designationMsg=" | Designation: ";

    public RemoveGASensorUI(GAList gaList) {
        this.ctrl = new RemoveGASensorCTRL(gaList);
    }

    //Broke this method is small methods
    /**
     * First: get the GA List (Dto) and validate if the gaList is empty. If so, show message
     * If not, show it
     * Second: the user chooses de GA (Dto)
     */
    public void selectGA() {
        List<GeographicalAreaDTO> gaListDTO = ctrl.getGAListDTO();
        if (gaListDTO.isEmpty()) {
            System.out.println("The list of geographical areas is empty. Please add at least one.");
            return;
        }
        System.out.println("Choose a geographical area from the list below:");
        int counter = 1;
        for (GeographicalAreaDTO gaDTO : gaListDTO) {
            System.out.print(counter++ + " Id: " + gaDTO.getIdentification());
            System.out.println(designationMsg + gaDTO.getDesignation());
        }
        int index = UtilsUI.requestIntegerInInterval(1, gaListDTO.size(), "Please choose a valid option");
        index--;
        selectedGADTO = gaListDTO.get(index);
        selectSensor();
    }

    //Broke this method is small methods
    /**
     * First: get the Sensor List (Dto) in the GA and validate if the list is empty. If so, show message
     * If not, show it
     * Second: the user chooses de Sensor (Dto)
     * Input the date (future work: date validation)
     * Show the status of the chosen Sensor
     */
    public void selectSensor() {
        if (selectedGADTO.getSensorListDTO().isEmpty()) {
            System.out.println("The list of sensors is empty. Please add at least one.");
            return;
        }
        System.out.println("Choose a sensor from the list below:");
        List<SensorDTO> sensorListDTO = selectedGADTO.getSensorListDTO();
        int counter = 1;
        for (SensorDTO sensorDTO : sensorListDTO) {
            System.out.print(counter++ + " Id: " + sensorDTO.getId());
            System.out.println(designationMsg + sensorDTO.getDesignation());
        }
        int index = UtilsUI.requestIntegerInInterval(1, sensorListDTO.size(), "Please choose a valid option");
        index--;
        SensorDTO sensorDTO = sensorListDTO.get(index);
        String gaDTOId = selectedGADTO.getIdentification();
        String sensorDTOId = sensorDTO.getId();
        if (this.ctrl.removeSensor(gaDTOId, sensorDTOId)) {
            System.out.println("The following sensor was successfully removed from the geographical area " + selectedGADTO.getDesignation() + ":");
            System.out.println(" - Id: " + sensorDTO.getId() + designationMsg + sensorDTO.getDesignation() + "\n");
        }
    }


}
