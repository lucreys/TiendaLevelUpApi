package com.example.TiendaLevelUp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("application", "TiendaLevelUp API");
        response.put("version", "1.0");
        response.put("message", "¡Bienvenido a la API de la Tienda Gamer!");
        response.put("documentation", "http://localhost:9090/swagger-ui.html");
        response.put("endpoints_products", "/api/products");
        response.put("endpoints_auth", "/api/auth/register, /api/auth/login");

        return response;
    }

}