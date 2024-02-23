package com.example.Employee.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Column(name = "name")
    private String empName;
    @Column(name = "salary")
    private long empSalary;
    @Column(name = "address")
    private String empAddress;
    @Column(name="dob")
    private Date dob;

}
