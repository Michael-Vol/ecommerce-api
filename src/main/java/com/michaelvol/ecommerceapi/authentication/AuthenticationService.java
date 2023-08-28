package com.michaelvol.ecommerceapi.authentication;

import com.michaelvol.ecommerceapi.authentication.dto.JwtToken;
import com.michaelvol.ecommerceapi.authentication.dto.UserLoginRequest;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.security.JwtService;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


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

    public User authenticateUser(UserLoginRequest request) throws UsernameNotFoundException {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Check for User Existence in DB
        User user = userService.getUserByEmail(request.getEmail());
        return user;

    }


}
