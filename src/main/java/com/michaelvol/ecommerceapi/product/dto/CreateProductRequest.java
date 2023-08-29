package com.michaelvol.ecommerceapi.product.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Data
@Builder
public class CreateProductRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 5000, message = "Description must be less than 5000 characters")
    private String description;

    @NotBlank(message = "Image URL is required")
    @URL(message = "Image URL must be valid")
    private String imageUrl;

    @NotBlank(message = "Price is required")
    private Double price;

    private Integer quantity;

    private Boolean isAvailable;
}
