package com.example.TiendaLevelUp.service;

import com.example.TiendaLevelUp.model.User;
import com.example.TiendaLevelUp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // Necesitarás configurar esto
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección por Constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // <-- Asignación
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        // 2. Encriptar la contraseña antes de guardar (¡Solución al error!)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Guardar el nuevo usuario
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        // Usamos orElse(null) para devolver null si no se encuentra,
        // simplificando la lógica en el controlador
        return userRepository.findByUsername(username).orElse(null);
    }
}