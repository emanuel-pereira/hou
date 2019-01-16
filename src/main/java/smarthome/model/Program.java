package smarthome.model;

public class Program {
    private int mDuration;
    private double mEnergyConsumption;

    /**
     * Constructor requiring to set a duration and respective energy consumption for any program of a device
     * @param duration time duration of the program
     * @param energyConsumption the amount of energy consumed by the program
     */

    public Program(int duration, double energyConsumption){
        mDuration=duration;
        mEnergyConsumption=energyConsumption;
    }

}
