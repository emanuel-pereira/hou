package smarthome.dto;

import smarthome.model.TypeGA;
import smarthome.repository.Repositories;

public class TypeGADTO {

    private Long id;

    private String type;

    public TypeGADTO() {
    }

    public TypeGADTO(String newType) {
        this.type = newType;
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
