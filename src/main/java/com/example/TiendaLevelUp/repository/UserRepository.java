package com.example.TiendaLevelUp.repository;

import com.example.TiendaLevelUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método clave: Necesario para verificar si un usuario ya existe y para el proceso de login
    Optional<User> findByUsername(String username);

    // Útil para verificar si el correo ya está en uso
    Optional<User> findByEmail(String email);
}