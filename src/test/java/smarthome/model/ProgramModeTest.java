package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramModeTest {

    @Test
    @DisplayName("Set and get program name")
    void setProgramName() {
        Program program = new ProgramMode ("Eco", 100);
        program.setProgramName ("Eco Friendly");

        String expected = "Eco Friendly";
        String result = program.getProgramName ();

        assertEquals (expected, result);
    }


    @Test
    @DisplayName("Set and get attribute value")
    void setAttributeValue() {
        ProgramMode program = new ProgramMode ("Eco", 0.78);
        program.setAttributeValue (0.80);

        double expected = 0.80;
        double result = program.getAttributeValue ();

        assertEquals (expected, result);
    }

    @Test
    @DisplayName("Correctly compare two different type of objects")
    void ifProgramEqualsTypeGA() {
        ProgramMode program = new ProgramMode ("Eco", 0.78);
        TypeGA typeGA = new TypeGA ("Cidade");

        boolean result =typeGA.equals (program);

        assertFalse (result);
    }

    @Test
    @DisplayName("Correctly compare two different programs with the same content")
    void ifProgramEqualsProgram() {
        ProgramMode program1 = new ProgramMode ("Eco", 0.78);
        ProgramMode program2 = new ProgramMode ("Eco", 0.78);

        boolean result = program1.equals (program2);

        assertEquals (program1.hashCode (), program2.hashCode ());

        assertTrue (result);
    }

    @Test
    @DisplayName("Correctly compare two different programs with different content")
    void ifProgramEqualsDifferentProgram() {
        ProgramMode program1 = new ProgramMode ("Eco", 0.78);
        ProgramMode program2 = new ProgramMode ("Full", 0.78);

        boolean result = program1.equals (program2);

        assertNotEquals (program1.hashCode (), program2.hashCode ());

        assertFalse (result);
    }

    @Test
    @DisplayName("Correctly compare a program with itself")
    void ifProgramEqualsSameProgram() {
        ProgramMode program = new ProgramMode ("Eco", 0.78);
        boolean result = program.equals (program);

        assertTrue (result);
    }

    @Test
    @DisplayName("Correctly compare a program with a different object")
    void ifNotAProgram() {
        ProgramMode program = new ProgramMode("Eco", 0.78);
        Configuration c = new Configuration();
        boolean result = program.equals(c);

        assertFalse(result);
    }

    @Test
    @DisplayName("Correctly get the attribute name")
    void getAttributeName() {
        ProgramMode program = new ProgramMode ("Eco", 0.78);

        String expected = "Nominal Power";
        String result = program.getAttributeName ();

        assertEquals (expected, result);
    }

}