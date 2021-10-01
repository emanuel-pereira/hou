package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericNameTest {

    @Test
    void getNameOfNullReturnsNull() {
        GenericName name= new GenericName();
        String result=name.getName();
        assertNull(result);
    }
}