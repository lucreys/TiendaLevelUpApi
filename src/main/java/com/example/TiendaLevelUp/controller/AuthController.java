package com.example.TiendaLevelUp.controller;

import com.example.TiendaLevelUp.dto.LoginRequest;
import com.example.TiendaLevelUp.model.User;
import com.example.TiendaLevelUp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesario para la encriptación

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // <--- CAMBIO 1: Declaración de campo

    // --- CAMBIO 2: Inyección por Constructor (Añadir PasswordEncoder) ---
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder; // <--- Asignación correcta
    }

    // 1. Endpoint de Registro
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            // Evitar enviar la contraseña de vuelta
            registeredUser.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RuntimeException e) {
            // Error de negocio (ej. usuario ya existe)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 2. Endpoint de Login
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Buscar usuario por nombre de usuario
        User user = userService.findByUsername(loginRequest.getUsername());

        // Verificar si el usuario no existe O si la contraseña no coincide
        // --- CAMBIO 3: Usar SOLO la lógica segura (passwordEncoder.matches) ---
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos."); // 401
        }

        // Éxito: En un sistema real, devolverías un token JWT aquí
        return ResponseEntity.ok("Inicio de sesión exitoso. Bienvenido, " + user.getUsername() + ".");
    }
}