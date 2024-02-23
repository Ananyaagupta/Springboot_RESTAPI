package com.example.Employee.controller;

import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.model.Employees;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/fetch")
    public ResponseEntity<?> returnAllEmployees() {
        try {
            return new ResponseEntity<>(employeeService.listAllEmployees(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
        try {
            employeeService.deleteThisEmployeeById(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee with Id"+ id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }



    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestParam Integer id, @RequestParam String name, @RequestParam long salary, @RequestParam String address, @RequestParam Date dob) {
        if (employeeService.addEmployee(id,name,salary,address,dob)) {
            return ResponseEntity.ok("Employee with ID " + id + " added successfully");

        }
        return ResponseEntity.status(HttpStatusCode.valueOf(77)).body("Error in adding");
    }

    @GetMapping("/fetchById")
    public ResponseEntity<?> fetchEmployeesById(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(employeeService.getEmployeesById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee with Id"+ id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

    @GetMapping("/richieRich")
    public ResponseEntity<List<Employees>> richFolks() {
        return new ResponseEntity<>(employeeService.listOfRichByAvg(), HttpStatus.OK);
    }

    @GetMapping("/youngHighSalary")
    public ResponseEntity<List<Employees>> youngRichFolks(){
        return new ResponseEntity<>(employeeService.listOfRichByAge(), HttpStatus.OK);

    }

}
