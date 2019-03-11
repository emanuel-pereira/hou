package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramTest {

    @Test
    @DisplayName("Set and get program name")
    void setProgramName() {
        Program program = new Program ("Eco", "Energy consumption", 100);
        program.setProgramName ("Eco Friendly");

        String expected = "Eco Friendly";
        String result = program.getProgramName ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get attribute name")
    void setAttributeName() {
        Program program = new Program ("Eco", "Nominal", 0.78);
        program.setAttributeName ("Nominal Power");

        String expected = "Nominal Power";
        String result = program.getAttributeName ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Set and get attribute value")
    void setAttributeValue() {
        Program program = new Program ("Eco", "Nominal Power", 0.78);
        program.setAttributeValue (0.80);

        double expected = 0.80;
        double result = program.getAttributeValue ();

        assertEquals (expected, result);
    }

}