package com.ecommerce.service;

import com.ecommerce.dto.request.product.ProductRequestDto;
import com.ecommerce.dto.update.UpdateProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.product.ProductNotFoundException;
import com.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto con el ID: " + id + " no fue encontrado"));
    }

    public Product create(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return productRepository.save(product);
    }


    // Método para la actulización total de un producto, PUT.
    public Product update(Long id, UpdateProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto seleccionado no fue encontrado"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());

        return productRepository.save(existing);
    }

    // Método para actualización parcial de un producto, es decir, un o algunos campos en específico, no todos, PATCH
    public Product partialUpdate(Long id, UpdateProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("El producto seleccionado para actualizar no fue encontrado"));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getCategory() != null) existing.setCategory(dto.getCategory());
        if (dto.getPrice() != null) existing.setPrice(dto.getPrice());
        if (dto.getStock() != null) existing.setStock(dto.getStock());

        return productRepository.save(existing);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado para eliminar"));
        ;
        productRepository.delete(product);
    }
}
