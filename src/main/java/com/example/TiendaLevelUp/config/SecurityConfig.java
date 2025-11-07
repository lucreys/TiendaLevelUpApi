package com.example.TiendaLevelUp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define el Codificador de Contraseñas (PasswordEncoder)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configura las Reglas de Seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita CSRF (típico en APIs REST sin cookies)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Permite acceso libre a los endpoints de autenticación
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                        // Permite acceso libre a los endpoints de productos para GET
                        .requestMatchers(new AntPathRequestMatcher("/api/products", "GET")).permitAll()
                        // Cualquier otra solicitud requiere autenticación (login)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}