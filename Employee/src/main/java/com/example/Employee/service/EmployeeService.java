package com.example.Employee.service;

import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
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

   public Optional<Employees> getEmployeesById(int id){
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

    public boolean addEmployee(Integer id, String name, long salary, String address, Date dob){
        Employees employees = new Employees(id,name,salary,address,dob);
        employeeRepository.save(employees);
        return true;
    }

    public List<Employees> listOfRichByAvg(){
        List<Employees> allEmployees = employeeRepository.findAll();
        double averageSalary =  allEmployees.stream().mapToDouble(Employees::getEmpSalary).average().orElse(0); //e->e.getEmpSalary() is Employees::getEmpSalary
        return allEmployees.stream().filter(e->e.getEmpSalary() > averageSalary).collect(Collectors.toList());
    }

    public static int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        return period.getYears();
    }

    public List<Employees> listOfRichByAge(){
        List<Employees> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().filter(e->e.getEmpSalary()>20000 && calculateAge(e.getDob().toLocalDate())>25 && calculateAge(e.getDob().toLocalDate())<35 ).collect(Collectors.toList());
    }
}