package com.example.TiendaLevelUp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok Anotations:
@Data               // Genera Getters, Setters, toString, equals y hashCode
@NoArgsConstructor  // Genera constructor sin argumentos (requerido por JPA)
@AllArgsConstructor // Genera constructor con todos los argumentos
@Entity             // Marca esta clase como una tabla en la base de datos
@Table(name = "products") // Nombre de la tabla
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 1000) // Columna más larga para la descripción
    private String description;
    private Integer price;
    private Integer stockQuantity; // Inventario
    private String category; // Juego, Consola, Accesorio
    private String imageUrl; // URL de la imagen del producto
}