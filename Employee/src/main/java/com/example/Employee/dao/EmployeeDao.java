package com.example.Employee.dao;

import com.example.Employee.model.Employees;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.Employee.service.EmployeeService.calculateAge;

@Component
public class EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employees> findEmployeeById(int id){
        return employeeRepository.findById(id);
    }


    public List<Employees> getListOfRichByAvg(){
        List<Employees> allEmployees = employeeRepository.findAll();
        double averageSalary =  allEmployees.stream().mapToDouble(Employees::getEmpSalary).average().orElse(0); //e->e.getEmpSalary() is Employees::getEmpSalary
        return allEmployees.stream().filter(e->e.getEmpSalary() > averageSalary).collect(Collectors.toList());
    }

    public Boolean saveNewEmployee(Employees employees){
        employeeRepository.save(employees);
        return true;
    }

    public List<Employees> getRichByAge(){
        List<Employees> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().filter(e->e.getEmpSalary()>20000 && calculateAge(e.getDob().toLocalDate())>25 && calculateAge(e.getDob().toLocalDate())<35 ).collect(Collectors.toList());
    }

    public void deleteEmployee(Employees employees){
        employeeRepository.delete(employees);

    }
}
