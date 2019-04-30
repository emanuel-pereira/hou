package smarthome.dto;

import org.junit.jupiter.api.Test;
import smarthome.model.OccupationArea;

import static org.junit.jupiter.api.Assertions.*;

class OccupationAreaDTOTest {

    @Test
    void fromDTO() {
        OccupationAreaDTO oA = new OccupationAreaDTO(5,10);

        OccupationArea result = oA.fromDTO();

        assertEquals(oA.getLength(),result.getLength());
        assertEquals(oA.getWidth(),result.getWidth());
    }
}