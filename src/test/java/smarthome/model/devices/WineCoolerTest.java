package smarthome.model.devices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smarthome.model.Device;
import smarthome.model.DeviceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WineCoolerTest {

    @Test
    @DisplayName("Ensure estimated energy consumption of wine cooler returns 0.123 kWh")
    void getEstimatedEnergyConsumption() {
        DeviceType dt = new WineCoolerType();
        Device device = dt.createDevice("Wine Cooler XPTO", 45);
        WineCooler wineCooler = (WineCooler) device;
        double expected = 0.123;
        double result = wineCooler.getEstimatedEnergyConsumption();

        assertEquals(expected, result, 0.01);

        String resultingType = wineCooler.getDeviceType();
        assertEquals("WineCooler", resultingType);

    }

    @Test
    @DisplayName("Ensure estimated energy consumption of wine cooler does not return 0 kWh")
    void getEstimatedEnergyConsumptionNotEqualsZero() {
        DeviceType dt = new WineCoolerType();
        Device device = dt.createDevice("Wine Cooler XPTO", 45);
        WineCooler wineCooler = (WineCooler) device;
        double expected = 0;
        double result = wineCooler.getEstimatedEnergyConsumption();

        assertNotEquals(expected, result);
    }
}