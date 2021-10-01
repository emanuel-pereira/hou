package smarthome.security;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import smarthome.repository.UserRepository;

class SecurityConfigurationTest {

    UserPrincipalDetailsService userPrincipalDetailsService;
    UserRepository userRepository;

    @Test
    public void passwordEncoderTest() {
        SecurityConfiguration configuration = new SecurityConfiguration(userPrincipalDetailsService, userRepository);
        PasswordEncoder passwordEncoder = configuration.passwordEncoder();
        Assert.assertNotNull(passwordEncoder);
    }
    @Test
    public void authenticationProviderTest() {
        SecurityConfiguration configuration = new SecurityConfiguration(userPrincipalDetailsService, userRepository);
        DaoAuthenticationProvider authenticationProvider = configuration.authenticationProvider();
        Assert.assertNotNull(authenticationProvider);
    }

    @Test
    void authenticationProvider() {
        SecurityConfiguration configuration = new SecurityConfiguration(userPrincipalDetailsService, userRepository);
        DaoAuthenticationProvider authenticationProvider = configuration.authenticationProvider();
        Assert.assertNotNull(authenticationProvider);
    }

    @Test
    void passwordEncoder() {
    }

    @Test
    void corsConfigurationSource() {
    }
}