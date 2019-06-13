package smarthome.dto;

public class HouseGridDTO {

    private Long id;

    private String designation;

    public HouseGridDTO() {
    }

    public HouseGridDTO(Long id, String designation) {
        this.setId(id);
        this.setDesignation(designation);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
