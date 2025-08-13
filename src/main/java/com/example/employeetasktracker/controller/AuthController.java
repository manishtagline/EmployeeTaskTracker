package com.example.employeetasktracker.controller;

import com.example.employeetasktracker.dto.ErrorResponse;
import com.example.employeetasktracker.dto.LoginRequest;
import com.example.employeetasktracker.dto.LoginResponse;
import com.example.employeetasktracker.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> handleLoginJson(@RequestBody LoginRequest request){
        String username = request.getUsername();
        log.info("Login attempt from user: {}", username);

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, request.getPassword())
            );

            UserDetails user = userDetailsService.loadUserByUsername(username);
            String token = jwtTokenProvider.generatedToken(user);

            log.info("User {} logged Successfully", username);

            return ResponseEntity.ok(new LoginResponse(token, "Bearer", jwtTokenProvider.extractExpiration(token).getTime()));
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            log.warn("Invalid login attempt for user: {}", username);
            return ResponseEntity.status(401).body(new ErrorResponse("Invalid username or password!!!"));
        }
    }

}
