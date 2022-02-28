package com.example.employee.Service;

import com.example.employee.Entity.Employee;
import com.example.employee.Repository.EmployeeRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EmployeeService{



    List<Employee> getAllStudents();

    Employee findById(int id);

    Employee updateEmployee(Employee currentEmployee);

    Employee deleteById(int id);

    Employee saveEmployee(Employee currentEmployee);

    List<Employee> findAgeGreater(int age);
}
