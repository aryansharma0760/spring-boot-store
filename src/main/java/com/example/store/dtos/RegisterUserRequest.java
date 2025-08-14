package com.example.store.dtos;

import com.example.store.validations.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "name is required")
    @Size(max = 255, message = "name must be less than 255 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @Lowercase(message = "email must be lowercase")
    private String email;

    @NotBlank(message = "password is a must")
    @Size(min = 6, max = 25, message = "password must be between 6 to 25 characters long")
    private String password;
}
