package smarthome.model;

import java.util.List;

public interface Programmable {

    /**
     * Every programmable device needs to create a program
     * @param name Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    Program createProgram (String name, double value);

    /**
     * Every programmable device needs to add a program to his own Program List
     * @param program The newly create program
     * @return True if added with success
     */
    boolean addProgramToList(Program program);

    /**
     * Every programmable device needs to have a Program List
     * @return List of programmes of the device
     */
    List<Program> getProgramList();
}
