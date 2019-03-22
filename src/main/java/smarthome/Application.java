package smarthome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import smarthome.model.GeoRepository;
import smarthome.model.GeographicalArea;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(GeoRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new GeographicalArea("Z1", "Porto"));
        };
    }
}
