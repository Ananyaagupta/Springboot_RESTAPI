package com.example.Employee.service;

import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

   public Optional<Employees> getAllEmployeesById(int id){
       return employeeRepository.findById(id);
   }

    public boolean deleteThisEmployees(Integer id){
        Optional<Employees> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return true; // Deletion successful
        } else {
            return false; // Employee with the given ID not found
        }

    }

    public boolean addEmployee(Integer id,String name,long salary,String address){
        Employees employees = new Employees(id,name,salary,address);
        employeeRepository.save(employees);
        return true;
    }

    public List<Employees> listOfRichByAvg(){
        List<Employees> allEmployees = employeeRepository.findAll();
        double averageSalary =  allEmployees.stream().mapToDouble(Employees::getEmpSalary).average().orElse(0); //e->e.getEmpSalary() is Employees::getEmpSalary
        return allEmployees.stream().filter(e->e.getEmpSalary() > averageSalary).collect(Collectors.toList());
    }

}