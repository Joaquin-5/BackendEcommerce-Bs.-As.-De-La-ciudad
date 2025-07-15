package com.ecommerce.dto.request.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineRequestDto {
    private Long productId;
    private int quantity;
}
