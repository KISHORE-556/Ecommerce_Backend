package dev.kishore.authservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection for non-browser clients (optional)
                .authorizeHttpRequests((requests) ->   {
                    try {
                        requests
                                .anyRequest().permitAll();


                    }catch(Exception e){
                        throw new RuntimeException(e);
                    }

                        });

        return http.build();
    }




}
