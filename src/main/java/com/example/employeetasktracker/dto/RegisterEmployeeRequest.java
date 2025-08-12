package com.example.employeetasktracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterEmployeeRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Phone number must be between 7 and 15 digits and can start with +"
    )
    private String phoneNumber;

    @Size(max = 50, message = "Department name must not exceed 50 characters")
    private String department;

    @Size(max = 50, message = "Position must not exceed 50 characters")
    private String position;
}
