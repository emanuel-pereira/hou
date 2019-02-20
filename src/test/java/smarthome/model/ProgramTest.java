package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void getProgramName() {
        Program program = new Program("Eco",30);
        String expected="Eco";
        String result=program.getProgramName();
        assertEquals(expected,result);
    }

    @Test
    void getEnergyConsumption() {
        Program program = new Program("Eco",30);
        double expected=30;
        double result=program.getEnergyConsumption();
        assertEquals(expected,result);
    }
}