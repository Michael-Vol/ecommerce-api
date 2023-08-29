package com.michaelvol.ecommerceapi.authentication;


import com.michaelvol.ecommerceapi.authentication.dao.AuthenticatedUserInfo;
import com.michaelvol.ecommerceapi.authentication.dto.*;
import com.michaelvol.ecommerceapi.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        User user = authenticationService.registerUser(request);
        JwtToken jwtToken = authenticationService.generateToken(user);
        UserRegisterResponse response = UserRegisterResponse.builder()
                .id(user.getId())
                .message("User with id " + user.getId() + " created")
                .token(jwtToken.getToken())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        AuthenticatedUserInfo authenticatedUserInfo = authenticationService.authenticateUser(request);
        Long userId = authenticatedUserInfo.getUser().getId();
        JwtToken jwtToken = authenticationService.generateToken(authenticatedUserInfo.getAuthentication());
        UserLoginResponse response = UserLoginResponse
                .builder()
                .id(userId)
                .message("User with id " + userId + " logged in successfully")
                .token(jwtToken.getToken())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, authentication);
        return "User logged out successfully";
    }
}
