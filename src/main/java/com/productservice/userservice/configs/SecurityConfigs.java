package com.productservice.userservice.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfigs {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (useful for testing; enable in production)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/**").permitAll()  // Public endpoints without authentication
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .httpBasic(withDefaults());  // Enable basic authentication

        return http.build();
    }

}
