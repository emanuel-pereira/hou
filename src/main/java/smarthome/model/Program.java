package smarthome.model;

public class Program {
    private String mName;
    private double mEnergyConsumption;

    /**
     * Constructor requiring to set a program name and respective energy consumption for any programmable device
     *
     * @param name              program name
     * @param energyConsumption the amount of energy consumed by the program
     */

    public Program(String name, double energyConsumption) {
        mName = name;
        mEnergyConsumption = energyConsumption;
    }

}
