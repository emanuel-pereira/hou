package smarthome.model;

public interface Program {

    String getProgramName();

    void setProgramName(String programName);

    String getAttributeName();

    double getAttributeValue();

    void setAttributeValue(double attributeValue);

    double getProgramEstimatedEC();

    boolean equals(Object o);

    int hashCode();
}
