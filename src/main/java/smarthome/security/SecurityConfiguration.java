package smarthome.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import smarthome.repository.UserRepository;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserRepository userRepository;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "http://localhost:3002","http://localhost:8080","http://localhost:8081","http://localhost:8082"}, maxAge = 3600)
    protected void configure(HttpSecurity http) throws Exception {
        http
                // by default uses a Bean by the name of corsConfigurationSource
                .cors().and()
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/rooms").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/externalSensors").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/geographicalAreas").hasRole("USER")
                .antMatchers("/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
    }


    /* Available links from root, used for reference AA
    {
    "_links": {
        "rooms": {
            "href": "http://localhost:8080/rooms"
        },
        "externalSensors": {
            "href": "http://localhost:8080/externalSensors"
        },
        "sensorTypes": {
            "href": "http://localhost:8080/sensorTypes"
        },
        "geographicalAreas": {
            "href": "http://localhost:8080/geographicalAreas"
        },
        "users": {
            "href": "http://localhost:8080/users"
        },
        "internalSensors": {
            "href": "http://localhost:8080/internalSensors"
        },
        "typeGAs": {
            "href": "http://localhost:8080/typeGAs"
        },
        "houseGrids": {
            "href": "http://localhost:8080/houseGrids"
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}
     */



    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001", "http://localhost:3002","http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}