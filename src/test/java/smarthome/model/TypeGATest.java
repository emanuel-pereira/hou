package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeGATest {


    @Test
    public void equalsIfPersonEqualsTypeGA() {
        String person = "Ricardo";
        TypeGA typeGA = new TypeGA("Cidade");
        boolean result;

        result = typeGA.equals(person);

        assertFalse(result);
    }

    @Test
    public void equalsIfTypeGAEqualsTypeGA() {
        boolean result;
        TypeGA typeGA1 = new TypeGA("Vila");
        TypeGA typeGA2 = new TypeGA("Vila");

        result = typeGA1.equals(typeGA2);

        assertEquals(typeGA1.hashCode(), typeGA2.hashCode());
        assertTrue(result);
    }

    @Test
    public void equalsIfTypeGAEqualsDifferentTypeGA() {
        boolean result;
        TypeGA typeGA1 = new TypeGA("Cidade");
        TypeGA typeGA2 = new TypeGA("Floresta");

        result = typeGA1.equals(typeGA2);

        assertNotEquals(typeGA1.hashCode(), typeGA2.hashCode());
        assertFalse(result);
    }

    @Test
    public void equalsIfTypeGAEqualsSameTypeGA() {
        boolean result;

        TypeGA typeGA = new TypeGA("País");
        result = typeGA.equals(typeGA);

        assertTrue(result);
    }

    @Test
    public void stringToString() {
        String expected = "city";

        TypeGA typeGA = new TypeGA("City");
        String result;
        result = typeGA.toString();

        assertEquals(expected, result);
    }

    @Test
    public void getType() {
        TypeGA typeGA = new TypeGA();

        assertNull(typeGA.toString());
    }
}