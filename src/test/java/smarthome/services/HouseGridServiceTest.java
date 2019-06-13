package smarthome.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import smarthome.dto.HouseGridDTO;
import smarthome.model.HouseGrid;
import smarthome.repository.HouseGridRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class HouseGridServiceTest {

    @Mock
    private HouseGridRepository houseGridRepository;

    private HouseGridService houseGridService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.houseGridService = new HouseGridService(this.houseGridRepository);
    }


    @Test
    void findAll() {
        when(this.houseGridRepository.findAll()).thenReturn(Stream.of(new HouseGrid(1L, "B Bulding"),
                new HouseGrid(2L, "B Bulding")).collect(Collectors.toList()));
        assertEquals(2, houseGridService.findAll().size());
    }

    @Test
    void saveHouseGrid() throws Exception {
        HouseGrid houseGrid = new HouseGrid(2L, "B Bulding");
        HouseGridDTO dto = new HouseGridDTO(2L, "B Bulding");
        when(this.houseGridRepository.save(houseGrid)).thenReturn(houseGrid);
        assertEquals(dto, houseGridService.addNewGrid(dto));
    }

    @Test
    void saveHouseGridThrowsException() {
        HouseGridDTO dto = new HouseGridDTO();
        Assertions.assertThrows(InstantiationException.class, () -> houseGridService.addNewGrid(dto));
    }

    @Test
    void gridExists() {
        when(this.houseGridRepository.existsById(1L)).thenReturn(true);
        assertTrue(houseGridService.gridExists(1L));
    }

    @Test
    void gridDoesNotExist() {
        when(this.houseGridRepository.existsById(1L)).thenReturn(false);
        assertFalse(houseGridService.gridExists(1L));
    }

    @Test
    void getSize() {
        when(this.houseGridRepository.count()).thenReturn(2L);
        assertEquals(2, houseGridService.getSize());
    }

    @Test
    void findById() throws Exception {
        when(this.houseGridRepository.findById(1L)).thenReturn(java.util.Optional.of(new HouseGrid(1L, "B Building")));
        HouseGridDTO expected = new HouseGridDTO(1L, "B Building");
        HouseGridDTO result = houseGridService.findById(1L);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getDesignation(), result.getDesignation());
    }

    @Test
    void NotFoundById() {
        when(this.houseGridRepository.findById(1L)).thenReturn(java.util.Optional.of(new HouseGrid(1L, "B Building")));
        Assertions.assertThrows(NoSuchFieldException.class, () -> houseGridService.findById(2L));
    }

    @Test
    void NotFoundById2() {
        when(this.houseGridRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(NoSuchFieldException.class, () -> houseGridService.findById(1L));
    }
}