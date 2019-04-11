package smarthome.controller;

import org.json.simple.parser.ParseException;
import smarthome.dto.GeographicalAreaDTO;
import smarthome.mapper.GeographicalAreaMapper;
import smarthome.model.Address;
import smarthome.model.GAList;
import smarthome.model.GeographicalArea;
import smarthome.model.Location;
import smarthome.model.readers.DataImport;

import java.io.IOException;
import java.util.List;

import static smarthome.model.House.*;


public class ConfigureHouseCTRL {

    private DataImport dataImportHouse;
    private GeographicalAreaMapper gaMapper = new GeographicalAreaMapper();
    private GAList gaList;


    public ConfigureHouseCTRL(GAList listOfGA) {
        gaList = listOfGA;
        this.dataImportHouse = new DataImport(this.gaList);
    }

    public List<GeographicalAreaDTO> getGAListDTO() {
        return gaMapper.toDtoList(gaList);
    }

    public String showGAListDTO() {
        List<GeographicalAreaDTO> list = getGAListDTO();
        StringBuilder result = new StringBuilder();
        String element = " - ";
        int number = 1;
        for (GeographicalAreaDTO ga : list) {
            result.append(number++);
            result.append(element);
            result.append(ga.getIdentification()+", " +ga.getDesignation()+";");
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * @return the number of elements in the geographical areas list as an integer value
     */

    public int getGAListSize() {
        return this.gaList.size();
    }


    //TODO: tests
    public String getIdFromIndex (int index){
        GeographicalArea geoArea = this.gaList.get(index-1);
        return geoArea.getId();
    }

    //TODO decrease number of method parameters to less than 7
    public boolean configureHouseLocation(String idGeoArea, String streetName, String number, String zipCode, String town, String country,  double latitude, double longitude, double altitude) {
        GeographicalArea ga = this.gaList.getById(idGeoArea);
        setHouseGA(ga);
        Location location = new Location(latitude,longitude,altitude);
        Address houseAddress = new Address(streetName, number, zipCode, town, country,location);
        setHouseAddress(houseAddress);
        return getAddress() != null;

    }

    /**Methods for US100 - Configure House From File*/

   public void configureHouseFromFileCTRL (String idGeoArea,double latitude,double longitude,double altitude) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, ParseException {
        GeographicalArea geoArea = this.gaList.getById(idGeoArea);
        setHouseGA(geoArea);

        this.dataImportHouse.importHouse();

        Address houseAddress = getAddress();
        Location location = new Location(latitude,longitude,altitude);
        houseAddress.setGpsLocation(location);
        setHouseAddress(houseAddress);
    }

    public String showAddressInString(){
       return getAddress().addressToString();
    }

    public int getRoomListSizeCTRL(){
       return getHouseRoomList().getRoomListSize();
    }

    public int getGridListSizeCTRL(){
        return getGridListInHouse().getSize();
    }




}
