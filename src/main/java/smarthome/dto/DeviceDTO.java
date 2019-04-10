package smarthome.dto;

public class DeviceDTO {
    private String type;
    private String name;
    private String active;

    public DeviceDTO(String type, String name, String active) {
        this.type = type;
        this.name = name;
        this.active = active;
    }

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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
