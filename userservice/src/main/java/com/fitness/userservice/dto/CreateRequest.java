package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
