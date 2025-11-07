package com.example.TiendaLevelUp.controller;// Paquete actualizado

import com.example.TiendaLevelUp.model.Product; // Modelo actualizado
import com.example.TiendaLevelUp.service.ProductService; // Servicio actualizado
import io.swagger.v3.oas.annotations.Operation; // Anotación moderna de Swagger/OpenAPI
import io.swagger.v3.oas.annotations.tags.Tag; // Anotación moderna de Swagger/OpenAPI
import org.springframework.http.HttpStatus; // Importación para códigos HTTP
import org.springframework.http.ResponseEntity; // Usar ResponseEntity para mejor control
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // URI actualizada
@Tag(name = "Gestión de Productos Gamer", description = "API para manejar el CRUD de productos de la tienda.") // Reemplaza @Api
public class ProductController {

    private final ProductService productService;

    // Inyección por constructor (preferido)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- 1. GET: Obtener todos los productos ---
    @GetMapping
    @Operation(summary = "Ver una lista de productos disponibles", description = "Devuelve todos los productos.") // Reemplaza @ApiOperation
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // --- 2. GET: Obtener producto por ID ---
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por su Id") // Reemplaza @ApiOperation
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build(); // 404 si no existe
    }

    // --- 3. POST: Crear un nuevo producto ---
    @PostMapping
    @Operation(summary = "Añadir un nuevo producto") // Reemplaza @ApiOperation
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct); // 201 Created
    }

    // --- 4. PUT: Actualizar un producto existente ---
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente") // Reemplaza @ApiOperation
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct != null) {
            // Actualización simple de los campos que cambiaste del modelo 'Book'
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setStockQuantity(productDetails.getStockQuantity());
            existingProduct.setCategory(productDetails.getCategory());
            existingProduct.setPlatform(productDetails.getPlatform());
            existingProduct.setGenre(productDetails.getGenre());
            existingProduct.setImageUrl(productDetails.getImageUrl());

            Product updatedProduct = productService.saveProduct(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build(); // 404 si no existe
    }

    // --- 5. DELETE: Eliminar un producto ---
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Añadido para devolver 204
    @Operation(summary = "Eliminar un producto") // Reemplaza @ApiOperation
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}