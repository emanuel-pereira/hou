package smarthome.model;

public class Program {
    private String programName;
    private double energyConsumption;

    /**
     * Constructor requiring to set a program name and respective energy consumption for any programmable device
     *
     * @param name              program name
     * @param energyConsumption the amount of energy consumed by the program
     */

    public Program(String name, double energyConsumption) {
        programName = name;
        this.energyConsumption = energyConsumption;
    }

    public String getProgramName() {
        return programName;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }
}
