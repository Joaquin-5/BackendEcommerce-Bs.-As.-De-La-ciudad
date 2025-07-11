package com.ecommerce.controller;

import com.ecommerce.dto.product.ProductRequestDto;
import com.ecommerce.dto.product.ProductResponseDto;
import com.ecommerce.dto.product.UpdateProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
        );
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return toResponse(productService.getById(id));
    }


    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto dto) {
        Product created = productService.create(dto);
        return toResponse(created);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody UpdateProductDto dto) {
        Product updated = productService.update(id, dto);
        return toResponse(updated);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto partialUpdateProduct(@PathVariable Long id, @RequestBody UpdateProductDto dto) {
        Product updated = productService.partialUpdate(id, dto);
        return toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); // Si todo sale bien reponde con un código 204 sin contenido;
    }
}
