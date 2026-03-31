package com.project.fitness.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is Required ..!")
    @Email(message = "Invalid Email-Id ..!")
    private String email;

    @NotBlank(message = "Password is Required ..!")
    private String password;

    @NotBlank(message = "first name is Required ..!")
    private String firstName;

    @NotBlank(message = "Last name is Required ..!")
    private String lastName;
}
