package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("Correctly compare two different type of objects")
    void ifProgramEqualsTypeGA() {
        Program program = new Program ("Eco", "Nominal Power", 0.78);
        TypeGA typeGA = new TypeGA ("Cidade");

        boolean result =typeGA.equals (program);

        assertFalse (result);
    }

    @Test
    @DisplayName("Correctly compare two different programs with the same content")
    void ifProgramEqualsProgram() {
        Program program1 = new Program ("Eco", "Nominal Power", 0.78);
        Program program2 = new Program ("Eco", "Nominal Power", 0.78);

        boolean result = program1.equals (program2);

        assertEquals (program1.hashCode (), program2.hashCode ());

        assertTrue (result);
    }

    @Test
    @DisplayName("Correctly compare two different programs with different content")
    void ifProgramEqualsDifferentProgram() {
        Program program1 = new Program ("Eco", "Nominal Power", 0.78);
        Program program2 = new Program ("Full", "Nominal Power", 0.78);

        boolean result = program1.equals (program2);

        assertNotEquals (program1.hashCode (), program2.hashCode ());

        assertFalse (result);
    }

    @Test
    @DisplayName("Correctly compare a program with itself")
    void ifProgramEqualsSameProgram() {
        Program program = new Program ("Eco", "Nominal Power", 0.78);
        boolean result = program.equals (program);

        assertTrue (result);
    }
}