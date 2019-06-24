package smarthome.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import smarthome.repository.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DbInitTest {

    @Mock
    private UserRepository userRepository;
    private DbInit dbInit;

    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        passwordEncoder= new BCryptPasswordEncoder();
        dbInit = new DbInit(userRepository, passwordEncoder);
    }

    @Test
    void run() throws Exception {
        when(this.userRepository.saveAll(Stream.of(
                new User("regular", passwordEncoder.encode("regular123"), "USER", ""),
                new User("admin", passwordEncoder.encode("admin123"), "ADMIN",
                        "ACCESS_TEST1,ACCESS_TEST2")).collect(Collectors.toList())))
                .thenReturn(Stream.of(new User("regular", passwordEncoder.encode("regular123"),
                        "USER", ""), new User("admin",
                        passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2")).collect(Collectors.toList()));
    dbInit.run();
    }

}