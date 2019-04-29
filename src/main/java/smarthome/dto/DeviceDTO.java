package smarthome.dto;

public class DeviceDTO {
    private String type;
    private String name;
    private double nominalPower;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNominalPower() {
        return nominalPower;
    }

    public void setNominalPower(double nominalPower) {
        this.nominalPower = nominalPower;
    }


}
