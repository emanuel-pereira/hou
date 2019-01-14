package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramListTest {

    @Test
    @DisplayName("Ensure same program instance is only added once to device list")

    void addProgram() {
        ProgramList programList= new ProgramList();
        Program p1= new Program(2,50);
        programList.addProgram(p1);

        int expected1= 1;
        int result1=programList.getProgramList().size();
        assertEquals(expected1,result1);

        programList.addProgram(p1);
        int expected2= 1;
        int result2=programList.getProgramList().size();
        assertEquals(expected2,result2);
    }

    @Test
    @DisplayName("Ensure newProgram method creates local instance of program and that addProgram method adds it programList")
    void newProgram() {
        ProgramList programList= new ProgramList();
        Program program = programList.newProgram(2,50);
        programList.addProgram(program);

        Program expected= program;
        Program result= programList.getProgramList().get(0);

        assertEquals(expected,result);
    }
}