package com.michaelvol.ecommerceapi.cart.cartitem;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive(message = "Quantity must be greater than 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
