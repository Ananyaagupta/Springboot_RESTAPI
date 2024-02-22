package com.example.Employee.controller;


import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
