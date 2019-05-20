package smarthome.dto;

public class TypeGADTO {

    private Long id;

    private String type;

    public TypeGADTO(){
    }

    public TypeGADTO(String newType) {
        this.type = newType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
