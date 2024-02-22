package com.example.Employee.service;

import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }
}