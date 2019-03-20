package smarthome.model;

import smarthome.model.validations.NameValidations;
import smarthome.model.validations.Utils;

import java.util.Objects;

public class ProgramMode implements Program {

    private NameValidations nameValidation = new NameValidations ();
    private String programName;
    private String attributeName;
    private double nominalPower;
    private double time;

    /**
     * Program has a name and the respective value
     *
     * @param programName  Name of the program
     * @param nominalPower The value of the attribute nominal power
     */
    public ProgramMode(String programName, double nominalPower) {
        this.programName = programName;
        this.attributeName = "Nominal Power";
        this.nominalPower = nominalPower;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String getProgramName() {
        return this.programName;
    }

    @Override
    public void setProgramName(String programName) {
        if (nameValidation.alphanumericName (programName))
            this.programName = programName;

    }

    @Override
    public String getAttributeName() {
        return this.attributeName;
    }

    @Override
    public double getAttributeValue() {
        return this.nominalPower;
    }

    @Override
    public void setAttributeValue(double nominalPower) {
        if (Utils.valueIsPositive (nominalPower))
            this.nominalPower = nominalPower;
    }

    @Override
    public double getProgramEstimatedEC() {
        double estimatedEnergy;
        estimatedEnergy = this.nominalPower * this.time;
        return estimatedEnergy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgramMode)) {
            return false;
        }
        ProgramMode that = (ProgramMode) o;
        return getProgramName ().equals (that.getProgramName ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getProgramName ());
    }

}
