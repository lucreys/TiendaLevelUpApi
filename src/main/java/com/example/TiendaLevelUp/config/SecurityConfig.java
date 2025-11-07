package com.example.TiendaLevelUp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Importación necesaria para HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define el Codificador de Contraseñas (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configura las Reglas de Seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth

                        // CAMBIO CLAVE AQUI: Añadir los patrones de Swagger UI/SpringDoc
                        .requestMatchers(
                                "/",
                                "/api/auth/**",
                                "/h2-console/**",
                                // Patrones de SpringDoc/Swagger UI:
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Permite acceso libre solo a GET /api/products
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()

                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                );

        // CAMBIO 2: La forma correcta de deshabilitar frameOptions
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}