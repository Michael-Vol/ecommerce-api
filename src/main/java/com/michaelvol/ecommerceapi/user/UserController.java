package com.michaelvol.ecommerceapi.user;

import com.michaelvol.ecommerceapi.exception.exceptions.BadRequestException;
import com.michaelvol.ecommerceapi.user.dto.CreateUserRequest;
import com.michaelvol.ecommerceapi.user.dto.GetUserDetailsResponse;
import com.michaelvol.ecommerceapi.user.dto.UserCreatedResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michaelvol.ecommerceapi.utils.AppConstants.API_BASE_URL;

@RestController
@RequestMapping(API_BASE_URL + "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<UserCreatedResponse> createUser(CreateUserRequest createUserRequest) {
        //response sample test

        UserCreatedResponse userCreatedResponse = UserCreatedResponse.builder()
                .userId(1L)
                .message("User with id 1 created")
                .build();
        return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDetailsResponse> getUserDetails() throws BadRequestException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email.isEmpty())
            throw new BadRequestException("User is not logged in");

        User user = userService.getUserByEmail(email);
        GetUserDetailsResponse response = GetUserDetailsResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
