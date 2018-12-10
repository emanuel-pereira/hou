package sprintzero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeGATest {

    //Testar Equals

    @Test
        public void equalsIfPersonEqualsTypeGA() {
        String person = "Ricardo";
        TypeGA typeGA = new TypeGA ("Cidade");
        boolean result;

        result = typeGA.equals (person);

        assertEquals (false, result);
    }

    @Test
    public void equalsIfTypeGAEqualsTypeGA() {

        TypeGA typeGA1 = new TypeGA ("Vila");
        TypeGA typeGA2 = new TypeGA ("Vila");

        boolean result;

        result = typeGA1.equals (typeGA2);

        assertEquals (typeGA1.hashCode (), typeGA2.hashCode ());
        assertEquals (true, result);
    }

    @Test
    public void equalsIfTypeGAEqualsDifferentTypeGA() {

        TypeGA typeGA1 = new TypeGA ("Cidade");
        TypeGA typeGA2 = new TypeGA ("Floresta");

        boolean result;

        result = typeGA1.equals (typeGA2);

        assertEquals (false, result);
    }

    @Test
    public void equalsIfTypeGAEqualsSameTypeGA() {

        TypeGA typeGA = new TypeGA ("País");

        boolean result;

        result = typeGA.equals (typeGA);

        assertEquals (true, result);
    }

}