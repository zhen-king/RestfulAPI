package com.example.employee.dao;

import com.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {
    List<Employee> getAgeGreater(int age);
}
