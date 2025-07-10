package com.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/")
    public String test() {
        return "Se intentó acceder a los productos...";
    }

    public String createProduct() {
        return "Producto creado...";
    }

    public String listProducts() {
        return "Lista de productos";
    }

    public String searchProduct() {
        return "Producto encontrado...";
    }

    public String
}
