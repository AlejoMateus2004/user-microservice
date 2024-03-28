package com.salessytem.usermicroservice.domain.records;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRecord {

    public record UserRequest(
            @NotBlank(message = "Firstname can't be null or empty")
            String firstName,
            @NotBlank(message = "Lastname can't be null or empty")
            String lastName,
            @Email(message = "Invalid email")
            @NotBlank(message = "Email can't be null or empty and should be @gmail or @hotmail")
            String email
    ){
    }

    public record UserResponse(
            @NotBlank(message = "Firstname can't be null or empty")
            String firstName,
            @NotBlank(message = "Lastname can't be null or empty")
            String lastName,
            @Email(message = "Invalid email")
            @NotBlank(message = "Email can't be null or empty and should be @gmail or @hotmail")
            String email,
            @NotBlank(message = "Phone Number can't be null or empty")
            String phoneNumber,
            @NotNull(message = "Firstname can't be null or empty")
            boolean isActive,
            @NotBlank(message = "Username can't be null or empty")
            String userName
    ){
    }
}
