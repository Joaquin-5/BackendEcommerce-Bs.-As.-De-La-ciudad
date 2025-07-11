package com.ecommerce.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer stock;
}
