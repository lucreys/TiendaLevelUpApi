package com.example.TiendaLevelUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Nombre de la tabla para usuarios
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Usado para login

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // ¡Debe ser guardada encriptada!

    // Opcional: Para Spring Security, puedes añadir roles
    // private String role = "USER";
}