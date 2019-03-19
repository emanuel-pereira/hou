package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.Objects;

public class ProgramWithTimer implements Program {

    private NameValidations nameValidation = new NameValidations();
    private String programName;
    private String attributeName;
    private double energyConsumption;
    private double duration;

    /**
     * Program has a name and the respective value
     * @param programName Name of the program
     * @param energyConsumption The value of the attribute energyConsumption
     */
    public ProgramWithTimer(String programName, double energyConsumption) {
        this.programName = programName;
        this.attributeName = "Energy Consumption";
        this.energyConsumption = energyConsumption;
        this.duration = 0;
    }

    public void setDuration(double duration) {
        if (Utils.valueIsPositive(duration))
            this.duration = duration;
    }

    public double getDuration(){
        return this.duration;
    }

    @Override
    public String getProgramName() {
        return this.programName;
    }

    @Override
    public void setProgramName(String programName) {
        if (nameValidation.alphanumericName(programName))
            this.programName = programName;
    }

    @Override
    public String getAttributeName() {
        return this.attributeName;
    }

    @Override
    public double getAttributeValue() {
        return this.energyConsumption;
    }

    @Override
    public void setAttributeValue(double energyConsumption) {
        if (Utils.valueIsPositive(energyConsumption))
            this.energyConsumption = energyConsumption;

    }

    @Override
    public double getProgramEstimatedEC() {
        return this.energyConsumption;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgramWithTimer)) return false;
        ProgramWithTimer that = (ProgramWithTimer) o;
        return getProgramName ().equals (that.getProgramName ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getProgramName ());
    }
}
