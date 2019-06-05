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
import smarthome.repository.UserRepository;

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
    protected void configure(HttpSecurity http) throws Exception {
        http
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
                .antMatchers("/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/rooms").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/externalSensors").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/geographicalAreas").hasRole("USER")
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
}