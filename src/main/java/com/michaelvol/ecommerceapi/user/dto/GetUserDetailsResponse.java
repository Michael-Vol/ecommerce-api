package com.michaelvol.ecommerceapi.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetUserDetailsResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
}
