package com.ecommerce.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineResponseDto {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}
