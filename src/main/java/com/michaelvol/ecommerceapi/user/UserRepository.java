package com.michaelvol.ecommerceapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByFirstName(String firstName);

    Optional<User> findUserByLastName(String lastName);
}
