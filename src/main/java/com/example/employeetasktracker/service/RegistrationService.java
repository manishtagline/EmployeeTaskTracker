package com.example.employeetasktracker.service;


import com.example.employeetasktracker.dto.RegisterEmployeeRequest;
import com.example.employeetasktracker.dto.RegisterUserRequest;
import com.example.employeetasktracker.entity.Employee;
import com.example.employeetasktracker.entity.User;

public interface RegistrationService {

    User registerUser(RegisterUserRequest request);

    Employee employeeRegister(RegisterEmployeeRequest request);

}
