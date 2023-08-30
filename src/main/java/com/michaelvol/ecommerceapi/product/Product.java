package com.michaelvol.ecommerceapi.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(generator = "product_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 5000, message = "Description must be less than 5000 characters")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @URL(message = "Image URL must be valid")
    @Column(name = "image_url")
    private String imageUrl;

    @Positive(message = "Price must be greater than 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @PositiveOrZero(message = "Quantity must be greater than or equal to 0")
    @Column(name = "quantity", nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    @Column(name = "is_available", nullable = false)
    @Builder.Default
    private Boolean isAvailable = true;
}
