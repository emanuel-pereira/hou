package smarthome.io.ui;

import smarthome.controller.cli.GetEnergyConsumptionOfWaterHeatingCTRL;
import smarthome.dto.DeviceDTO;

public class GetEnergyConsumptionOfWaterHeatingUI {

    private GetEnergyConsumptionOfWaterHeatingCTRL ctrl;
    public GetEnergyConsumptionOfWaterHeatingUI() {
        ctrl = new GetEnergyConsumptionOfWaterHeatingCTRL();
    }

    public void run() {

        for (DeviceDTO deviceDTO : ctrl.getDevicesInAllRoomsByType("ElectricWaterHeater")) {
            String coldWaterTemperatureStr = "coldWaterTemperature";
            String volumeOfWaterToHeatStr = "volumeOfWaterToHeat";

            System.out.println("Please specify the cold water temperature (ºC) of the electric water heater "+deviceDTO.getName()+":");
            double coldWaterTemperature=UtilsUI.requestDoubleInInterval(0,100,"Please insert a valid decimal value.");
            ctrl.setAttribute(deviceDTO.getName(),coldWaterTemperatureStr,coldWaterTemperature);
            System.out.println("Please insert the volume of water to heat (liters) in the electric water heater "+deviceDTO.getName()+":");
            double volumeOfWaterToHeat=UtilsUI.requestDoubleInInterval(0,100,"Please insert a positive value.");
            ctrl.setAttribute(deviceDTO.getName(),volumeOfWaterToHeatStr,volumeOfWaterToHeat);
        }
        System.out.println("The total energy used in heating water in a given day is " + ctrl.getEstimatedEnergyConsumptionByDeviceType("ElectricWaterHeater") + " KWh.\n");
    }
}
