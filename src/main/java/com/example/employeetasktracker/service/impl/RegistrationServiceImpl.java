package com.example.employeetasktracker.service.impl;

import com.example.employeetasktracker.dto.RegisterEmployeeRequest;
import com.example.employeetasktracker.dto.RegisterUserRequest;
import com.example.employeetasktracker.entity.Employee;
import com.example.employeetasktracker.entity.User;
import com.example.employeetasktracker.entity.constant.Role;
import com.example.employeetasktracker.repository.EmployeeRepository;
import com.example.employeetasktracker.repository.UserRepository;
import com.example.employeetasktracker.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepo;

    private final EmployeeRepository empRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserRequest request) {
        if(userRepo.findByUsername(request.getUsername()).isPresent()){
            log.debug("User {} already exists", request.getUsername());
            throw  new IllegalArgumentException("Username already taken!!!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEnabled(true);

        log.info("User {} saved successfully", request.getUsername());

        return userRepo.save(user);
    }

    @Override
    public Employee employeeRegister(RegisterEmployeeRequest request) {
        if(userRepo.findByUsername(request.getUsername()).isPresent()){
            log.debug("User {} already exists", request.getUsername());
            throw  new IllegalArgumentException("Username already taken!!!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        user.setEnabled(true);
        userRepo.save(user);

        Employee emp = new Employee();
        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setEmail(request.getEmail());
        emp.setDepartment(request.getDepartment());
        emp.setPosition(request.getPosition());
        emp.setPhoneNumber(request.getPhoneNumber());
        emp.setUser(user);

        empRepo.save(emp);

        user.setEmployee(emp);
        userRepo.save(user);

        log.info("Employee {} saved successfully", request.getUsername());

        return emp;
    }
}
