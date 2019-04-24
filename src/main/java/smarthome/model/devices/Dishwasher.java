package smarthome.model.devices;

import smarthome.model.DeviceSpecs;
import smarthome.model.Program;
import smarthome.model.ProgramWithTimer;
import smarthome.model.Programmable;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher extends GenericDevice implements Programmable {

    private final List<Program> programList = new ArrayList<>();
    private ProgramWithTimer meteredProgram;

    /**
     * Dishwasher constructor
     *
     * @param deviceName   Dishwasher name
     * @param deviceSpecs  Dishwasher specs
     * @param nominalPower Dishwasher nominal power
     */

    public Dishwasher(String deviceName, DeviceSpecs deviceSpecs, double nominalPower) {
        super(deviceName, deviceSpecs, nominalPower);
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
     * Create a Dishwasher program
     *
     * @param name  Name of the program
     * @param value Value that represents the nominal power or energy consumption of the program
     * @return The created program
     */
    @Override
    public ProgramWithTimer createProgram(String name, double value) {
        return new ProgramWithTimer(name, value);
    }

    /**
     * Add program to the program list of the Dishwasher
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
     * Get program list of the Dishwasher
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
                this.meteredProgram = (ProgramWithTimer) program;
            }
        }
    }

    @Override
    public Program getMeteredProgram() {
        return this.meteredProgram;
    }

}
