package com.ws101.senobiorayandayan.ecommerceapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    @NotBlank(message = "Product name is required")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Category name is required")
    private String categoryName;

    @PositiveOrZero(message = "Stock quantity cannot be negative")
    private int stockQuantity;

    private String imageUrl;
}