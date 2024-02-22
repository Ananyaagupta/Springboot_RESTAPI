package com.example.Employee.controller;

import com.example.Employee.model.Employees;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees/delete")
public class EmployeeDeleteController {
    @Autowired
    private EmployeeService employeeService;
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        if(employeeService.deleteThisEmployees(id)) {
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found with ID " + id);
        }
    }
}
