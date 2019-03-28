package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDTOTest {

    @Test
    void getReadingValue() {
        Calendar c = new GregorianCalendar();
        ReadingDTO dto = new ReadingDTO(1.01, c);
        double result = dto.getReadingValue();
        double expected = 1.01;
        assertEquals(expected,result);
    }
}