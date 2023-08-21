package com.michaelvol.ecommerceapi.user;

import com.michaelvol.ecommerceapi.authentication.dao.UserAvailability;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    UserAvailability checkUsernameAvailability(String username);

    UserAvailability checkEmailAvailability(String email);

    User registerUser(User user);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void deleteUser(Long userId);

    void updateUser(User user);

}
