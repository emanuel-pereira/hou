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
        importAddress(path);
        //if changing info doesn't clear the previous lists please clear them here!
        importRooms(path);
        importGrids(path);
    }

    private void importAddress(Path path) throws IOException, ParseException {
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

    private void importRooms(Path path) throws IOException, ParseException {
        this.filePath = path;
        RoomList roomList = new RoomList();
        JSONArray jsonRooms = (JSONArray) this.readFile().get("room");

        for (Object room : jsonRooms) {
            JSONObject jsonRoom = (JSONObject) room;
            Room roomFromFile = loadRoom(jsonRoom);
            roomList.addRoom(roomFromFile);
        }
        getHouseRoomList().getRoomList().addAll(roomList.getRoomList());
    }

    private void importGrids(Path path) throws IOException, ParseException {
        this.filePath = path;
        List<HouseGrid> hgList = new ArrayList<>();
        JSONArray jsonGrids = (JSONArray) this.readFile().get("grid");

        for (Object grid : jsonGrids) {
            JSONObject jsonGrid = (JSONObject) grid;
            HouseGrid gridFromFile = loadGrid(jsonGrid);
            RoomList gridRoomList = gridFromFile.getRoomListInAGrid();
            loadRoomsInGrid(jsonGrid, gridRoomList);
            hgList.add(gridFromFile);
        }
        getGridListInHouse().getHouseGridList().addAll(hgList);
    }


    private static Room loadRoom(JSONObject jsonRoom) {
        String id = (String) jsonRoom.get("id");
        String description = (String) jsonRoom.get("description");
        String floorString = (String) jsonRoom.get("floor");
        int floor = Integer.parseInt(floorString);
        double width = (double) jsonRoom.get("width");
        double length = (double) jsonRoom.get("length");
        double height = (double) jsonRoom.get("height");
        return new Room(id, description, floor, width, length, height);
    }

    private static HouseGrid loadGrid(JSONObject jsonGrid) {
        String name = (String) jsonGrid.get("name");
        return new HouseGrid(name);
    }

    private void loadRoomsInGrid(JSONObject jsonGrid, RoomList roomsInGrid) {
        JSONArray jsonRoomsId = (JSONArray) jsonGrid.get("rooms");
        for (Object id : jsonRoomsId) {
            String roomId = (String) id;
            Room room = getHouseRoomList().getRoomById(roomId);
            roomsInGrid.addRoom(room);
        }

    }

}
