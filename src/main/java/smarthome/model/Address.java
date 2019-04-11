package smarthome.model;

import java.util.Calendar;

public class Address {

    private String street;
    private String number;
    private String zipCode;
    private String town;
    private String country;
    private Location gpsLocation;


    /**
     * This constructor sets up the Address that will be used in the House
     *
     * @param streetName  Required street name
     * @param zipCode     Required zip code
     * @param town        Required town
     * @param gpsLocation Required GPS location
     */
    public Address(String streetName, String number, String zipCode, String town, String country, Location gpsLocation) {
        this.street = streetName;
        this.number = number;
        this.zipCode = zipCode;
        this.town = town;
        this.country = country;
        this.gpsLocation = gpsLocation;
    }


    public void setStreet(String streetName) {
        if (this.validateName(streetName)) {
            this.street = streetName;
        }
    }

    public void setNumber(String number) {
        if (this.validateNumber(number)) {
            this.number = number;
        }
    }

    public void setZipCode(String zipCode) {
        if (this.validateZipCode(zipCode)) {
            this.zipCode = zipCode;
        }
    }

    public void setTown(String town) {
        if (this.validateTown(town)) {
            this.town = town;
        }
    }

    public void setCountry(String country) {
        if (this.validateTown(country)) {
            this.country = country;
        }
    }

    /**
     * Validates the name of the street
     *
     * @param name The name of the street
     * @return False if nulls, empty spaces and texts that start with spaces
     */
    public boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return this.noStartWithSpaces(name);
    }

    /**
     * Get de street name
     *
     * @return Name of the street
     */
    public String getName() {
        return this.street;
    }


    public boolean validateNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            return false;
        }
        return this.noStartWithSpaces(number);
    }

    public String getNumber() {
        return number;
    }

    /**
     * Validates the number of the house. It accepts only numbers with no spaces
     *
     * @param town The town of the house
     * @return False if nulls, empty spaces and numbers that starts and ends with spaces
     */
    public boolean validateTown(String town) {
        if (town == null || town.trim().isEmpty()) {
            return false;
        }
        return this.noStartWithSpaces(town);
    }

    /**
     * Get house number
     *
     * @return Number of the house
     */
    public String getTown() {
        return this.town;
    }


    public boolean validateCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            return false;
        }
        return this.noStartWithSpaces(country);
    }

    public String getCountry() {
        return country;
    }

    /**
     * Validates the zip code of the house
     *
     * @param code The zip code of the house
     * @return False if nulls, empty spaces and if it starts with spaces
     */
    public boolean validateZipCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        return this.noStartWithSpaces(code);
    }


    public boolean noStartWithSpaces(String text) {
        return text.matches("^(?![\\s]).*");
    }

    /**
     * Get de zip code
     *
     * @return The zip code of the house
     */
    public String getZipCode() {
        return this.zipCode;
    }


    /**
     * sets the house address coordinates
     *
     * @param gpsLocation new Location object that will replace the previous one
     */
    public void setGpsLocation(Location gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    /**
     * Get GPS location
     *
     * @return The GPS location of the house
     */
    public Location getGPSLocation() {
        return this.gpsLocation;
    }

    /**
     * Method to turn the Address object into a string
     *
     * @return address information as a String
     */
//TODO: add tests

    public String addressToString() {
        StringBuilder output = new StringBuilder();
        String space = "    ";
        String line = "\n";
        String comma = ", ";

        output.append(space);
        output.append(this.street);
        output.append(comma);
        output.append(this.number);
        output.append(comma);
        output.append(this.zipCode);
        output.append(line + space);
        output.append(this.town);
        output.append(comma);
        output.append(this.country);
        output.append(line);
        output.append(this.gpsLocation.locationToString());

        return output.toString();

    }

}




