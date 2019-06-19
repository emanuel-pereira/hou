package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void setReadingValue() {
        Calendar c = new GregorianCalendar();
        ReadingDTO dto = new ReadingDTO(1.01, c);
        dto.setReadingValue(2.02);
        double result = dto.getReadingValue();
        double expected = 2.02;
        assertEquals(expected,result);
    }

    @Test
    void getReadingDateAndTime() {
        Calendar c = new GregorianCalendar();
        ReadingDTO dto = new ReadingDTO(1.01, c);
        Calendar result = dto.getReadingDateAndTime();
        Calendar expected = c;
        assertEquals(expected,result);
    }
    @Test
    void setReadingDateAndTime() {
        Calendar c = new GregorianCalendar();
        ReadingDTO dto = new ReadingDTO();
        dto.setReadingValue(2.02);
        Calendar newC = new GregorianCalendar(2019,Calendar.JUNE,19);
        dto.setReadingDateAndTime(newC);
        Calendar result = dto.getReadingDateAndTime();
        Calendar expected = newC;
        assertEquals(expected,result);
    }
}