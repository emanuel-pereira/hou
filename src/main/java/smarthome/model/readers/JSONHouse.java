package smarthome.model.readers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import smarthome.model.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static smarthome.model.House.*;

public class JSONHouse implements FileReaderHouse {
    private Path filePath;
    private JSONParser parser = new JSONParser();

    public JSONHouse() {
        /* empty constructor to allow reflection */
    }

    private JSONObject readFile() throws IOException, ParseException {
        java.io.FileReader fileReader = new java.io.FileReader(filePath.toAbsolutePath().toFile());
        return (JSONObject) this.parser.parse(fileReader);
    }

    public void importHouseConfiguration(Path path) throws IOException, ParseException {
        this.filePath = path;
        loadAddress(path);
        //if changing info doesn't clear the previous lists please clear them here!
        loadRooms(path);
        loadGrids(path);
    }

    public void loadAddress(Path path) throws IOException, ParseException {
        this.filePath = path;
        JSONObject jsonAddress = (JSONObject) this.readFile().get("adress");
        String street = (String) jsonAddress.get("street");
        String number = (String) jsonAddress.get("number");
        String zip = (String) jsonAddress.get("zip");
        String town = (String) jsonAddress.get("town");
        String country = (String) jsonAddress.get("country");
        Location location = new Location();
        Address houseAddress = new Address(street, number, zip, town, country, location);
        setHouseAddress(houseAddress);
    }

    public void loadRooms(Path path) throws IOException, ParseException {
        this.filePath = path;
        RoomList roomList = new RoomList();
        JSONArray jsonRooms = (JSONArray) this.readFile().get("room");

        for (Object room : jsonRooms) {
            JSONObject jsonRoom = (JSONObject) room;
            Room roomFromFile = importRoom(jsonRoom);
            roomList.addRoom(roomFromFile);
        }
        getHouseRoomList().getRoomList().addAll(roomList.getRoomList());
    }

    public void loadGrids(Path path) throws IOException, ParseException {
        this.filePath = path;
        List<HouseGrid> hgList = new ArrayList<>();
        JSONArray jsonGrids = (JSONArray) this.readFile().get("grid");

        for (Object grid : jsonGrids) {
            JSONObject jsonGrid = (JSONObject) grid;
            HouseGrid gridFromFile = importGrid(jsonGrid);
            RoomList gridRoomList = gridFromFile.getRoomListInAGrid();
            importRoomsInGrid(jsonGrid, gridRoomList);
            hgList.add(gridFromFile);
        }
        getHGListInHouse().getHouseGridList().addAll(hgList);
    }


    private static Room importRoom(JSONObject jsonRoom) {
        String id = (String) jsonRoom.get("id");
        String description = (String) jsonRoom.get("description");
        String floorString = (String) jsonRoom.get("floor");
        int floor = Integer.parseInt(floorString);
        double width = (double) jsonRoom.get("width");
        double length = (double) jsonRoom.get("length");
        double height = (double) jsonRoom.get("height");
        return new Room(id, description, floor, width, length, height);
    }

    private static HouseGrid importGrid(JSONObject jsonGrid) {
        String name = (String) jsonGrid.get("name");
        return new HouseGrid(name);
    }

    public void importRoomsInGrid(JSONObject jsonGrid, RoomList roomsInGrid) {
        JSONArray jsonRoomsId = (JSONArray) jsonGrid.get("rooms");
        for (Object id : jsonRoomsId) {
            String roomId = (String) id;
            Room room = getHouseRoomList().getRoomById(roomId);
            roomsInGrid.addRoom(room);
        }

    }

}
