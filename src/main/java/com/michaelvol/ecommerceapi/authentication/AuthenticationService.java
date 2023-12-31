package com.michaelvol.ecommerceapi.authentication;

import com.michaelvol.ecommerceapi.authentication.dao.AuthenticatedUserInfo;
import com.michaelvol.ecommerceapi.authentication.dto.JwtToken;
import com.michaelvol.ecommerceapi.authentication.dto.UserLoginRequest;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.security.JwtService;
import com.michaelvol.ecommerceapi.user.User;
import com.michaelvol.ecommerceapi.user.UserService;
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

    public JwtToken generateToken(Authentication authentication) {
        String token = jwtService.generateToken(new HashMap<>(), authentication);
        return new JwtToken(token);
    }

    public JwtToken generateToken(User user) {
        String token = jwtService.generateToken(new HashMap<>(), user);
        return new JwtToken(token);
    }

    public AuthenticatedUserInfo authenticateUser(UserLoginRequest request) throws UsernameNotFoundException,
            BadRequestException {

        //        //Check if user is already logged in
        //        Boolean userIsLoggedIn = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        //        if (userIsLoggedIn)
        //            throw new BadRequestException("User is already logged in");


        //Authenticate User
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Check for User Existence in DB
        User user = userService.getUserByEmail(request.getEmail());

        AuthenticatedUserInfo authenticatedUserInfo = AuthenticatedUserInfo.builder()
                .user(user)
                .authentication(authentication)
                .build();
        return authenticatedUserInfo;
    }

}
