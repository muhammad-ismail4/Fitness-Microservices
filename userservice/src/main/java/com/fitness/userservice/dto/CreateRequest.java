package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRequest {

    @NotNull(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;
    private String firstName;
    private String lastName;

}
