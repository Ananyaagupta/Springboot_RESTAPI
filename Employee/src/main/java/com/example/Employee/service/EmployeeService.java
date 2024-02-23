package com.example.Employee.service;

import com.example.Employee.dao.EmployeeDao;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employees> listAllEmployees() {
        try {
            return employeeDao.getAllEmployees();
        } catch (Exception e){
            throw new RuntimeException("Error occurred while fetching employees " + e);
        }
    }

   public Employees getEmployeesById(int id){
       try {
           return employeeDao.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("this message is from service layer Employee with ID " + id + " not found"));
       } catch (Exception e) {
           throw new RuntimeException("Error occurred while fetching employee with ID " + id, e);
       }
   }

    public void deleteThisEmployeeById(Integer id) throws ResourceNotFoundException {
        Optional<Employees> optionalEmployee = employeeDao.findEmployeeById(id);
        Employees employee = optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        try {
            employeeDao.deleteEmployee(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting employee with ID " + id, e);
        }
    }


    public boolean addEmployee(Integer id, String name, long salary, String address, Date dob){
        Employees employees = new Employees(id,name,salary,address,dob);
       return employeeDao.saveNewEmployee(employees);

    }

    public List<Employees> listOfRichByAvg(){
       return employeeDao.getListOfRichByAvg();
    }

    public static int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        return period.getYears();
    }

    public List<Employees> listOfRichByAge(){
        return employeeDao.getRichByAge();
    }

}