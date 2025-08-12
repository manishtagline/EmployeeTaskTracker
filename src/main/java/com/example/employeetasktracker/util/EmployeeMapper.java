package com.example.employeetasktracker.util;

import com.example.employeetasktracker.dto.EmployeeDTO;
import com.example.employeetasktracker.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDto(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setPosition(employee.getPosition());
        dto.setPhoneNumber(employee.getPhoneNumber());

        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto){
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setPosition(dto.getPosition());
        emp.setPhoneNumber(dto.getPhoneNumber());

        return emp;
    }

}
