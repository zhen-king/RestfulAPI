
package com.example.employee.Controller;

import com.example.employee.Entity.Employee;
import com.example.employee.Entity.EmployeeList;
import com.example.employee.Exception.EmployeeNotFoundException;
import com.example.employee.Service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class employeeController {





    private static Logger logger = LoggerFactory.getLogger(employeeController.class);

    private EmployeeService employeeService;

    @Autowired
    public employeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }


    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllStudents();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null){
            throw new EmployeeNotFoundException();
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/age/{ageGreater}")
    public ResponseEntity<List<String>> getEmployeeGreater(@PathVariable("ageGreater") int age) {
        List<Employee> employee = employeeService.findAgeGreater(age);
        if (employee == null){
            throw new EmployeeNotFoundException();
        }
        List<String> names = employee.stream()
                .map(Employee::getEmployee_name)
                .collect(Collectors.toList());
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateUserById(@PathVariable("id") int id, @RequestBody Employee employee){
        Employee currentEmployee = employeeService.findById(id);
        if (currentEmployee == null){
            throw new EmployeeNotFoundException();
        }

        currentEmployee.setEmployee_age(employee.getEmployee_age());
        currentEmployee.setEmployee_name(employee.getEmployee_name());
        currentEmployee.setEmployee_salary(employee.getEmployee_salary());
        currentEmployee.setProfile_image(employee.getProfile_image());
        employeeService.updateEmployee(currentEmployee);
        return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
    }

    //@PostMapping("/")

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") int id) {
        Employee employee = employeeService.deleteById(id);
        if (employee == null){
            throw new EmployeeNotFoundException();
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


}

