package com.michaelvol.ecommerceapi.authentication;

import com.michaelvol.ecommerceapi.authentication.dto.JwtToken;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.security.JwtService;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;

    public User registerUser(UserRegisterRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .build();
        return userService.registerUser(user);
    }

    public JwtToken generateToken(@Nonnull User user) {
        String token = jwtService.generateToken(new HashMap<>(), user);
        return new JwtToken(token);
    }

}
