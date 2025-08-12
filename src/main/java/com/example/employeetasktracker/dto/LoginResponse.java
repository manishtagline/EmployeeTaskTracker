package com.example.employeetasktracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private final String token;
    private final String tokenType;
    private final long expiresAt;

}
