package smarthome.model;

public interface Program {

    String getProgramName();

    void setProgramName(String programName);

    String getAttributeName();

    double getEnergyConsumption();

    void setEnergyConsumption(double attributeValue);

    double getProgramEstimatedEC();

    boolean equals(Object o);

    int hashCode();
}

