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




    @Test
    void getReadingDateAndTime() {
        Calendar c = new GregorianCalendar();
        c.set(2019,0,1,12,34);
        ReadingDTO dto = new ReadingDTO(1.01, c);
        Calendar result = dto.getReadingDateAndTime();
        Calendar expected = new GregorianCalendar();
        expected.set(2019,0,1,12,34);
        assertEquals(expected,result);
    }
}