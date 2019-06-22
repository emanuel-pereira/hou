package smarthome.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.userdetails.UserDetails;
import smarthome.model.User;
import smarthome.repository.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserPrincipalDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserPrincipalDetailsService service;

    private User user;
    private User regularUser;



    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.service= new UserPrincipalDetailsService(userRepository);
        user= new User("username", "u$3rPa55", "admin", "all");
        regularUser= new User("regularUSer", "+an.ThErpa55w0rd", "RegularUser", "regular");

    }

    @Test
    void loadUserByUsernameAndGetPassword() {
        when(this.userRepository.findByUsername("username")).thenReturn(user);
        String expected="u$3rPa55" ;
        String result=service.loadUserByUsername("username").getPassword();
        assertEquals(expected,result);
    }

    @Test
    void loadUserByUsernameAndGetUsername() {
        when(this.userRepository.findByUsername("regularUSer")).thenReturn(regularUser);
        String expected="regularUSer" ;
        String result=service.loadUserByUsername("regularUSer").getUsername();
        assertEquals(expected,result);
    }

    @Test
    void loadUserByUsernameAndCheckIfEnabled() {
        when(this.userRepository.findByUsername("regularUSer")).thenReturn(regularUser);
        assertTrue(service.loadUserByUsername("regularUSer").isEnabled());
    }

    @Test
    void loadUserByUsernameAndCheckIfAccountNonExpired() {
        when(this.userRepository.findByUsername("regularUSer")).thenReturn(regularUser);
        assertTrue(service.loadUserByUsername("regularUSer").isAccountNonExpired());
    }

    @Test
    void loadUserByUsernameAndCheckIfAccountNonLocked() {
        when(this.userRepository.findByUsername("username")).thenReturn(regularUser);
        assertTrue(service.loadUserByUsername("username").isAccountNonLocked());
    }

    @Test
    void loadUserByUsernameAndCheckIfCredentialsNonExpired() {
        when(this.userRepository.findByUsername("username")).thenReturn(regularUser);
        assertTrue(service.loadUserByUsername("username").isCredentialsNonExpired());
    }

    @Test
    void loadUserByUsernameAndCheckIfgetAuthoritiesReturnsSizeOf2OneForPermissionsAndOtherForRole() {
        when(this.userRepository.findByUsername("username")).thenReturn(regularUser);
        int result=service.loadUserByUsername("username").getAuthorities().size();
        assertEquals(2,result);

    }
}