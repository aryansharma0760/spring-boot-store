package com.example.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
}
