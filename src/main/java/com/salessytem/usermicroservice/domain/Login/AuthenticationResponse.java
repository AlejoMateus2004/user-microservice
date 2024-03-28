package com.salessytem.usermicroservice.domain.Login;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String FullName;
    private String username;
    private String role;
    private String jwt;

}
