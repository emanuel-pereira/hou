package smarthome.model;

public class Program {
    private String programName;
    private String attributeName;
    private double attributeValue;

    /**
     * Program has a name and the respective energy consumption or nominal power name and value
     *
     * @param programName    Program name
     * @param attributeName  Program attribute that can be energy consumption or nominal power
     * @param attributeValue The correspondent energy consumed or nominal power value
     */
    public Program(String programName, String attributeName, double attributeValue) {
        this.programName = programName;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public double getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(double attributeValue) {
        this.attributeValue = attributeValue;
    }
}
