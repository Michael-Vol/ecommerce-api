package com.michaelvol.ecommerceapi.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserLoginRequest {
    private String email;
    
    private String password;
}
