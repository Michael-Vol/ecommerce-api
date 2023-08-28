package com.michaelvol.ecommerceapi.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLoginResponse {
    private Long id;

    private String message;

    private String token;
}
