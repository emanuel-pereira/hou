package smarthome.dto;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ExternalReadingDTOTest {

    @Test
    void getValue() {
        ExternalReadingDTO readingDTO = new ExternalReadingDTO();
        double value = 123.456;
        readingDTO.setReadingValue(value);

        double result = readingDTO.getReadingValue();
        assertEquals(value,result);
    }

    @Test
    void getDateAndTime() {
        ExternalReadingDTO readingDTO = new ExternalReadingDTO(123,new GregorianCalendar(2019,Calendar.MARCH,1));
        GregorianCalendar calendar = new GregorianCalendar(2019,Calendar.MAY,28);
        readingDTO.setDateAndTime(calendar);

        Calendar result = readingDTO.getDateAndTime();
        assertEquals(calendar,result);
    }
}