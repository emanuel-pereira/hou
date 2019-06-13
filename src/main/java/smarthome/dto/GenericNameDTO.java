package smarthome.dto;


public class GenericNameDTO {

    private String name;

    public GenericNameDTO(){}

    public GenericNameDTO (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
