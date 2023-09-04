package com.michaelvol.ecommerceapi.order;

import com.michaelvol.ecommerceapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findFirstByUserOrderByDateCreatedDesc(User user);

}
