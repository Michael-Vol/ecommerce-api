package com.michaelvol.ecommerceapi.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserRegisterResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String address;

    private String message;
}
