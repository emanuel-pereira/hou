package smarthome.dto;

public class GridDTO {

    private Long id;

    private double contractedMaximumPower = Double.NaN;
    private String designation;

    public GridDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getContractedMaximumPower() {
        return contractedMaximumPower;
    }

    public void setContractedMaximumPower(double contractedMaximumPower) {
        this.contractedMaximumPower = contractedMaximumPower;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
