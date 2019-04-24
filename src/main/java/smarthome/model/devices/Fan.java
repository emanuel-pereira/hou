package smarthome.model.devices;

import smarthome.model.DeviceSpecs;
import smarthome.model.Program;
import smarthome.model.ProgramMode;
import smarthome.model.Programmable;
import java.util.ArrayList;
import java.util.List;

public class Fan extends GenericDevice implements Programmable {

    private final List<Program> programList = new ArrayList<>();
    private ProgramMode meteredProgram;

    public Fan(String name, DeviceSpecs deviceSpecs, double nominalPower) {
        super(name, deviceSpecs, nominalPower);
    }

    public double getEnergyConsumption() {
        if (this.meteredProgram != null) {
            double energy;
            energy = this.meteredProgram.getProgramEstimatedEC();
            return energy;
        } else {
            return 0;
        }
    }

    /**
     * Create a Fan program
     *
     * @param name  Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public ProgramMode createProgram(String name, double value) {
        return new ProgramMode(name, value);
    }

    /**
     * Add program to the program list of the Fan
     *
     * @param newProgram Previous created program
     * @return True if added with success
     */
    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;
    }

    /**
     * Get program list of the Fan
     *
     * @return Program list of the device
     */
    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

    @Override
    public void setMeteredProgram(String programName) {
        for (Program program : this.programList) {
            if (program.getProgramName().equals(programName)) {
                this.meteredProgram = (ProgramMode) program;
            }
        }
    }

    @Override
    public Program getMeteredProgram() {
        return this.meteredProgram;
    }

}