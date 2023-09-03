package com.michaelvol.ecommerceapi.product.dto;

import com.michaelvol.ecommerceapi.product.ProductCategory;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Data
@Builder
public class UpdateProductRequest {
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @Size(max = 5000, message = "Description must be less than 5000 characters")
    private String description;

    private ProductCategory category;

    @URL(message = "Image URL must be valid")
    private String imageUrl;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    private Boolean isAvailable;
}