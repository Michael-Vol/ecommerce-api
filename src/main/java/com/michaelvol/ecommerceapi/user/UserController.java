package com.michaelvol.ecommerceapi.user;

import com.michaelvol.ecommerceapi.user.dto.CreateUserRequest;
import com.michaelvol.ecommerceapi.user.dto.UserCreatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/new")
    public ResponseEntity<UserCreatedResponse> createUser(CreateUserRequest createUserRequest) {
        //response sample test
        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .userId(1L)
                .message("User with id 1 created")
                .build();
        return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);
    }

}
