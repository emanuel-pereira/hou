package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {


    @Test
    @DisplayName("define and check house address, zip code, town, location and geographical area")
    void checkHouseLocation() {
        //Arrange
        Location l1 = new Location (41, 12.3, 110);
        Address a1 = new Address ("Rua Dinis Júlio","123", "222-767", "Lisboa","Espanha", l1);


        //Act
        a1.setStreet ("Rua Júlio Dinis");
        a1.setNumber ("456");
        a1.setZipCode ("3380-45");
        a1.setTown ("Porto");
        a1.setCountry("Portugal");


        String result1 = a1.getStreet();
        String result0 = a1.getNumber();
        String result2 = a1.getZipCode();
        String result3 = a1.getTown();
        String result7 = a1.getCountry();
        double result4 = l1.getLatitude();
        double result5 = l1.getLongitude();
        double result6 = l1.getAltitude();


        //Assert
        assertEquals ("Rua Júlio Dinis", result1);
        assertEquals ("456", result0);
        assertEquals ("3380-45", result2);
        assertEquals ("Porto", result3);
        assertEquals ("Portugal", result7);
        assertEquals (41, result4);
        assertEquals (12.3, result5);
        assertEquals (110, result6);
    }

    /**
     * Validate street name if correctly written and return true
     */
    @Test
    void validateNameIfCorrect() {

        Location location1 = new Location(23, 67, 89);
        String streetName = "Rua António Nobre";
        Address address1 = new Address (streetName,"123", "333-568", "Porto","Portugal", location1);

        boolean result = address1.validateName(streetName);

        assertTrue(result);
        assertEquals(streetName, address1.getStreet());
    }

    /**
     * Don't validate street name if empty and return false
     */
    @Test
    void validateNameIfEmpty() {

        Location location1 = new Location(23, 67, 89);
        String streetName = " ";
        Address address1 = new Address (streetName,"123", "333-568", "Lisboa","Portugal", location1);

        boolean result = address1.validateName(streetName);

        assertFalse(result);
    }

    /**
     * Don't validate street name if null
     */
    @Test
    void validateNameIfFalse() {

        Location location1 = new Location(23, 67, 89);
        String streetName = null;
        Address address1 = new Address (streetName,"123", "333-568", "Lisboa","Portugal", location1);

        boolean result = address1.validateName(streetName);

        assertFalse(result);
    }

    /**
     * Don't validate street name if starts with space and return false
     */
    @Test
    void validateNameIfIncorrect() {

        Location location1 = new Location(23, 67, 89);
        String streetName = " Rua Pacheco, 2";
        Address address1 = new Address (streetName,"123", "333-568", "Porto","Portugal", location1);

        boolean result = address1.validateName(streetName);

        assertFalse(result);
    }

    /**
     * Validate town if correctly written and return true
     */
    @Test
    void validateTownIfCorrect() {

        Location location1 = new Location(23, 67, 89);
        String town = "Porto";
        Address address1 = new Address ("Rua Julio Dinis","56", "3368-786", town,"Portugal", location1);

        boolean result = address1.validateTown(town);

        assertTrue(result);
        assertEquals(town, address1.getTown());
    }

    /**
     * Don't validate town if null and return false
     */
    @Test
    void validateTownIfFalse() {

        Location location1 = new Location(23, 67, 89);
        String town = null;
        Address address1 = new Address ("Rua Pacheco da Cunha","123", "333-568", town,"Portugal", location1);

        boolean result = address1.validateTown(town);

        assertFalse(result);
    }

    /**
     * Don't validate town if empty and return false
     */
    @Test
    void validateTownIfEmpty() {

        Location location1 = new Location(23, 67, 89);
        String town = "       ";
        Address address1 = new Address ("Rua Pacheco da Cunha","123", "333-568", town,"Portugal", location1);

        boolean result = address1.validateTown(town);

        assertFalse(result);
    }

    /**
     * Don't validate town if starts with space and return false
     */
    @Test
    void validateTownIfStartsSpace() {

        Location location1 = new Location(23, 67, 89);
        String town = " Lisboa";
        Address address1 = new Address ("Rua Pacheco da Cunha","67", "333-568", town,"Portugal", location1);

        boolean result = address1.validateTown(town);

        assertFalse(result);
    }

    /**
     * Validate zip code if correctly written and return true
     */
    @Test
    void validateZipCodeIfCorrect() {

        Location location1 = new Location(23, 67, 89);
        String code = "3370-767";
        Address address1 = new Address ("Twelve Street","123", code, "Porto","Portugal", location1);

        boolean result = address1.validateZipCode(code);

        assertTrue(result);
        assertEquals(code, address1.getZipCode());
    }

    /**
     * Don't validate zip code if empty and return false
     */
    @Test
    void validateZipCodeIfEmpty() {

        Location location1 = new Location(23, 67, 89);
        String code = "  ";
        Address address1 = new Address ("Newport Junction","123", code, "Lisboa","Portugal", location1);

        boolean result = address1.validateZipCode(code);

        assertFalse(result);
    }

    /**
     * Don't validate zip code if null and return false
     */
    @Test
    void validateZipCodeIfFalse() {

        Location location1 = new Location(23, 67, 89);
        String code = null;
        Address address1 = new Address ("Newport Junction","123", code, "Lisboa","Portugal", location1);

        boolean result = address1.validateZipCode(code);

        assertFalse(result);
    }

    /**
     * Don't validate zip code if starts with space and return false
     */
    @Test
    void validateZipCodeIfIncorrect() {

        Location location1 = new Location(23, 67, 89);
        String code = " LW 3343";
        Address address1 = new Address ("Madison Avenue","123", code, "Vila Nova de Gaia","Portugal", location1);

        boolean result = address1.validateZipCode(code);

        assertFalse(result);
    }

    /**
     * Validate country set if starts with space and return false
     */
    @Test
    void validateCountryCorrect() {

        Location location1 = new Location(23, 67, 89);
        String country = "Portugal";
        Address address1 = new Address ("Madison Avenue","123", " LW 3343", "Vila Nova de Gaia",country, location1);

        boolean result = address1.validateCountry(country);

        assertTrue(result);
    }

    /**
     * Don't validate country if starts with space and return false
     */
    @Test
    void validateCountryIncorrect() {

        Location location1 = new Location(23, 67, 89);
        String country = " Portugal";
        Address address1 = new Address ("Madison Avenue","123", " LW 3343", "Vila Nova de Gaia",country, location1);

        boolean result = address1.validateCountry(country);

        assertFalse(result);
    }

    /**
     * Don't validate country if null and return false
     */
    @Test
    void validateCountryNull() {
        Location location1 = new Location(23, 67, 89);
        String country = null;
        Address address1 = new Address ("Madison Avenue","123", " LW 3343", "Vila Nova de Gaia","Espanha", location1);

        address1.setCountry(country);
        String result = address1.getCountry();
        assertEquals("Espanha",result);
        boolean result2 = address1.validateCountry(country);
        assertFalse(result2);
    }

    /**
     * Don't validate number if null and return false
     */
    @Test
    void validateNumberNull() {
        Location location1 = new Location(23, 67, 89);
        String number = null;
        Address address1 = new Address ("Madison Avenue","123", " LW 3343", "Vila Nova de Gaia","Espanha", location1);

        address1.setNumber(number);
        String result = address1.getNumber();
        assertEquals("123",result);
        boolean result2 = address1.validateCountry(number);
        assertFalse(result2);
    }

    /**
     * Check the GPS location
     */
    @Test
    void gpsLocation() {

        Location location1 = new Location (23, 67, 89);
        Address address1 = new Address ("Twelve Street","123", "3370-767", "Lisboa","Portugal", location1);

        Location result = address1.getGPSLocation();

        assertEquals(location1, result);
    }

    /**
     * Don't validate street name, zip code and town set method if empty
     */
    @Test
    @DisplayName("define and check house address, zip code, town, location and geographical area")
    void checkIncorrectSetStreetName() {
        //Arrange
        Location l1 = new Location(41, 12.3, 110);
        Location l2 = new Location(12,34,56);
        Address a1 = new Address("Rua Júlio Dinis","345", "3380-45", "Porto","Portugal", l1);

        //Act
        a1.setStreet(" ");
        a1.setNumber(" ");
        a1.setZipCode(" ");
        a1.setTown(" ");
        a1.setCountry(" ");
        a1.setGpsLocation(l2);

        String result0 = a1.getStreet();
        String result1 = a1.getNumber();
        String result2 = a1.getZipCode();
        String result3 = a1.getTown();
        String result7 = a1.getCountry();
        double result4 = l2.getLatitude();
        double result5 = l2.getLongitude();
        double result6 = l2.getAltitude();

        //Assert
        assertEquals("Rua Júlio Dinis", result0);
        assertEquals("345",result1);
        assertEquals("3380-45", result2);
        assertEquals("Porto", result3);
        assertEquals("Portugal",result7);
        assertEquals(12, result4);
        assertEquals(34, result5);
        assertEquals(56, result6);
    }




    /**
     * Validate street name if correctly written and return true using another constructor
     */
    @Test
    void otherConstructorValidateNameIfCorrect() {

        String streetName = "Rua António Nobre";
        Location houseLocation = new Location(23, 67, 89);
        Address address1 = new Address(streetName, "15", "4430-250", "Porto","Portugal",houseLocation);

        boolean result = address1.validateName(streetName);

        assertTrue(result);
        assertEquals(streetName, address1.getStreet());
    }

    /**
     * Validate town if correctly written and return true using another constructor
     */
    @Test
    void otherConstructorValidateTownIfCorrect() {

        String town = "Porto";
        Location houseLocation = new Location(12, 22, 189);
        Address address1 = new Address("Rua Julio Dinis", "15", "4430-250", town,"Portugal",houseLocation);

        boolean result = address1.validateTown(town);

        assertTrue(result);
        assertEquals(town, address1.getTown());
    }


    /**
     * Validate zip code if correctly written and return true using another constructor
     */
    @Test
    void otherConstructorValidateZipCodeIfCorrect() {

        String code = "3370-767";
        Location houseLocation = new Location(23, 67, 89);
        Address address1 = new Address ("Twelve Street","123", code, "Aveiro", "Portugal",houseLocation);

        boolean result = address1.validateZipCode(code);

        assertTrue(result);
        assertEquals(code, address1.getZipCode());
    }

    /**
     * Don't validate street name if incorrectly written and return false using another constructor
     */
    @Test
    void otherConstructorValidateNameIfIncorrect() {

        String streetName = " ";
        Location location = new Location(23, 67, 89);
        Address address1 = new Address (streetName, "123","333-568", "Porto", "Portugal",location);

        boolean result = address1.validateName(streetName);

        assertFalse(result);
        assertEquals(streetName, address1.getStreet());
    }

    /**
     * Don't validate town if incorrectly written and return false using another constructor
     */
    @Test
    void otherConstructorValidateTownIfIncorrect() {

        String town = " ";
        Location location = new Location(23, 67, 89);
        Address address1 = new Address ("Rua Julio Dinis", "123","333-568", town, "Portugal", location);

        boolean result = address1.validateTown(town);

        assertFalse(result);
        assertEquals(town, address1.getTown());
    }


    /**
     * Don't validate zip code if incorrectly written and return false using another constructor
     */
    @Test
    void otherConstructorValidateZipCodeIfIncorrect() {

        String code = " 3370-767";
        Location location = new Location(23, 67, 89);
        Address address1 = new Address ("Twelve Street","123", code, "Aveiro", "Portugal", location);

        boolean result = address1.validateZipCode(code);

        assertFalse(result);
        assertEquals(code, address1.getZipCode());
    }

    @Test
    public void addressToString() {

        Location location = new Location(23, 67, 89);

        String expected = "    Rua Júlio Dinis, 80, 4200-120\n    Porto, Portugal\n | Location:\n    Latitude: 23.0º | Longitude: 67.0º | Altitude: 89.0 meters";

        Address a = new Address("Rua Júlio Dinis", "80", "4200-120", "Porto", "Portugal", location);
        String result;
        result = a.addressToString();

        assertEquals(expected, result);
    }

}