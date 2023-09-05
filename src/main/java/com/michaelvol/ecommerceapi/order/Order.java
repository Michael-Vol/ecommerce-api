package com.michaelvol.ecommerceapi.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michaelvol.ecommerceapi.product.Product;
import com.michaelvol.ecommerceapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "products", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Builder.Default
    private Set<Product> products = Set.of();

    @Column(name = "total_price", nullable = false)
    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;

    @Column(name = "shipping_address", nullable = false)
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @Column(name = "payment_method", nullable = false)
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @Column(name = "status", nullable = false)
    @Enumerated
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private ZonedDateTime dateCreated;

    @Column(name = "date_updated", nullable = false, columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime dateUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
