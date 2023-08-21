package com.michaelvol.ecommerceapi.authentication;


import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterRequest;
import com.michaelvol.ecommerceapi.authentication.dto.UserRegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController(API_BASE_URL + "/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {


        UserRegisterResponse response = UserRegisterResponse.builder()
                .id(1L)
                .message("User with id 1 created")
                .build();
        return new ResponseEntity<UserRegisterResponse>(response, HttpStatus.CREATED);
    }
}
