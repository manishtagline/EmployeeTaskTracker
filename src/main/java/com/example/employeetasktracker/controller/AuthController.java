package com.example.employeetasktracker.controller;

import com.example.employeetasktracker.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginForm(@ModelAttribute LoginRequest request,
                                  HttpServletResponse response,
                                  Model model){
        String username = request.username;
        log.info("Login received from user: {}", username);
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, request.getPassword())
            );

            UserDetails user = userDetailsService.loadUserByUsername(username);
            String token = jwtTokenProvider.generatedToken(user);

            response.setHeader("Authorization" , "Bearer " + token);

            log.info("User {} logged Successfully", username);

            return "redirect:/";

        }catch (BadCredentialsException e){
            log.warn("Invalid login attempt from user: {}", username);
            model.addAttribute("error", "Invalid username or password!!!");
            return "login";
        }
    }

    @Data
    public static class LoginRequest{
        private String username;
        private String password;
    }

}
