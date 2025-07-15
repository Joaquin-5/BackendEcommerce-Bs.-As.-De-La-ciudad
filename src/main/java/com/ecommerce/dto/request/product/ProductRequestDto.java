package com.ecommerce.dto.request.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
}
