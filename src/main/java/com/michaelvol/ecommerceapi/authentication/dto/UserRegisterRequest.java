package com.michaelvol.ecommerceapi.authentication.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserRegisterRequest {
    @NotBlank(message = "First name is required")
    @Max(value = 40, message = "First name must be less than 40 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Max(value = 40, message = "Last name must be less than 40 characters")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Max(value = 40, message = "Username must be less than 40 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Min(value = 8, message = "Password must be at least 8 characters")
    private String password;

    private String address;
}
