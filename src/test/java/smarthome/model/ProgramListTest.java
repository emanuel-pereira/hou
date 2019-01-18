package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramListTest {

    @Test
    @DisplayName("Ensure same program instance is only added once to device list")
    void addProgram() {
        ProgramList programList = new ProgramList ();
        Program p1 = new Program ("Fast", 50);
        programList.addProgram (p1);

        int expected1 = 1;
        int result1 = programList.getProgramList ().size ();
        assertEquals (expected1, result1);

        programList.addProgram (p1);
        int expected2 = 1;
        int result2 = programList.getProgramList ().size ();
        assertEquals (expected2, result2);
    }

    @Test
    @DisplayName("Ensure same program instance is only added once to device list and return true or false")
    void addProgramConfirmations() {
        ProgramList programList = new ProgramList ();
        Program p1 = programList.newProgram ("Fast", 50);

        boolean result1 = programList.addProgram (p1);
        assertTrue (result1);

        Program expectedProgram = p1;
        assertEquals (expectedProgram, programList.getProgramList ().get (0));

        boolean result2 = programList.addProgram (p1);
        assertFalse (result2);
    }

    @Test
    @DisplayName("Ensure newProgram method creates local instance of program and that addProgram method adds it programList")
    void newProgram() {
        ProgramList programList = new ProgramList ();
        Program program = programList.newProgram ("Economic", 50);
        programList.addProgram (program);

        Program expected = program;
        Program result = programList.getProgramList ().get (0);

        assertEquals (expected, result);
    }
}