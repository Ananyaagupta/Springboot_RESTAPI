package com.example.Employee.controller;

import com.example.Employee.model.Employees;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/fetch")
    public List<Employees> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        if(employeeService.deleteThisEmployees(id)) {
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found with ID " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam Integer id, @RequestParam String name, @RequestParam long salary, @RequestParam String address) {
        if (employeeService.addEmployee(id,name,salary,address)) {
            return ResponseEntity.ok("Employee with ID " + id + " added successfully");

        }
        return ResponseEntity.status(HttpStatusCode.valueOf(77)).body("Could not add");
    }

    @GetMapping("/fetchById/{id}")
    public Optional<Employees> fetchEmployeesById(int id) {
        return employeeService.getAllEmployeesById(id);
    }

    @GetMapping("/richierich")
    public ResponseEntity<List<Employees>> richFolks() {
        List<Employees> richEmployees = employeeService.listOfRichByAvg();
        return new ResponseEntity<>(richEmployees, HttpStatus.OK);
    }

}
