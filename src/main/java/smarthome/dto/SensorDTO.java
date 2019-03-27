package smarthome.dto;

import java.util.List;

public class SensorDTO {

    private String id;
    private String designation;
    private List <ReadingDTO> readingListDTO;

    public SensorDTO(String id, String designation, List<ReadingDTO> readingListDTO) {
        this.id = id;
        this.designation = designation;
        this.readingListDTO=readingListDTO;
    }
    public SensorDTO(String id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public List<ReadingDTO> getReadingListDTO(){
        return readingListDTO;
    }
}
