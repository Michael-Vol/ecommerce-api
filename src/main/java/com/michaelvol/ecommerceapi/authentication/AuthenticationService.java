package com.michaelvol.ecommerceapi.authentication;

import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;

    public void registerUser(UserRegisterRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();
        userService.registerUser(user);
    }

}
