package smarthome.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import smarthome.model.GeographicalArea;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class RepositoryTest {

    //Example test
    @Test
    void test() {
        List<GeographicalArea> city = Repositories.geoRepository.findByDesignation("city");
        assertEquals(0, city.size());
    }
}