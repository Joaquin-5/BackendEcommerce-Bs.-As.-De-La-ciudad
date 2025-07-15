package com.ecommerce.controller;

import com.ecommerce.dto.response.order.OrderLineResponseDto;
import com.ecommerce.dto.request.order.OrderRequestDto;
import com.ecommerce.dto.response.order.OrderResponseDto;
import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponseDto toResponse(Order order) {
        List<OrderLineResponseDto> lineDtos = order.getItems().stream().map(line -> {
            OrderLineResponseDto dto = new OrderLineResponseDto();
            dto.setProductId(line.getProduct().getId());
            dto.setProductName(line.getProduct().getName());
            dto.setQuantity(line.getQuantity());
            dto.setPrice(line.getPrice());
            return dto;
        }).toList();

        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCreated(order.getCreatedAt());
        dto.setItems(lineDtos);
        return dto;
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        return toResponse(orderService.getById(id));
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto dto) {
        Order created = orderService.create(dto);
        return toResponse(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
