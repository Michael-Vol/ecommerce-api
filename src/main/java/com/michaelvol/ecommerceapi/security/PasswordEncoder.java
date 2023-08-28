package com.michaelvol.ecommerceapi.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class PasswordEncoder extends BCryptPasswordEncoder {
}
