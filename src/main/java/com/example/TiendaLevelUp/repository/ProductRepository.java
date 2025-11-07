package com.example.TiendaLevelUp.repository;

import com.example.TiendaLevelUp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Buscar todos los productos que pertenecen a una CATEGORÍA específica (ej: "Juego", "Consola").
    List<Product> findByCategory(String category);

    // 2. Buscar todos los productos que son compatibles con una PLATAFORMA (ej: "PS5", "PC").
    List<Product> findByPlatform(String platform);

    // 3. Buscar productos por CATEGORÍA Y GÉNERO (ej: "Juego" y "RPG").
    List<Product> findByCategoryAndGenre(String category, String genre);

    // 4. Buscar productos cuyo nombre contenga una palabra clave (útil para la barra de búsqueda).
    // 'Containing' realiza una búsqueda LIKE %keyword% en SQL.
    List<Product> findByNameContainingIgnoreCase(String keyword);

}
