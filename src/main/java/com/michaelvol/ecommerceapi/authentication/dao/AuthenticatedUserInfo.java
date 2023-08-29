package com.michaelvol.ecommerceapi.authentication.dao;

import com.michaelvol.ecommerceapi.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@AllArgsConstructor
@Builder
public class AuthenticatedUserInfo {
    private final User user;
    private final Authentication authentication;
}
