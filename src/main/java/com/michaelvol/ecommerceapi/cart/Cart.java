package com.michaelvol.ecommerceapi.cart;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michaelvol.ecommerceapi.cart.cartitem.CartItem;
import com.michaelvol.ecommerceapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {
    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(generator = "cart_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart")
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    @PositiveOrZero(message = "Total price must be greater or equal to 0")
    @Builder.Default
    private Double totalPrice = 0.0;
}
