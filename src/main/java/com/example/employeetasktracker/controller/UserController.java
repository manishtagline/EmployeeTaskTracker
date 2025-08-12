package com.example.employeetasktracker.controller;

import com.example.employeetasktracker.dto.RegisterEmployeeRequest;
import com.example.employeetasktracker.dto.RegisterUserRequest;
import com.example.employeetasktracker.entity.Employee;
import com.example.employeetasktracker.entity.User;
import com.example.employeetasktracker.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final RegistrationService registerService;

    @PostMapping("/register-user")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody RegisterUserRequest req){
        log.info("Received user registration request for user: {}", req.getUsername());

        try{
            User user = registerService.registerUser(req);
            log.info("User {} registered with role {}", user.getUsername(), user.getRole());

            return ResponseEntity.ok(Map.of(
               "message", "User created successfully",
                    "user", user.getUsername(),
                    "role", user.getRole()
            ));
        }catch(IllegalArgumentException e){
            log.warn("User registration failed for user: {}", req.getUsername());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register-employee")
    public ResponseEntity<?> employeeRegistration(@Valid @RequestBody RegisterEmployeeRequest request){
        log.info("Received employee registration request for employee: {}", request.getUsername());

        try{
            Employee emp = registerService.employeeRegister(request);
            log.info("Employee {} registered successfully", emp.getUser().getUsername());

            return ResponseEntity.ok(Map.of(
                "message", "Employee created successfully",
                    "employee", emp.getUser().getUsername(),
                    "employeeId", emp.getId()
            ));
        }catch(IllegalArgumentException e){
            log.warn("Employee registration failed for employee: {}", request.getUsername());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
