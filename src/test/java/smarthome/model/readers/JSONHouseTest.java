package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.model.*;
import smarthome.repository.HouseGridRepository;
import smarthome.repository.RoomRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static smarthome.model.House.*;

@RunWith(MockitoJUnitRunner.class)
class JSONHouseTest {
    Location loc = new Location(20, 20, 2);
    Address a1 = new Address("R. Dr. António Bernardino de Almeida", "431", "4200-072", "Porto", "Portugal", loc);
    OccupationArea oc = new OccupationArea(2, 5);
    GeographicalArea g1 = new GeographicalArea("PT", "Porto", "City", oc, loc);
    House house = House.getHouseInstance(a1, g1);

    @Mock
    private HouseGridRepository houseGridRepository;

    @Mock
    private RoomRepository roomRepository;

    private JSONHouse reader;

    private Room b107;
    private Room b109;

    @BeforeEach
    public void resetMySingleton() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field instance = House.class.getDeclaredField("theHouse");
        instance.setAccessible(true);
        instance.set(null, null);
        MockitoAnnotations.initMocks(this);
        reader = new JSONHouse(roomRepository,houseGridRepository);
        b107= new Room("B107", "Classroom", 1, 10.0, 6.0, 3.5);
        b109=new Room("B109", "Classroom", 1, 10.0, 6.0, 3.5);
    }

    @Test
    void loadAddressStreet() throws IOException, ParseException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        String expected = "R. Dr. António Bernardino de Almeida";
        String result = getAddress().getStreet();

        assertEquals(expected, result);
    }

    @Test
    void loadAddressNumber() throws IOException, ParseException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        String expected = "431";
        String result = getAddress().getNumber();

        assertEquals(expected, result);
    }

    @Test
    void loadAddressZip() throws IOException, ParseException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        String expected = "4200-072";
        String result = getAddress().getZipCode();

        assertEquals(expected, result);
    }

    @Test
    void loadAddressTown() throws IOException, ParseException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        String expected = "Porto";
        String result = getAddress().getTown();

        assertEquals(expected, result);
    }

    @Test
    void loadAddressCountry() throws IOException, ParseException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        String expected = "Portugal";
        String result = getAddress().getCountry();

        assertEquals(expected, result);
    }

    @Test
    void loadRooms() throws IOException, ParseException, ClassCastException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        int expected = 2;
        int result = getHouseRoomList().getRoomListSize();

        assertEquals(expected, result);

    }

   @Test
    void loadGrids1() throws IOException, ParseException, ClassCastException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);

        //List<HouseGrid> importGrids = reader.loadGrids(path);

        assertEquals(1, getGridListInHouse().getSize());
    }

    @Test
    void loadGrids2() throws IOException, ParseException, ClassCastException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);
        HouseGridList houseGridList = House.getGridListInHouse();

        //reader.loadGrids(path);

        assertEquals(1, houseGridList.getSize());
    }


    @Test
    void checkRoomListSizeInGrid() throws IOException, ParseException, ClassCastException {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        when(this.roomRepository.findById("B107")).thenReturn(java.util.Optional.of(b107));
        when(this.roomRepository.findById("B109")).thenReturn(java.util.Optional.of(b109));
        when(this.roomRepository.save(b107)).thenReturn(b107);
        when(this.roomRepository.save(b109)).thenReturn(b109);

        reader.importHouseConfiguration(path);
        //reader.loadGrids(path);

        int expected = 2;
        int result = getGridListInHouse().get(0).getRoomListInAGridSize();

        assertEquals(expected, result);
    }

    @Test
    void importHouseConfigurationWithoutRepositoryInjectionsThrowsNullPointerException() {
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseBasic.json");
        JSONHouse jsonHouse= new JSONHouse();
        Assertions.assertThrows(NullPointerException.class,()->jsonHouse.importHouseConfiguration(path));




    }

}
