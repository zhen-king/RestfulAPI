package com.example.employee.controller;

//import com.example.employee.dto.EmployeeDTO;
import com.example.employee.dto.DTO;
import com.example.employee.entity.Employee;
import com.example.employee.exception.TooManyAttemptsException;
import com.example.employee.service.EmployeeService;
import com.example.employee.source.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController //Controller + respond body

public class EmployeeController {

    private EmployeeService employeeService;
    private RestTemplate restTemplate;
    @Autowired
    public EmployeeController(EmployeeService employeeService, RestTemplate restTemplate) {
        this.employeeService = employeeService;
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping(value = "/employees")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return employeeService.saveEmployees(employees);
    }

    @PostMapping(value ="/Employees")
    public List<Employee> getAllEmployees() {
        ResponseEntity<DTO> response;
        List<Employee> employees;
        response = restTemplate.getForEntity(Source.BASEURL + "/employees", DTO.class);
        employees = response.getBody().getData();
        return employeeService.saveEmployees(employees);
    }

    @GetMapping(value = "/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(value = "/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/employee/age/{age}")
    public List<Employee> getAgeGreater(@PathVariable("age") int age){
        return employeeService.getAgeGreater(age);
    }

    @PutMapping(value = "/employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateProduct(employee);
    }

    @DeleteMapping(value = "/employee/{id}")
    public String deleteProduct(@PathVariable("id")  int id){
        return employeeService.deleteEmployee(id);
    }



}
