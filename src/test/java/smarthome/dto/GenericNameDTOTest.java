package smarthome.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericNameDTOTest {

    @Test
    void getName() {
        GenericNameDTO dto = new GenericNameDTO("name");

        String field = "new name";
        dto.setName(field);

        String result = dto.getName();

        assertEquals(field,result);
    }

    @Test
    void getNameEmptyConstructor() {
        GenericNameDTO dto = new GenericNameDTO();

        String field = "i am a name";
        dto.setName(field);

        String result = dto.getName();

        assertEquals(field,result);

    }
}