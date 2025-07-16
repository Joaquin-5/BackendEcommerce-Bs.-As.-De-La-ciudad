package com.ecommerce.service;

import com.ecommerce.dto.request.order.OrderRequestDto;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderLine;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.order.OrderNotFoundException;
import com.ecommerce.exception.product.InsufficientStockException;
import com.ecommerce.exception.product.ProductNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("El pedido con el ID: " + id + " no fue encontrado"));
    }

    public Order create(OrderRequestDto dto) {
        Order order = new Order();

        dto.getItems().forEach(itemDto -> {
            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("El producto con el ID: " + itemDto.getProductId() + " no fue econtrado"));

            // Validar si hay stock suficiente
            if (product.getStock() < itemDto.getQuantity()) {
                throw new InsufficientStockException("No hay suficiente stock para el producto " + product.getName());
            }

            // Descontar el stock de los productos en el pedido
            product.setStock(product.getStock() - itemDto.getQuantity());

            OrderLine line = new OrderLine();
            line.setProduct(product);
            line.setQuantity(itemDto.getQuantity());
            line.setPrice(product.getPrice());
            line.setOrder(order);

            order.getItems().add(line);
        });

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("El pedido con el ID: " + id + " no fue encontrado");
        }
        orderRepository.deleteById(id);
    }
}
