package com.example.Employee.controller;

import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeAddController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam Integer id, @RequestParam String name, @RequestParam long salary, @RequestParam String address) {
        if (employeeService.addEmployee(id,name,salary,address)) {
            return ResponseEntity.ok("Employee with ID " + id + " added successfully");

        }
        return ResponseEntity.status(HttpStatusCode.valueOf(77)).body("Could not add");
    }
}
