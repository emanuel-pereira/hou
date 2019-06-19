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
                //configure access rules
                .antMatchers("/h2-geo/**").permitAll()

                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/**").permitAll()

                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
        http.headers().frameOptions().sameOrigin();
    }


    /* Available links from root, used for reference AA
    {
    "_links": {
        "rooms": {
            "href": "https://localhost:8443/rooms"
        },
        "externalSensors": {
            "href": "https://localhost:8443/externalSensors"
        },
        "sensorTypes": {
            "href": "https://localhost:8443/sensorTypes"
        },
        "geographicalAreas": {
            "href": "https://localhost:8443/geographicalAreas"
        },
        "users": {
            "href": "https://localhost:8443/users"
        },
        "internalSensors": {
            "href": "https://localhost:8443/internalSensors"
        },
        "typeGAs": {
            "href": "https://localhost:8443/typeGAs"
        },
        "houseGrids": {
            "href": "https://localhost:8443/houseGrids"
        },
        "profile": {
            "href": "https://localhost:8443/profile"
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
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001", "http://localhost:3002","http://localhost:8080","http://localhost:8082"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}