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


    //TODO: create own method to check if type exists to replace findByType

    public TypeGA fromDTO() {
        return Repositories.getTypeGARepository().findByType(type);
    }
}
