package com.Foro.Challenge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF para simplificar pruebas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/topico").permitAll() // Permitir acceso sin autenticación a /topico
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Usar autenticación básica
        return http.build();
    }
}
