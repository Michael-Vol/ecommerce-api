package com.michaelvol.ecommerceapi.cart.cartitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michaelvol.ecommerceapi.cart.Cart;
import com.michaelvol.ecommerceapi.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartItem {
    @Id
    @SequenceGenerator(name = "cart_item_sequence", sequenceName = "cart_item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive(message = "Quantity must be greater than 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
