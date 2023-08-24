package com.michaelvol.ecommerceapi.authentication;


import com.michaelvol.ecommerceapi.authentication.dto.JwtToken;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterResponse;
import com.michaelvol.ecommerceapi.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
