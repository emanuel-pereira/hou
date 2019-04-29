package smarthome.model.devices;

import smarthome.model.DeviceSpecs;
import smarthome.model.Program;
import smarthome.model.ProgramMode;
import smarthome.model.Programmable;

import java.util.ArrayList;
import java.util.List;

public class Oven extends GenericDevice implements Programmable{

    private final List<Program> programList = new ArrayList<>();
    private ProgramMode meteredProgram;

    public Oven(String deviceName, DeviceSpecs deviceSpecs, double deviceNominalPower) {
        super(deviceName, deviceSpecs, deviceNominalPower);
    }

    @Override
    public double getEstimatedEnergyConsumption() {
        if (this.meteredProgram != null) {
            double energy;
            energy = this.meteredProgram.getProgramEstimatedEC ();
            return energy;
        } else {
            return 0;
        }
    }

    @Override
    public ProgramMode createProgram(String name, double value) {
        return new ProgramMode(name, value);
    }

    @Override
    public boolean addProgramToList(Program newProgram) {
        if (!this.programList.contains(newProgram)) {
            this.programList.add(newProgram);
            return true;
        } else return false;
    }

    @Override
    public List<Program> getProgramList() {
        return this.programList;
    }

    @Override
    public void setMeteredProgram(String programName) {
        for (Program program : this.programList) {
            if (program.getProgramName ().equals (programName)) {
                this.meteredProgram = (ProgramMode) program;
            }
        }
    }

    @Override
    public Program getMeteredProgram() {
        return this.meteredProgram;
    }

}
