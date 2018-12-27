package smarthome.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {

    /**
     * Validate street name if correctly written and return true
     */
    @Test
    void validateNameIfCorrect() {

        Location location1 = new Location (23, 67, 89);
        String streetName = "Rua António Nobre";
        Address address1 = new Address (streetName, 2, "", "", "333-568", "Paranhos", location1);

        boolean result = address1.validateName (streetName);

        assertEquals (true, result);
        assertEquals ( streetName,address1.getName ());
    }

    /**
     * Don't validate street name if empty and return false
     */
    @Test
    void validateNameIfEmpty() {

        Location location1 = new Location (23, 67, 89);
        String streetName = " ";
        Address address1 = new Address (streetName, 2, "", "", "333-568", "Paranhos", location1);

        boolean result = address1.validateName (streetName);

        assertEquals (false, result);
    }

    /**
     * Don't validate street name if starts with space and return false
     */
    @Test
    void validateNameIfIncorrect() {

        Location location1 = new Location (23, 67, 89);
        String streetName = " Rua Pacheco";
        Address address1 = new Address (streetName, 2, "", "", "333-568", "Paranhos", location1);

        boolean result = address1.validateName (streetName);

        assertEquals (false, result);
    }

    /**
     * Validate house number if correctly written and return true
     */
    @Test
    void validateHouseNumberIfCorrect() {

        Location location1 = new Location (23, 67, 89);
        Integer number = 2;
        Address address1 = new Address ("Rua Julio Dinis", number, "", "", "333-568", "Paranhos", location1);

        boolean result = address1.validateNumber (number);

        assertEquals (true, result);
        assertEquals (number, address1.getHouseNumber ());
    }

    /**
     * Don't validate house number if empty and return false
     */
    @Test
    void validateHouseNumberEmpty() {

        Location location1 = new Location (23, 67, 89);
        Integer number = null;
        Address address1 = new Address ("Rua Pacheco da Cunha", number, "", "", "333-568", "Paranhos", location1);

        boolean result = address1.validateNumber (number);

        assertEquals (false, result);
    }


    /**
     * Validate county if correctly written and return true
     */
    @Test
    void validateCountyIfCorrect() {

        Location location1 = new Location (23, 67, 89);
        String county = "Florida";
        Address address1 = new Address ("Twelve Street", 2, "", "", "FL 44456", county, location1);

        boolean result = address1.validateCounty (county);

        assertEquals (true, result);
        assertEquals ( county,address1.getCounty ());
    }

    /**
     * Don't validate county if empty and return false
     */
    @Test
    void validateCountyIfEmpty() {

        Location location1 = new Location (23, 67, 89);
        String county = "";
        Address address1 = new Address ("Newport Junction", 2, "", "", "4456", county, location1);

        boolean result = address1.validateCounty (county);

        assertEquals (false, result);
    }

    /**
     * Don't validate county if starts with space and return false
     */
    @Test
    void validateCountyIfIncorrect() {

        Location location1 = new Location (23, 67, 89);
        String county = "  Michigan";
        Address address1 = new Address ("Madison Avenue", 2, "", "", "MIC 5646", county, location1);

        boolean result = address1.validateCounty (county);

        assertEquals (false, result);
    }

    /**
     * Validate zip code if correctly written and return true
     */
    @Test
    void validateZipCodeIfCorrect() {

        Location location1 = new Location (23, 67, 89);
        String code = "3370-767";
        Address address1 = new Address ("Twelve Street", 2, "", "", code, "Paranhos", location1);

        boolean result = address1.validateZipCode (code);

        assertEquals (true, result);
        assertEquals ( code,address1.getZipCode ());
    }

    /**
     * Don't validate zip code if empty and return false
     */
    @Test
    void validateZipCodeIfEmpty() {

        Location location1 = new Location (23, 67, 89);
        String code = "  ";
        Address address1 = new Address ("Newport Junction", 2, "", "", code, "Paranhos", location1);

        boolean result = address1.validateZipCode (code);

        assertEquals (false, result);
    }

    /**
     * Don't validate zip code if starts with space and return false
     */
    @Test
    void validateZipCodeIfIncorrect() {

        Location location1 = new Location (23, 67, 89);
        String code = " LW 3343";
        Address address1 = new Address ("Madison Avenue", 2, "", "", code, "Paranhos", location1);

        boolean result = address1.validateZipCode (code);

        assertEquals (false, result);
    }

    /**
     * Check the GPS location
     */
    @Test
    void gpsLocation() {

        Location location1 = new Location (23, 67, 89);
        Address address1 = new Address ("Twelve Street", 2, "", "", "3370-767", "Paranhos", location1);

        Location result = address1.getGPSLocation ();

        assertEquals (location1, result);
    }
}